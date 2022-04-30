package com.github.amrmsaraya.movies.feature_movies.data.source.local.movie_details

import com.github.amrmsaraya.movies.database.dto.movie_details.MovieDetailsDTO
import kotlinx.coroutines.flow.Flow

interface MovieDetailsLocalDataSource {
    suspend fun insert(movie: MovieDetailsDTO)
    fun getMovieDetails(id: Long): Flow<MovieDetailsDTO>
}