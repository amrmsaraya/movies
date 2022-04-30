package com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.popular

import com.github.amrmsaraya.movies.database.dto.remote_key.PopularMovieRemoteKey

interface PopularMovieRemoteKeyRepo {
    suspend fun insert(remoteKeys: List<PopularMovieRemoteKey>)
    suspend fun remoteKeysId(id: Long): PopularMovieRemoteKey?
    suspend fun clearRemoteKeys()
}