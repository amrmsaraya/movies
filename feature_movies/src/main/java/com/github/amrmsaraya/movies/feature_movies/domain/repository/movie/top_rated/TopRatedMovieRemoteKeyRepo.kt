package com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.top_rated

import com.github.amrmsaraya.movies.database.dto.remote_key.TopRatedMovieRemoteKey

interface TopRatedMovieRemoteKeyRepo {
    suspend fun insert(remoteKeys: List<TopRatedMovieRemoteKey>)
    suspend fun remoteKeysId(id: Long): TopRatedMovieRemoteKey?
    suspend fun clearRemoteKeys()
}