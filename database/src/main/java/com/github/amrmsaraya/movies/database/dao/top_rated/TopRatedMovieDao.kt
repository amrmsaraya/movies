package com.github.amrmsaraya.movies.database.dao.top_rated

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.amrmsaraya.movies.database.dto.top_rated.TopRatedMovieDTO

@Dao
interface TopRatedMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<TopRatedMovieDTO>)

    @Query("DELETE FROM top_rated_movie")
    suspend fun clearMovies(ids: List<Long>)

    @Query("SELECT * FROM top_rated_movie")
    fun getMovies(): PagingSource<Int, TopRatedMovieDTO>

}