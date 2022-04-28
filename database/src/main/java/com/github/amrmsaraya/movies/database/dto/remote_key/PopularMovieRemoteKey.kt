package com.github.amrmsaraya.movies.database.dto.remote_key

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_movie_remote_keys")
data class PopularMovieRemoteKey(
    @PrimaryKey
    val id: Long,
    val prevKey: Int?,
    val NextKey: Int?
)
