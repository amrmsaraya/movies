package com.github.amrmsaraya.movies.feature_movies.data.remote_mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.github.amrmsaraya.movies.database.dto.popular.PopularMovieDTO
import com.github.amrmsaraya.movies.database.dto.remote_key.PopularMovieRemoteKey
import com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.popular.PopularMovieRemoteKeyRepo
import com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.popular.PopularMovieRepo

@OptIn(ExperimentalPagingApi::class)
class PopularMoviesRemoteMediator(
    private val popularMovieRepo: PopularMovieRepo,
    private val popularMovieRemoteKeyRepo: PopularMovieRemoteKeyRepo
) : RemoteMediator<Int, PopularMovieDTO>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PopularMovieDTO>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }
        }

        runCatching {
            val movies = popularMovieRepo.getRemoteMovies(page)
            val endOfPaginationReached = movies.isEmpty()

            if (loadType == LoadType.REFRESH) {
                popularMovieRemoteKeyRepo.clearRemoteKeys()
                popularMovieRepo.clearMovies()
            }

            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (endOfPaginationReached) null else page + 1
            val keys = movies.map {
                PopularMovieRemoteKey(id = it.id, prevKey = prevKey, nextKey = nextKey)
            }

            popularMovieRemoteKeyRepo.insert(keys)
            popularMovieRepo.insertAll(movies)

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        }.getOrElse {
            return MediatorResult.Error(it)
        }

    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PopularMovieDTO>): PopularMovieRemoteKey? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { movie ->
            popularMovieRemoteKeyRepo.remoteKeysId(movie.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, PopularMovieDTO>): PopularMovieRemoteKey? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { movie ->
            popularMovieRemoteKeyRepo.remoteKeysId(movie.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, PopularMovieDTO>
    ): PopularMovieRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                popularMovieRemoteKeyRepo.remoteKeysId(id)
            }
        }
    }

}