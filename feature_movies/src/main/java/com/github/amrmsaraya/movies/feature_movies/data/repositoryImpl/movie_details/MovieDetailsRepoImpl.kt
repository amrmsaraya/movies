package com.github.amrmsaraya.movies.feature_movies.data.repositoryImpl.movie_details

import com.github.amrmsaraya.movies.common.di.IoDispatcher
import com.github.amrmsaraya.movies.feature_movies.data.mapper.toMovieDetails
import com.github.amrmsaraya.movies.feature_movies.data.mapper.toMovieDetailsDTO
import com.github.amrmsaraya.movies.feature_movies.data.source.local.movie_details.MovieDetailsLocalDataSource
import com.github.amrmsaraya.movies.feature_movies.data.source.remote.MovieRemoteDataSource
import com.github.amrmsaraya.movies.feature_movies.domain.entity.movie_details.MovieDetails
import com.github.amrmsaraya.movies.feature_movies.domain.repository.movie_details.MovieDetailsRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MovieDetailsRepoImpl(
    private val movieDetailsLocalDataSource: MovieDetailsLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : MovieDetailsRepo {
    override suspend fun insert(movie: MovieDetails) {
        withContext(dispatcher) {
            movieDetailsLocalDataSource.insert(movie.toMovieDetailsDTO())
        }
    }

    override fun getLocalMovieDetails(id: Long): Flow<MovieDetails> {
        return movieDetailsLocalDataSource.getMovieDetails(id).map { it.toMovieDetails() }
    }

    override suspend fun getRemoteMovieDetails(id: Long): MovieDetails {
        return withContext(dispatcher) {
            movieRemoteDataSource.getMovieDetails(id).toMovieDetails()
        }
    }
}