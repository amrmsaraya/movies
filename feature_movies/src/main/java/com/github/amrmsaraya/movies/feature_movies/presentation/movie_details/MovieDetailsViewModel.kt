package com.github.amrmsaraya.movies.feature_movies.presentation.movie_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.amrmsaraya.movies.common.di.MainDispatcher
import com.github.amrmsaraya.movies.common.util.Result
import com.github.amrmsaraya.movies.feature_movies.domain.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val intentChannel = Channel<MovieDetailsIntent>()

    var uiState by mutableStateOf(MovieDetailsUiState())
        private set

    init {
        handleIntent()
    }

    fun sendIntent(intent: MovieDetailsIntent) = viewModelScope.launch(dispatcher) {
        intentChannel.send(intent)
    }

    private fun handleIntent() = viewModelScope.launch(dispatcher) {
        intentChannel.consumeAsFlow().collect {
            when (it) {
                is MovieDetailsIntent.GetMovieDetails -> getMovieDetails(it.id)
            }
        }
    }

    private fun getMovieDetails(id: Long) = viewModelScope.launch(dispatcher) {
        uiState = uiState.copy(isLoading = true)
        uiState = when (val movie = getMovieDetailsUseCase(id)) {
            is Result.Success -> uiState.copy(movie = movie.result)
            is Result.Error -> uiState.copy(
                movie = movie.result,
                throwable = movie.throwable
            )
        }
        uiState = uiState.copy(isLoading = false)
    }
}