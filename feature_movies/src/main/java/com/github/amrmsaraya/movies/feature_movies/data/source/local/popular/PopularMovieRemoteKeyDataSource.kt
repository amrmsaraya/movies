package com.github.amrmsaraya.movies.feature_movies.data.source.local.popular

import com.github.amrmsaraya.movies.database.dto.remote_key.PopularMovieRemoteKey

interface PopularMovieRemoteKeyDataSource {
    suspend fun insert(remoteKeys: List<PopularMovieRemoteKey>)
    suspend fun remoteKeysId(id: Long): PopularMovieRemoteKey?
    suspend fun clearRemoteKeys()
}