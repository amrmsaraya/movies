package com.github.amrmsaraya.movies.feature_movies.data.sourceImpl.local.movie_details

import com.github.amrmsaraya.movies.database.dao.movie_details.MovieDetailsDao
import com.github.amrmsaraya.movies.database.dto.movie_details.MovieDetailsDTO
import com.github.amrmsaraya.movies.feature_movies.data.source.local.movie_details.MovieDetailsLocalDataSource
import kotlinx.coroutines.flow.Flow

class MovieDetailsLocalDataSourceImpl(
    private val movieDetailsDao: MovieDetailsDao
) : MovieDetailsLocalDataSource {
    override suspend fun insert(movie: MovieDetailsDTO) {
        movieDetailsDao.insert(movie)
    }

    override suspend fun getMovieDetails(id: Long): MovieDetailsDTO {
        return movieDetailsDao.getMovieDetails(id)
    }
}