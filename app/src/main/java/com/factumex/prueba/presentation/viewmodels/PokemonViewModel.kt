package com.factumex.prueba.presentation.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.factumex.prueba.data.datasource.remote.dto.PokemonResponseDto
import com.factumex.prueba.domain.usecase.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val pokemonUseCase: PokemonUseCase
) : ViewModel() {


    private val _listaPokemon = MutableLiveData<PokemonResponseDto>()
    val listaPokemon: LiveData<PokemonResponseDto>
        get() = _listaPokemon

    init {
        cargarPokemon(1,50)
    }

    fun cargarPokemon(inicio: Int, fin: Int) {
        viewModelScope.launch {
            try {
                val response = pokemonUseCase(inicio, fin)
                response.results.forEach{ it ->
                    Log.i("Log", "response: " + it.name)
                }
                _listaPokemon.value = response
            } catch (e: Exception) {
                // Manejar error, mostrar mensaje al usuario, etc.
            }
        }
    }
}