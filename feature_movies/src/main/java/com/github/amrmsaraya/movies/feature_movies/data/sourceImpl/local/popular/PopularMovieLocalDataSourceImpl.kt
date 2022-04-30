package com.github.amrmsaraya.movies.feature_movies.data.sourceImpl.local.popular

import androidx.paging.PagingSource
import com.github.amrmsaraya.movies.database.dao.popular.PopularMovieDao
import com.github.amrmsaraya.movies.database.dto.popular.PopularMovieDTO
import com.github.amrmsaraya.movies.feature_movies.data.source.local.popular.PopularMovieLocalDataSource

class PopularMovieLocalDataSourceImpl(
    private val popularMovieDao: PopularMovieDao
) : PopularMovieLocalDataSource {
    override suspend fun insertAll(movies: List<PopularMovieDTO>) {
        popularMovieDao.insertAll(movies)
    }

    override suspend fun clearMovies() {
        popularMovieDao.clearMovies()
    }

    override fun getMovies(): PagingSource<Int, PopularMovieDTO> {
        return popularMovieDao.getMovies()
    }
}