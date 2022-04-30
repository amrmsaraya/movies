package com.github.amrmsaraya.movies.preference.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferenceRepo {
    suspend fun savePreference(key: String, value: Int)
    suspend fun getIntPreference(key: String): Flow<Int>
}