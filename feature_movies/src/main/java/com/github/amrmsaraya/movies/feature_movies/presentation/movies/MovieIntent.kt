package com.github.amrmsaraya.movies.feature_movies.presentation.movies

import com.github.amrmsaraya.movies.preference.domain.entity.MovieSort

sealed class MovieIntent {
    object GetPopularMovies : MovieIntent()
    object GetTopRatedMovies : MovieIntent()
    object GetMovieSort : MovieIntent()
    data class SaveMovieSort(val sort: MovieSort) : MovieIntent()
}
