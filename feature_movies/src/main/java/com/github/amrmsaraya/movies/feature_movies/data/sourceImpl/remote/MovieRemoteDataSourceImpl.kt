package com.github.amrmsaraya.movies.feature_movies.data.sourceImpl.remote

import com.github.amrmsaraya.movies.feature_movies.data.source.remote.MovieRemoteDataSource
import com.github.amrmsaraya.movies.network.dto.movie.MovieResponseNDTO
import com.github.amrmsaraya.movies.network.dto.movie_details.MovieDetailsNDTO
import com.github.amrmsaraya.movies.network.service.MovieService

class MovieRemoteDataSourceImpl(
    private val movieService: MovieService
) : MovieRemoteDataSource {
    override suspend fun getPopularMovies(page: Int): MovieResponseNDTO {
        return movieService.getPopularMovies(page)
    }

    override suspend fun getTopRatedMovies(page: Int): MovieResponseNDTO {
        return movieService.getTopRatedMovies(page)
    }

    override suspend fun getMovieDetails(id: Long): MovieDetailsNDTO {
        return movieService.getMovieDetails(id)
    }
}