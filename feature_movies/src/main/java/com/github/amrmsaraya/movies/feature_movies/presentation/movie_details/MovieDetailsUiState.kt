package com.github.amrmsaraya.movies.feature_movies.presentation.movie_details

import com.github.amrmsaraya.movies.feature_movies.domain.entity.movie_details.MovieDetails

data class MovieDetailsUiState(
    val movie: MovieDetails? = null,
    val throwable: Throwable? = null,
    val isLoading: Boolean = false
)
