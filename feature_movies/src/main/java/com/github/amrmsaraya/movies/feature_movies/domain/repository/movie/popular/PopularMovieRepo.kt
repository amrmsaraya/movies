package com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.popular

import androidx.paging.PagingSource
import com.github.amrmsaraya.movies.database.dto.popular.PopularMovieDTO
import com.github.amrmsaraya.movies.feature_movies.domain.entity.movie.Movie

interface PopularMovieRepo {
    suspend fun insertAll(movies: List<Movie>)
    suspend fun clearMovies()
    fun getLocalMovies(): PagingSource<Int, PopularMovieDTO>
    suspend fun getRemoteMovies(page: Int): List<Movie>
}