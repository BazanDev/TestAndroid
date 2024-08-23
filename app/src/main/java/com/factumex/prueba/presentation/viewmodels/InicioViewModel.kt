package com.factumex.prueba.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InicioViewModel @Inject constructor() : ViewModel() {


    fun navegarPokedex(navHostController: NavHostController) {
        navHostController.navigate("Pokemon")
    }

    fun navegarComponente(navHostController: NavHostController) {
        navHostController.navigate("ActividadUno")
    }

}