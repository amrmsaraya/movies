package com.github.amrmsaraya.movies.preference.domain.usecase

import com.github.amrmsaraya.movies.preference.domain.entity.MovieSort
import com.github.amrmsaraya.movies.preference.domain.repository.PreferenceRepo
import javax.inject.Inject

class SaveMovieSortUseCase @Inject constructor(private val preferenceRepo: PreferenceRepo) {
    suspend operator fun invoke(sort: MovieSort) {
        preferenceRepo.savePreference("sort", sort.ordinal)
    }
}