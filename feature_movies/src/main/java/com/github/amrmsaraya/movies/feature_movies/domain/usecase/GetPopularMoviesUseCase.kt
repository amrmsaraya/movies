package com.github.amrmsaraya.movies.feature_movies.domain.usecase

import androidx.paging.*
import com.github.amrmsaraya.movies.feature_movies.data.mapper.toMovie
import com.github.amrmsaraya.movies.feature_movies.data.remote_mediator.PopularMoviesRemoteMediator
import com.github.amrmsaraya.movies.feature_movies.domain.entity.movie.Movie
import com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.popular.PopularMovieRemoteKeyRepo
import com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.popular.PopularMovieRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class GetPopularMoviesUseCase @Inject constructor(
    private val popularMovieRepo: PopularMovieRepo,
    private val popularMovieRemoteKeyRepo: PopularMovieRemoteKeyRepo,
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            remoteMediator = PopularMoviesRemoteMediator(
                popularMovieRepo = popularMovieRepo,
                popularMovieRemoteKeyRepo = popularMovieRemoteKeyRepo
            ),
            pagingSourceFactory = { popularMovieRepo.getLocalMovies() }
        ).flow.map { pagingData ->
            pagingData.map { it.toMovie() }
        }
    }
}