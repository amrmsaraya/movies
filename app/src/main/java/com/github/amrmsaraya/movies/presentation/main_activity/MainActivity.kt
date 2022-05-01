package com.github.amrmsaraya.movies.presentation.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.github.amrmsaraya.movies.common.presentation.theme.MoviesTheme
import com.github.amrmsaraya.movies.presentation.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()

        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    MoviesTheme() {
        Surface(modifier = Modifier.fillMaxSize()) {
            val navController = rememberNavController()
            Navigation(navController = navController)
        }
    }
}
