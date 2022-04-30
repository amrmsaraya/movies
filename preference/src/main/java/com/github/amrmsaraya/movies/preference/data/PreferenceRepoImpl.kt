package com.github.amrmsaraya.movies.preference.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.github.amrmsaraya.movies.preference.domain.repository.PreferenceRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PreferenceRepoImpl(
    private val dataStore: DataStore<Preferences>,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PreferenceRepo {
    override suspend fun savePreference(key: String, value: Int) {
        withContext(dispatcher) {
            val preferencesKey = intPreferencesKey(key)
            dataStore.edit { preferences ->
                preferences[preferencesKey] = value
            }
        }
    }

    override suspend fun getIntPreference(key: String): Flow<Int> {
        return withContext(dispatcher) {
            val preferencesKey = intPreferencesKey(key)
            dataStore.data.map { preferences ->
                preferences[preferencesKey] ?: 0
            }
        }
    }
}