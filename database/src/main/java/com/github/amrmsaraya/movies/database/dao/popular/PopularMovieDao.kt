package com.github.amrmsaraya.movies.database.dao.popular

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.amrmsaraya.movies.database.dto.popular.PopularMovieDTO

@Dao
interface PopularMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<PopularMovieDTO>)

    @Query("DELETE FROM popular_movie")
    suspend fun clearMovies(ids: List<Long>)

    @Query("SELECT * FROM popular_movie")
    fun getMovies(): PagingSource<Int, PopularMovieDTO>
}