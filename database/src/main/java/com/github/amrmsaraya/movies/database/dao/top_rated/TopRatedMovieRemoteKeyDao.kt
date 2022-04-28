package com.github.amrmsaraya.movies.database.dao.top_rated

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.amrmsaraya.movies.database.dto.remote_key.TopRatedMovieRemoteKey

@Dao
interface TopRatedMovieRemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteKeys: List<TopRatedMovieRemoteKey>)

    @Query("SELECT * FROM top_rated_movie_remote_keys WHERE id = :id")
    suspend fun remoteKeysId(id: Long): TopRatedMovieRemoteKey?

    @Query("DELETE FROM top_rated_movie_remote_keys")
    suspend fun clearRemoteKeys()
}