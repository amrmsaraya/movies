package com.github.amrmsaraya.movies.feature_movies.domain.usecase

import androidx.paging.*
import com.github.amrmsaraya.movies.feature_movies.data.mapper.toMovie
import com.github.amrmsaraya.movies.feature_movies.data.remote_mediator.TopRatedMoviesRemoteMediator
import com.github.amrmsaraya.movies.feature_movies.domain.entity.movie.Movie
import com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.top_rated.TopRatedMovieRemoteKeyRepo
import com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.top_rated.TopRatedMovieRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class GetTopRatedMoviesUseCase @Inject constructor(
    private val topRatedMovieRepo: TopRatedMovieRepo,
    private val topRatedMovieRemoteKeyRepo: TopRatedMovieRemoteKeyRepo,
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            remoteMediator = TopRatedMoviesRemoteMediator(
                topRatedMovieRepo = topRatedMovieRepo,
                topRatedMovieRemoteKeyRepo = topRatedMovieRemoteKeyRepo
            ),
            pagingSourceFactory = { topRatedMovieRepo.getLocalMovies() }
        ).flow.map { pagingData ->
            pagingData.map { it.toMovie() }
        }
    }
}