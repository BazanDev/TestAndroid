package com.factumex.prueba.presentation.ui.navigation


import androidx.navigation.NavHostController
import com.factumex.prueba.presentation.ui.navigation.AllDestinations.ActividadUno
import com.factumex.prueba.presentation.ui.navigation.AllDestinations.ActividadDos
import com.factumex.prueba.presentation.ui.navigation.AllDestinations.ActividadTres
import com.factumex.prueba.presentation.ui.navigation.AllDestinations.Pokemon

object AllDestinations {

    const val ActividadUno = "ActividadUno"
    const val ActividadDos = "ActividadDos"
    const val ActividadTres = "ActividadTres"
    const val Pokemon = "Pokemon"
}


class AppNavigationActions(private val navHostController: NavHostController) {

    fun navigateToActividadUno() {
        navHostController.navigate(ActividadUno) {
            popUpTo(ActividadUno)
        }
    }

    fun navigateToActividadDos() {
        navHostController.navigate(ActividadDos) {
            popUpTo(ActividadDos)
        }
    }

    fun navigateToActividadTres() {
        navHostController.navigate(ActividadTres) {
            popUpTo(ActividadTres)
        }
    }

    fun navigateToPokemon() {
        navHostController.navigate(Pokemon) {
            popUpTo(Pokemon)
        }
    }

}