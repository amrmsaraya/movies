package com.github.amrmsaraya.movies.feature_movies.data.repositoryImpl.movie.top_rated

import androidx.paging.PagingSource
import com.github.amrmsaraya.movies.common.di.IoDispatcher
import com.github.amrmsaraya.movies.database.dto.top_rated.TopRatedMovieDTO
import com.github.amrmsaraya.movies.feature_movies.data.mapper.toMovie
import com.github.amrmsaraya.movies.feature_movies.data.mapper.toTopRatedMovieDTO
import com.github.amrmsaraya.movies.feature_movies.data.source.local.top_rated.TopRatedMovieLocalDataSource
import com.github.amrmsaraya.movies.feature_movies.data.source.remote.MovieRemoteDataSource
import com.github.amrmsaraya.movies.feature_movies.domain.entity.movie.Movie
import com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.top_rated.TopRatedMovieRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TopRatedMovieRepoImpl(
    private val topRatedMovieLocalDataSource: TopRatedMovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : TopRatedMovieRepo {

    override suspend fun insertAll(movies: List<Movie>) {
        withContext(dispatcher) {
            topRatedMovieLocalDataSource.insertAll(movies.map { it.toTopRatedMovieDTO() })
        }
    }

    override suspend fun clearMovies() {
        withContext(dispatcher) {
            topRatedMovieLocalDataSource.clearMovies()
        }
    }

    override fun getLocalMovies(): PagingSource<Int, TopRatedMovieDTO> {
        return topRatedMovieLocalDataSource.getMovies()
    }

    override suspend fun getRemoteMovies(page: Int): List<Movie> {
        return withContext(dispatcher) {
            movieRemoteDataSource.getTopRatedMovies(page).movies.map { it.toMovie() }
        }
    }
}