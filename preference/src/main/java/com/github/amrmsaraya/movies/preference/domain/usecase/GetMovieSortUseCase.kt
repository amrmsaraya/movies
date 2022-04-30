package com.github.amrmsaraya.movies.preference.domain.usecase

import com.github.amrmsaraya.movies.preference.domain.entity.MovieSort
import com.github.amrmsaraya.movies.preference.domain.repository.PreferenceRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMovieSortUseCase @Inject constructor(private val preferenceRepo: PreferenceRepo) {
    suspend operator fun invoke(): Flow<MovieSort> {
        return preferenceRepo.getIntPreference("sort").map { result ->
            MovieSort.values().firstOrNull { it.ordinal == result } ?: MovieSort.POPULAR
        }
    }
}