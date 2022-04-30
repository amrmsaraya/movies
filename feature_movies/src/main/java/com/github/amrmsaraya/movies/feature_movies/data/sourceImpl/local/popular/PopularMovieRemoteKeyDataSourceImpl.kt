package com.github.amrmsaraya.movies.feature_movies.data.sourceImpl.local.popular

import com.github.amrmsaraya.movies.database.dao.popular.PopularMovieRemoteKeyDao
import com.github.amrmsaraya.movies.database.dto.remote_key.PopularMovieRemoteKey
import com.github.amrmsaraya.movies.feature_movies.data.source.local.popular.PopularMovieRemoteKeyDataSource

class PopularMovieRemoteKeyDataSourceImpl(
    private val popularMovieRemoteKeyDao: PopularMovieRemoteKeyDao
) : PopularMovieRemoteKeyDataSource {
    override suspend fun insert(remoteKeys: List<PopularMovieRemoteKey>) {
        popularMovieRemoteKeyDao.insert(remoteKeys)
    }

    override suspend fun remoteKeysId(id: Long): PopularMovieRemoteKey? {
        return popularMovieRemoteKeyDao.remoteKeysId(id)
    }

    override suspend fun clearRemoteKeys() {
        popularMovieRemoteKeyDao.clearRemoteKeys()
    }
}