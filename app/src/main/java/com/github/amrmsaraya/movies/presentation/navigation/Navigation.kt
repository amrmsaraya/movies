package com.github.amrmsaraya.movies.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.amrmsaraya.movies.feature_movies.presentation.movie_details.MovieDetailsScreen
import com.github.amrmsaraya.movies.feature_movies.presentation.movies.MovieScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Movies.route
    ) {
        composable(Screen.Movies.route) {
            MovieScreen(
                onNavigateToMovieDetails = { id ->
                    navController.navigate("${Screen.MovieDetails.route}/$id") {
                        popUpTo(Screen.Movies.route)
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = "${Screen.MovieDetails.route}/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.LongType },
            )
        ) {
            MovieDetailsScreen(
                id = it.arguments?.getLong("id") ?: 1,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
