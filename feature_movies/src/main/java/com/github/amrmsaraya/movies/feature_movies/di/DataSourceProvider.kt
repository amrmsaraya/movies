package com.github.amrmsaraya.movies.feature_movies.di

import com.github.amrmsaraya.movies.database.dao.movie_details.MovieDetailsDao
import com.github.amrmsaraya.movies.database.dao.popular.PopularMovieDao
import com.github.amrmsaraya.movies.database.dao.popular.PopularMovieRemoteKeyDao
import com.github.amrmsaraya.movies.database.dao.top_rated.TopRatedMovieDao
import com.github.amrmsaraya.movies.database.dao.top_rated.TopRatedMovieRemoteKeyDao
import com.github.amrmsaraya.movies.feature_movies.data.source.local.movie_details.MovieDetailsLocalDataSource
import com.github.amrmsaraya.movies.feature_movies.data.source.local.popular.PopularMovieLocalDataSource
import com.github.amrmsaraya.movies.feature_movies.data.source.local.popular.PopularMovieRemoteKeyDataSource
import com.github.amrmsaraya.movies.feature_movies.data.source.local.top_rated.TopRatedMovieLocalDataSource
import com.github.amrmsaraya.movies.feature_movies.data.source.local.top_rated.TopRatedMovieRemoteKeyDataSource
import com.github.amrmsaraya.movies.feature_movies.data.source.remote.MovieRemoteDataSource
import com.github.amrmsaraya.movies.feature_movies.data.sourceImpl.local.movie_details.MovieDetailsLocalDataSourceImpl
import com.github.amrmsaraya.movies.feature_movies.data.sourceImpl.local.popular.PopularMovieLocalDataSourceImpl
import com.github.amrmsaraya.movies.feature_movies.data.sourceImpl.local.popular.PopularMovieRemoteKeyDataSourceImpl
import com.github.amrmsaraya.movies.feature_movies.data.sourceImpl.local.top_rated.TopRatedMovieLocalDataSourceImpl
import com.github.amrmsaraya.movies.feature_movies.data.sourceImpl.local.top_rated.TopRatedMovieRemoteKeyDataSourceImpl
import com.github.amrmsaraya.movies.feature_movies.data.sourceImpl.remote.MovieRemoteDataSourceImpl
import com.github.amrmsaraya.movies.network.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceProvider {

    @Singleton
    @Provides
    fun providePopularMovieLocalDataSource(popularMovieDao: PopularMovieDao): PopularMovieLocalDataSource {
        return PopularMovieLocalDataSourceImpl(popularMovieDao)
    }

    @Singleton
    @Provides
    fun providePopularMovieRemoteKeyDataSource(remoteKeyDao: PopularMovieRemoteKeyDao): PopularMovieRemoteKeyDataSource {
        return PopularMovieRemoteKeyDataSourceImpl(remoteKeyDao)
    }

    @Singleton
    @Provides
    fun provideTopRatedMovieLocalDataSource(topRatedMovieDao: TopRatedMovieDao): TopRatedMovieLocalDataSource {
        return TopRatedMovieLocalDataSourceImpl(topRatedMovieDao)
    }

    @Singleton
    @Provides
    fun provideTopRatedMovieRemoteKeyDataSource(remoteKeyDao: TopRatedMovieRemoteKeyDao): TopRatedMovieRemoteKeyDataSource {
        return TopRatedMovieRemoteKeyDataSourceImpl(remoteKeyDao)
    }

    @Singleton
    @Provides
    fun provideMovieDetailsLocalDataSource(movieDetailsDao: MovieDetailsDao): MovieDetailsLocalDataSource {
        return MovieDetailsLocalDataSourceImpl(movieDetailsDao)
    }

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(movieService: MovieService): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(movieService)
    }
}