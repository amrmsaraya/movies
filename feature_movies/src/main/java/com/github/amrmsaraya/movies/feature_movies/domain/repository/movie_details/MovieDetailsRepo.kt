package com.github.amrmsaraya.movies.feature_movies.domain.repository.movie_details

import com.github.amrmsaraya.movies.feature_movies.domain.entity.movie_details.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepo {
    suspend fun insert(movie: MovieDetails)
    fun getLocalMovieDetails(id: Long): Flow<MovieDetails>
    suspend fun getRemoteMovieDetails(id: Long): MovieDetails
}