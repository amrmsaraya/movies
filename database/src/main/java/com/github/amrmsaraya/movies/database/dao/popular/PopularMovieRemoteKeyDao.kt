package com.github.amrmsaraya.movies.database.dao.popular

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.amrmsaraya.movies.database.dto.remote_key.PopularMovieRemoteKey

@Dao
interface PopularMovieRemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteKeys: List<PopularMovieRemoteKey>)

    @Query("SELECT * FROM popular_movie_remote_keys WHERE id = :id")
    suspend fun remoteKeysId(id: Long): PopularMovieRemoteKey?

    @Query("DELETE FROM popular_movie_remote_keys")
    suspend fun clearRemoteKeys()
}