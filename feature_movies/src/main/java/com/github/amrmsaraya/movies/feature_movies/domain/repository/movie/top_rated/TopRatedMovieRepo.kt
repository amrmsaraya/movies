package com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.top_rated

import androidx.paging.PagingSource
import com.github.amrmsaraya.movies.database.dto.top_rated.TopRatedMovieDTO
import com.github.amrmsaraya.movies.feature_movies.domain.entity.movie.Movie

interface TopRatedMovieRepo {
    suspend fun insertAll(movies: List<Movie>)
    suspend fun clearMovies()
    fun getLocalMovies(): PagingSource<Int, TopRatedMovieDTO>
    suspend fun getRemoteMovies(page: Int): List<Movie>
}