package com.github.amrmsaraya.movies.feature_movies.data.source.remote

import com.github.amrmsaraya.movies.network.dto.movie.MovieResponseNDTO
import com.github.amrmsaraya.movies.network.dto.movie_details.MovieDetailsNDTO

interface MovieRemoteDataSource {
    suspend fun getPopularMovies(page: Int): MovieResponseNDTO
    suspend fun getTopRatedMovies(page: Int): MovieResponseNDTO
    suspend fun getMovieDetails(id: Long): MovieDetailsNDTO
}