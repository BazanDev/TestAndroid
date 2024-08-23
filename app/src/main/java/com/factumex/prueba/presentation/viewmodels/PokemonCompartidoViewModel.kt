package com.factumex.prueba.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.factumex.prueba.data.datasource.local.PokemonEntity
import com.factumex.prueba.domain.usecase.UpdatePokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PokemonCompartidoViewModel @Inject constructor(
    private val updatePokemonUseCase: UpdatePokemonUseCase,
) : ViewModel() {


    private val _listaPokemon = MutableLiveData<List<PokemonEntity>>()
    val listaPokemon: LiveData<List<PokemonEntity>>
        get() = _listaPokemon

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    private val _selectedPokemon = MutableLiveData<PokemonEntity>()
    val selectedPokemon: LiveData<PokemonEntity>
        get() = _selectedPokemon

    fun selectPokemon(pokemon: PokemonEntity) {
        Log.d("SharedViewModel", "Pokemon seleccionado: ${pokemon.name}")
        _selectedPokemon.value = pokemon
    }


    fun toggleFavorite(pokemon: PokemonEntity) {
        Log.i("toggleFavorite", "toggleFavorite called for Pokémon ID: ${pokemon.id}, current isFavorite: ${pokemon.isFavorite}")

        viewModelScope.launch {
            try {
                // Invertir el estado de isFavorite

                var valor = pokemon.isFavorite
                if (pokemon.isFavorite == 1) {
                    valor = 0
                } else {
                    valor = 1
                }

                val updatedPokemon = pokemon.copy(isFavorite = valor)
                Log.i("toggleFavorite", "Pokemon ID: ${updatedPokemon.id} toggled to isFavorite: ${updatedPokemon.isFavorite}")

                // Actualizar en la base de datos u otra fuente de datos
                updatePokemonUseCase(updatedPokemon)

                // Actualizar la lista en memoria
                _listaPokemon.value = _listaPokemon.value?.map {
                    if (it.id == pokemon.id) updatedPokemon else it
                }

                _selectedPokemon.value = updatedPokemon

                // Verificar que la lista se actualizó correctamente
                _listaPokemon.value?.forEach {
                    Log.i("toggleFavorite", "Pokémon ID: ${it.id}, isFavorite: ${it.isFavorite}")
                }

            } catch (e: Exception) {
                _error.value = e.message
                Log.e("toggleFavorite", "Error updating favorite: ${e.message}")
            }
        }
    }


}