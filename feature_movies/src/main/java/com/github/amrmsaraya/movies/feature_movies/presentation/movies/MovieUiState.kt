package com.github.amrmsaraya.movies.feature_movies.presentation.movies

import androidx.paging.PagingData
import com.github.amrmsaraya.movies.feature_movies.domain.entity.movie.Movie
import com.github.amrmsaraya.movies.preference.domain.entity.MovieSort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class MovieUiState(
    val popularMovies: Flow<PagingData<Movie>> = flow { },
    val topRatedMovies: Flow<PagingData<Movie>> = flow { },
    val movieSort: MovieSort = MovieSort.POPULAR,
)
