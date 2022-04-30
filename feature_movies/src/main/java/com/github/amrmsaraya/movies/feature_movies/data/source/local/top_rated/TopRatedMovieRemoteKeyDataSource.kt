package com.github.amrmsaraya.movies.feature_movies.data.source.local.top_rated

import com.github.amrmsaraya.movies.database.dto.remote_key.TopRatedMovieRemoteKey

interface TopRatedMovieRemoteKeyDataSource {
    suspend fun insert(remoteKeys: List<TopRatedMovieRemoteKey>)
    suspend fun remoteKeysId(id: Long): TopRatedMovieRemoteKey?
    suspend fun clearRemoteKeys()
}