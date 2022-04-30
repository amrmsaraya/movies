package com.github.amrmsaraya.movies.network.serviceImpl

import com.github.amrmsaraya.movies.network.service.MovieService
import com.github.amrmsaraya.movies.network.dto.movie.MovieResponseNDTO
import com.github.amrmsaraya.movies.network.dto.movie_details.MovieDetailsNDTO
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class MovieServiceImpl(private val client: HttpClient) : MovieService {
    override suspend fun getPopularMovies(page: Int): MovieResponseNDTO {
        return client.get("3/movie/popular") {
            parameter("page", page)
        }.body()
    }

    override suspend fun getTopRatedMovies(page: Int): MovieResponseNDTO {
        return client.get("3/movie/top_rated") {
            parameter("page", page)
        }.body()
    }

    override suspend fun getMovieDetails(id: Long): MovieDetailsNDTO {
        return client.get("3/movie/$id").body()
    }
}