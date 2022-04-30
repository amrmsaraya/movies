package com.github.amrmsaraya.movies.feature_movies.data.repositoryImpl.movie.top_rated

import com.github.amrmsaraya.movies.common.di.IoDispatcher
import com.github.amrmsaraya.movies.database.dto.remote_key.TopRatedMovieRemoteKey
import com.github.amrmsaraya.movies.feature_movies.data.source.local.top_rated.TopRatedMovieRemoteKeyDataSource
import com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.top_rated.TopRatedMovieRemoteKeyRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TopRatedMovieRemoteKeyRepoImpl(
    private val topRatedMovieRemoteKeyDataSource: TopRatedMovieRemoteKeyDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : TopRatedMovieRemoteKeyRepo {
    override suspend fun insert(remoteKeys: List<TopRatedMovieRemoteKey>) {
        withContext(dispatcher) {
            topRatedMovieRemoteKeyDataSource.insert(remoteKeys)
        }
    }

    override suspend fun remoteKeysId(id: Long): TopRatedMovieRemoteKey? {
        return withContext(dispatcher) {
            topRatedMovieRemoteKeyDataSource.remoteKeysId(id)
        }
    }

    override suspend fun clearRemoteKeys() {
        withContext(dispatcher) {
            topRatedMovieRemoteKeyDataSource.clearRemoteKeys()
        }
    }
}