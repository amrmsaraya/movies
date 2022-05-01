package com.github.amrmsaraya.movies.feature_movies.presentation.movie_details

sealed class MovieDetailsIntent {
    data class GetMovieDetails(val id: Long) : MovieDetailsIntent()
}
