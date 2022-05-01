package com.github.amrmsaraya.movies.feature_movies.domain.repository.movie_details

import com.github.amrmsaraya.movies.feature_movies.domain.entity.movie_details.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepo {
    suspend fun insert(movie: MovieDetails)
    suspend fun getLocalMovieDetails(id: Long): MovieDetails
    suspend fun getRemoteMovieDetails(id: Long): MovieDetails
}