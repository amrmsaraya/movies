package com.github.amrmsaraya.movies.database.di

import android.content.Context
import com.github.amrmsaraya.movies.database.dao.movie_details.MovieDetailsDao
import com.github.amrmsaraya.movies.database.dao.popular.PopularMovieDao
import com.github.amrmsaraya.movies.database.dao.popular.PopularMovieRemoteKeyDao
import com.github.amrmsaraya.movies.database.dao.top_rated.TopRatedMovieDao
import com.github.amrmsaraya.movies.database.dao.top_rated.TopRatedMovieRemoteKeyDao
import com.github.amrmsaraya.movies.database.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DAOProvider {

    @Singleton
    @Provides
    fun provideMovieDetailsDao(@ApplicationContext context: Context): MovieDetailsDao {
        return AppDatabase.getInstance(context).movieDetailsDao()
    }

    @Singleton
    @Provides
    fun providePopularMovieDao(@ApplicationContext context: Context): PopularMovieDao {
        return AppDatabase.getInstance(context).popularMovieDao()
    }

    @Singleton
    @Provides
    fun provideTopRatedMovieDao(@ApplicationContext context: Context): TopRatedMovieDao {
        return AppDatabase.getInstance(context).topRatedMovieDao()
    }

    @Singleton
    @Provides
    fun providePopularMovieRemoteKeyDao(@ApplicationContext context: Context): PopularMovieRemoteKeyDao {
        return AppDatabase.getInstance(context).popularMovieRemoteKeyDao()
    }

    @Singleton
    @Provides
    fun provideTopRatedMovieRemoteKeyDao(@ApplicationContext context: Context): TopRatedMovieRemoteKeyDao {
        return AppDatabase.getInstance(context).topRatedMovieRemoteKeyDao()
    }
}