package com.github.amrmsaraya.movies.common.presentation.screen

sealed class Screen(val route: String) {
    object Movies : Screen(
        route = "movies"
    )

    object MovieDetails : Screen(
        route = "movie_details"
    )
}
