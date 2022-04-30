package com.github.amrmsaraya.movies.feature_movies.data.sourceImpl.local.top_rated

import androidx.paging.PagingSource
import com.github.amrmsaraya.movies.database.dao.top_rated.TopRatedMovieDao
import com.github.amrmsaraya.movies.database.dto.top_rated.TopRatedMovieDTO
import com.github.amrmsaraya.movies.feature_movies.data.source.local.top_rated.TopRatedMovieLocalDataSource

class TopRatedMovieLocalDataSourceImpl(
    private val topRatedMovieDao: TopRatedMovieDao
) : TopRatedMovieLocalDataSource {
    override suspend fun insertAll(movies: List<TopRatedMovieDTO>) {
        topRatedMovieDao.insertAll(movies)
    }

    override suspend fun clearMovies() {
        topRatedMovieDao.clearMovies()
    }

    override fun getMovies(): PagingSource<Int, TopRatedMovieDTO> {
        return topRatedMovieDao.getMovies()
    }
}