package com.github.amrmsaraya.movies.feature_movies.data.sourceImpl.local.top_rated

import com.github.amrmsaraya.movies.database.dao.top_rated.TopRatedMovieRemoteKeyDao
import com.github.amrmsaraya.movies.database.dto.remote_key.TopRatedMovieRemoteKey
import com.github.amrmsaraya.movies.feature_movies.data.source.local.top_rated.TopRatedMovieRemoteKeyDataSource

class TopRatedMovieRemoteKeyDataSourceImpl(
    private val topRatedMovieRemoteKeyDao: TopRatedMovieRemoteKeyDao
) : TopRatedMovieRemoteKeyDataSource {
    override suspend fun insert(remoteKeys: List<TopRatedMovieRemoteKey>) {
        topRatedMovieRemoteKeyDao.insert(remoteKeys)
    }

    override suspend fun remoteKeysId(id: Long): TopRatedMovieRemoteKey? {
        return topRatedMovieRemoteKeyDao.remoteKeysId(id)
    }

    override suspend fun clearRemoteKeys() {
        topRatedMovieRemoteKeyDao.clearRemoteKeys()
    }
}