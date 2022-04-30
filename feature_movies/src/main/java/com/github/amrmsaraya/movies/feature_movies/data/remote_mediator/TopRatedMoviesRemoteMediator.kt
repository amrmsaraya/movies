package com.github.amrmsaraya.movies.feature_movies.data.remote_mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.github.amrmsaraya.movies.database.dto.remote_key.TopRatedMovieRemoteKey
import com.github.amrmsaraya.movies.database.dto.top_rated.TopRatedMovieDTO
import com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.top_rated.TopRatedMovieRemoteKeyRepo
import com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.top_rated.TopRatedMovieRepo

@OptIn(ExperimentalPagingApi::class)
class TopRatedMoviesRemoteMediator(
    private val topRatedMovieRepo: TopRatedMovieRepo,
    private val topRatedMovieRemoteKeyRepo: TopRatedMovieRemoteKeyRepo
) : RemoteMediator<Int, TopRatedMovieDTO>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TopRatedMovieDTO>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null
                )
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey ?: return MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null
                )
                nextKey
            }
        }

        runCatching {
            val movies = topRatedMovieRepo.getRemoteMovies(page)
            val endOfPaginationReached = movies.isEmpty()

            if (loadType == LoadType.REFRESH) {
                topRatedMovieRemoteKeyRepo.clearRemoteKeys()
                topRatedMovieRepo.clearMovies()
            }

            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (endOfPaginationReached) null else page + 1
            val keys = movies.map {
                TopRatedMovieRemoteKey(id = it.id, prevKey = prevKey, nextKey = nextKey)
            }

            topRatedMovieRemoteKeyRepo.insert(keys)
            topRatedMovieRepo.insertAll(movies)

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        }.getOrElse {
            return MediatorResult.Error(it)
        }

    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, TopRatedMovieDTO>): TopRatedMovieRemoteKey? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { movie ->
            topRatedMovieRemoteKeyRepo.remoteKeysId(movie.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, TopRatedMovieDTO>): TopRatedMovieRemoteKey? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { movie ->
            topRatedMovieRemoteKeyRepo.remoteKeysId(movie.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, TopRatedMovieDTO>
    ): TopRatedMovieRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                topRatedMovieRemoteKeyRepo.remoteKeysId(id)
            }
        }
    }

}