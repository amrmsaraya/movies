package com.github.amrmsaraya.movies.feature_movies.di

import com.github.amrmsaraya.movies.common.di.IoDispatcher
import com.github.amrmsaraya.movies.feature_movies.data.repositoryImpl.movie.popular.PopularMovieRemoteKeyRepoImpl
import com.github.amrmsaraya.movies.feature_movies.data.repositoryImpl.movie.popular.PopularMovieRepoImpl
import com.github.amrmsaraya.movies.feature_movies.data.repositoryImpl.movie.top_rated.TopRatedMovieRemoteKeyRepoImpl
import com.github.amrmsaraya.movies.feature_movies.data.repositoryImpl.movie.top_rated.TopRatedMovieRepoImpl
import com.github.amrmsaraya.movies.feature_movies.data.repositoryImpl.movie_details.MovieDetailsRepoImpl
import com.github.amrmsaraya.movies.feature_movies.data.source.local.movie_details.MovieDetailsLocalDataSource
import com.github.amrmsaraya.movies.feature_movies.data.source.local.popular.PopularMovieLocalDataSource
import com.github.amrmsaraya.movies.feature_movies.data.source.local.popular.PopularMovieRemoteKeyDataSource
import com.github.amrmsaraya.movies.feature_movies.data.source.local.top_rated.TopRatedMovieLocalDataSource
import com.github.amrmsaraya.movies.feature_movies.data.source.local.top_rated.TopRatedMovieRemoteKeyDataSource
import com.github.amrmsaraya.movies.feature_movies.data.source.remote.MovieRemoteDataSource
import com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.popular.PopularMovieRemoteKeyRepo
import com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.popular.PopularMovieRepo
import com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.top_rated.TopRatedMovieRemoteKeyRepo
import com.github.amrmsaraya.movies.feature_movies.domain.repository.movie.top_rated.TopRatedMovieRepo
import com.github.amrmsaraya.movies.feature_movies.domain.repository.movie_details.MovieDetailsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryProvider {

    @Singleton
    @Provides
    fun providePopularMovieRepo(
        popularMovieLocalDataSource: PopularMovieLocalDataSource,
        movieRemoteKeyDataSource: MovieRemoteDataSource,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): PopularMovieRepo {
        return PopularMovieRepoImpl(
            popularMovieLocalDataSource,
            movieRemoteKeyDataSource,
            dispatcher
        )
    }

    @Singleton
    @Provides
    fun providePopularMovieRemoteKeyRepo(
        popularMovieRemoteKeyDataSource: PopularMovieRemoteKeyDataSource,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): PopularMovieRemoteKeyRepo {
        return PopularMovieRemoteKeyRepoImpl(
            popularMovieRemoteKeyDataSource,
            dispatcher
        )
    }

    @Singleton
    @Provides
    fun provideTopRatedMovieRepo(
        topRatedMovieLocalDataSource: TopRatedMovieLocalDataSource,
        movieRemoteKeyDataSource: MovieRemoteDataSource,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): TopRatedMovieRepo {
        return TopRatedMovieRepoImpl(
            topRatedMovieLocalDataSource,
            movieRemoteKeyDataSource,
            dispatcher
        )
    }

    @Singleton
    @Provides
    fun provideTopRatedMovieRemoteKeyDataSource(
        remoteKeyLocalDataSource: TopRatedMovieRemoteKeyDataSource,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): TopRatedMovieRemoteKeyRepo {
        return TopRatedMovieRemoteKeyRepoImpl(
            remoteKeyLocalDataSource,
            dispatcher
        )
    }

    @Singleton
    @Provides
    fun provideMovieDetailsRepo(
        movieDetailsLocalDataSource: MovieDetailsLocalDataSource,
        movieRemoteKeyDataSource: MovieRemoteDataSource,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): MovieDetailsRepo {
        return MovieDetailsRepoImpl(
            movieDetailsLocalDataSource,
            movieRemoteKeyDataSource,
            dispatcher
        )
    }
}