package com.github.amrmsaraya.movies.feature_movies.data.repositoryImpl.movie.popular

import com.github.amrmsaraya.movies.common.di.IoDispatcher
import com.github.amrmsaraya.movies.database.dto.remote_key.PopularMovieRemoteKey
import com.github.amrmsaraya.movies.feature_movies.data.source.local.popular.PopularMovieRemoteKeyDataSource
import com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.popular.PopularMovieRemoteKeyRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PopularMovieRemoteKeyRepoImpl(
    private val popularMovieRemoteKeyDataSource: PopularMovieRemoteKeyDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : PopularMovieRemoteKeyRepo {
    override suspend fun insert(remoteKeys: List<PopularMovieRemoteKey>) {
        withContext(dispatcher) {
            popularMovieRemoteKeyDataSource.insert(remoteKeys)
        }
    }

    override suspend fun remoteKeysId(id: Long): PopularMovieRemoteKey? {
        return withContext(dispatcher) {
            popularMovieRemoteKeyDataSource.remoteKeysId(id)
        }
    }

    override suspend fun clearRemoteKeys() {
        withContext(dispatcher) {
            popularMovieRemoteKeyDataSource.clearRemoteKeys()
        }
    }

}