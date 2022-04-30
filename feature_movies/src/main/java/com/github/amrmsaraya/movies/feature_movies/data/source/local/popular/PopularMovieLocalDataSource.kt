package com.github.amrmsaraya.movies.feature_movies.data.source.local.popular

import androidx.paging.PagingSource
import com.github.amrmsaraya.movies.database.dto.popular.PopularMovieDTO

interface PopularMovieLocalDataSource {
    suspend fun insertAll(movies: List<PopularMovieDTO>)
    suspend fun clearMovies()
    fun getMovies(): PagingSource<Int, PopularMovieDTO>
}