package com.github.amrmsaraya.movies.feature_movies.data.repositoryImpl.movie.popular

import androidx.paging.PagingSource
import com.github.amrmsaraya.movies.common.di.IoDispatcher
import com.github.amrmsaraya.movies.database.dto.popular.PopularMovieDTO
import com.github.amrmsaraya.movies.feature_movies.data.mapper.toMovie
import com.github.amrmsaraya.movies.feature_movies.data.mapper.toPopularMovieDTO
import com.github.amrmsaraya.movies.feature_movies.data.source.local.popular.PopularMovieLocalDataSource
import com.github.amrmsaraya.movies.feature_movies.data.source.remote.MovieRemoteDataSource
import com.github.amrmsaraya.movies.feature_movies.domain.entity.movie.Movie
import com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.popular.PopularMovieRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PopularMovieRepoImpl(
    private val popularMovieLocalDataSource: PopularMovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : PopularMovieRepo {


    override suspend fun insertAll(movies: List<Movie>) {
        withContext(dispatcher) {
            popularMovieLocalDataSource.insertAll(movies.map { it.toPopularMovieDTO() })
        }
    }

    override suspend fun clearMovies() {
        withContext(dispatcher) {
            popularMovieLocalDataSource.clearMovies()
        }
    }

    override fun getLocalMovies(): PagingSource<Int, PopularMovieDTO> {
        return popularMovieLocalDataSource.getMovies()
    }

    override suspend fun getRemoteMovies(page: Int): List<Movie> {
        return withContext(dispatcher) {
            movieRemoteDataSource.getPopularMovies(page).movies.map { it.toMovie() }
        }
    }
}