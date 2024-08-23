package com.factumex.prueba.presentation.ui.navigation

sealed class AppScreens(val route: String) {
    object ActividadUnoView : AppScreens("ActividadUnoView")
    object ActividadDosView : AppScreens("ActividadDosView")
    object ActividadTresView : AppScreens("ActividadTresView")
    object SplashScreenView : AppScreens("SplashScreenView")
    object PokemonView : AppScreens("PokemonView")


}