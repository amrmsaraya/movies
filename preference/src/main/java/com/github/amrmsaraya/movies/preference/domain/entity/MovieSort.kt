package com.github.amrmsaraya.movies.preference.domain.entity

import androidx.annotation.StringRes
import com.github.amrmsaraya.movies.preference.R

enum class MovieSort(@StringRes val stringRes: Int) {
    POPULAR(R.string.popular_movies),
    TOP_RATED(R.string.top_rated_movies)
}