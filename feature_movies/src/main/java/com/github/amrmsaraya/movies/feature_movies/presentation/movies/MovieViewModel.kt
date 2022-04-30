package com.github.amrmsaraya.movies.feature_movies.presentation.movies

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.amrmsaraya.movies.common.di.MainDispatcher
import com.github.amrmsaraya.movies.feature_movies.domain.usecase.GetPopularMoviesUseCase
import com.github.amrmsaraya.movies.feature_movies.domain.usecase.GetTopRatedMoviesUseCase
import com.github.amrmsaraya.movies.preference.domain.entity.MovieSort
import com.github.amrmsaraya.movies.preference.domain.usecase.GetMovieSortUseCase
import com.github.amrmsaraya.movies.preference.domain.usecase.SaveMovieSortUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val saveMovieSortUseCase: SaveMovieSortUseCase,
    private val getMovieSortUseCase: GetMovieSortUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val intentChannel = Channel<MovieIntent>()

    var uiState by mutableStateOf(MovieUiState())
        private set

    init {
        handleIntent()
        getMovieSort()
    }

    fun sendIntent(intent: MovieIntent) = viewModelScope.launch(dispatcher) {
        intentChannel.send(intent)
    }

    private fun handleIntent() = viewModelScope.launch(dispatcher) {
        intentChannel.consumeAsFlow().collect {
            when (it) {
                MovieIntent.GetPopularMovies -> getPopularMovies()
                MovieIntent.GetTopRatedMovies -> getTopRatedMovies()
                MovieIntent.GetMovieSort -> getMovieSort()
                is MovieIntent.SaveMovieSort -> saveMovieSort(it.sort)
            }
        }
    }

    private fun getPopularMovies() = viewModelScope.launch(dispatcher) {
        val flow = getPopularMoviesUseCase()
        uiState = uiState.copy(popularMovies = flow)
    }

    private fun getTopRatedMovies() = viewModelScope.launch(dispatcher) {
        val flow = getTopRatedMoviesUseCase()
        uiState = uiState.copy(topRatedMovies = flow)
    }

    private fun getMovieSort() = viewModelScope.launch(dispatcher) {
        getMovieSortUseCase().collectLatest {
            uiState = uiState.copy(movieSort = it)
        }
    }

    private fun saveMovieSort(sort: MovieSort) = viewModelScope.launch(dispatcher) {
        saveMovieSortUseCase(sort)
    }
}