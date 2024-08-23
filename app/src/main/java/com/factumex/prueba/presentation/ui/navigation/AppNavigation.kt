package com.factumex.prueba.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.factumex.prueba.presentation.ui.views.SplashScreenView

@Composable
fun AppNavigation() {

    val rememberNavController = rememberNavController()

    NavHost(
        navController = rememberNavController,
        startDestination = AppScreens.SplashScreenView.route
    ) {
        composable(AppScreens.SplashScreenView.route) {
            SplashScreenView(rememberNavController)
        }
    }
}