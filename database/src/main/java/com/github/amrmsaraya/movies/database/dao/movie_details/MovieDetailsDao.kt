package com.github.amrmsaraya.movies.database.dao.movie_details

import androidx.room.*
import com.github.amrmsaraya.movies.database.dto.movie_details.MovieDetailsDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieDetailsDTO)

    @Query("SELECT * FROM movie_details WHERE id = :id")
    suspend fun getMovieDetails(id: Long): MovieDetailsDTO
}