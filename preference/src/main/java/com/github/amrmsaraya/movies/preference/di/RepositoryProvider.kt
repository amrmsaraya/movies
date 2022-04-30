package com.github.amrmsaraya.movies.preference.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.github.amrmsaraya.movies.preference.data.PreferenceRepoImpl
import com.github.amrmsaraya.movies.preference.domain.repository.PreferenceRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryProvider {
    @Singleton
    @Provides
    fun providePreferenceRepo(dataStore: DataStore<Preferences>): PreferenceRepo {
        return PreferenceRepoImpl(dataStore)
    }
}
