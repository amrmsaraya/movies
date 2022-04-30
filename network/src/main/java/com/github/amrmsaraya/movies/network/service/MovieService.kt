package com.github.amrmsaraya.movies.network.service

import com.github.amrmsaraya.movies.network.dto.movie.MovieResponseNDTO
import com.github.amrmsaraya.movies.network.dto.movie_details.MovieDetailsNDTO

interface MovieService {
    suspend fun getPopularMovies(page: Int): MovieResponseNDTO
    suspend fun getTopRatedMovies(page: Int): MovieResponseNDTO
    suspend fun getMovieDetails(id: Long): MovieDetailsNDTO
}