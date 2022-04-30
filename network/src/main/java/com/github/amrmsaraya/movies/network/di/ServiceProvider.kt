package com.github.amrmsaraya.movies.network.di

import com.github.amrmsaraya.movies.network.service.MovieService
import com.github.amrmsaraya.movies.network.serviceImpl.MovieServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceProvider {

    @Singleton
    @Provides
    fun provideMovieService(client: HttpClient): MovieService {
        return MovieServiceImpl(client)
    }
}