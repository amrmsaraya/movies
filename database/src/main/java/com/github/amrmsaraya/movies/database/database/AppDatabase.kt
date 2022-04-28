package com.github.amrmsaraya.movies.database.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.amrmsaraya.movies.database.converter.Converter
import com.github.amrmsaraya.movies.database.dao.movie_details.MovieDetailsDao
import com.github.amrmsaraya.movies.database.dao.popular.PopularMovieDao
import com.github.amrmsaraya.movies.database.dao.popular.PopularMovieRemoteKeyDao
import com.github.amrmsaraya.movies.database.dao.top_rated.TopRatedMovieDao
import com.github.amrmsaraya.movies.database.dao.top_rated.TopRatedMovieRemoteKeyDao
import com.github.amrmsaraya.movies.database.dto.movie_details.MovieDetailsDTO
import com.github.amrmsaraya.movies.database.dto.popular.PopularMovieDTO
import com.github.amrmsaraya.movies.database.dto.remote_key.PopularMovieRemoteKey
import com.github.amrmsaraya.movies.database.dto.remote_key.TopRatedMovieRemoteKey
import com.github.amrmsaraya.movies.database.dto.top_rated.TopRatedMovieDTO

@Database(
    entities = [
        PopularMovieDTO::class, TopRatedMovieDTO::class, MovieDetailsDTO::class,
        PopularMovieRemoteKey::class, TopRatedMovieRemoteKey::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDetailsDao(): MovieDetailsDao
    abstract fun popularMovieDao(): PopularMovieDao
    abstract fun topRatedMovieDao(): TopRatedMovieDao
    abstract fun popularMovieRemoteKeyDao(): PopularMovieRemoteKeyDao
    abstract fun topRatedMovieRemoteKeyDao(): TopRatedMovieRemoteKeyDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}