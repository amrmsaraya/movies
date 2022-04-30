package com.github.amrmsaraya.movies.feature_movies.data.source.local.top_rated

import androidx.paging.PagingSource
import com.github.amrmsaraya.movies.database.dto.top_rated.TopRatedMovieDTO

interface TopRatedMovieLocalDataSource {
    suspend fun insertAll(movies: List<TopRatedMovieDTO>)
    suspend fun clearMovies()
    fun getMovies(): PagingSource<Int, TopRatedMovieDTO>
}