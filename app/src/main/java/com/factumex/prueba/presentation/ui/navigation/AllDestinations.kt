package com.factumex.prueba.presentation.ui.navigation


import androidx.navigation.NavHostController

import com.factumex.prueba.presentation.ui.navigation.AllDestinations.Inicio
import com.factumex.prueba.presentation.ui.navigation.AllDestinations.ActividadUno
import com.factumex.prueba.presentation.ui.navigation.AllDestinations.Pokemon

object AllDestinations {

    const val Inicio = "Inicio"
    const val ActividadUno = "ActividadUno"
    const val Pokemon = "Pokemon"
    const val PokemonDetalle = "PokemonDetalle"
}


class AppNavigationActions(private val navHostController: NavHostController) {


    fun navigateToInicio() {
        navHostController.navigate(Inicio) {
            popUpTo(Inicio)
        }
    }

    fun navigateToActividadUno() {
        navHostController.navigate(ActividadUno) {
            popUpTo(ActividadUno)
        }
    }

    fun navigateToPokemon() {
        navHostController.navigate(Pokemon) {
            popUpTo(Pokemon)
        }
    }


}