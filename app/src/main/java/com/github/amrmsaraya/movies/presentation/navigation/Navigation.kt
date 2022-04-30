package com.github.amrmsaraya.movies.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.amrmsaraya.movies.feature_movies.presentation.movies.MovieScreen

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Movies.route
    ) {
        composable(Screen.Movies.route) {
            MovieScreen()
        }

        composable(Screen.MovieDetails.route) {

        }
    }
}
