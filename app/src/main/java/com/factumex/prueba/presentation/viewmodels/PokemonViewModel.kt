package com.factumex.prueba.presentation.viewmodels


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.factumex.prueba.data.datasource.local.PokemonEntity
import com.factumex.prueba.domain.usecase.GetPokemonDetailUseCase
import com.factumex.prueba.domain.usecase.PokemonUseCase
import com.factumex.prueba.domain.usecase.UpdatePokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
) : ViewModel() {

    private val _listaPokemon = MutableLiveData<List<PokemonEntity>>()
    val listaPokemon: LiveData<List<PokemonEntity>>
        get() = _listaPokemon

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    init {
        //cargarPokemon(0, 25)
    }

    fun cargarPokemon(inicio: Int, fin: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = pokemonUseCase(inicio, fin)
                val pokemonEntities = response.results.map { dto ->

                    val id = obtenerId(dto.url)

                    val detailResponse = getPokemonDetailUseCase.getPokemonDetail(id)
                    if (detailResponse.isSuccessful) {
                        val detail = detailResponse.body() ?: throw Exception("Error")

                        PokemonEntity(
                            id = detail.id,
                            name = detail.name,
                            imageUrl = detail.sprites.frontDefault,
                            height = detail.height,
                            weight = detail.weight,
                            types = detail.types.joinToString(", ") { it.type.name },
                            isFavorite = detail.isFavorite
                        )
                    } else {
                        throw Exception("Fallo cargarPokemon: ${detailResponse.code()}")
                    }
                }
                _listaPokemon.value = pokemonEntities
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun obtenerId(url: String): Int {
        return url.split("/").dropLast(1).last().toInt()
    }


    // Método para manejar la paginación
    fun cargarMasPokemon(offset: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val newPokemon = pokemonUseCase(offset, offset + 25)
                val currentList = _listaPokemon.value.orEmpty()

                // mapear okémon
                val pokemonEntities = newPokemon.results.map { dto ->
                    val id = obtenerId(dto.url)

                    // Llamada para obtener los detalles del Pokémon
                    val detailResponse = getPokemonDetailUseCase.getPokemonDetail(id)
                    if (detailResponse.isSuccessful) {
                        val detail = detailResponse.body() ?: throw Exception("Error en bdy")
                        PokemonEntity(
                            id = detail.id,
                            name = detail.name,
                            imageUrl = detail.sprites.frontDefault, // Asignar el sprite correcto
                            height = detail.height,
                            weight = detail.weight,
                            types = detail.types.joinToString(", ") { it.type.name },
                            isFavorite = detail.isFavorite
                        )
                    } else {
                        throw Exception("Fallo cargarPokemon: ${detailResponse.code()}")
                    }
                }

                // Actualizar la lista con los nuevos Pokémon
                _listaPokemon.value = currentList + pokemonEntities
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }


    /**
     * Metodo para cargar si no hay red
     */
    fun cargarDatosSinRed() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val pokemons = getPokemonDetailUseCase.getPokemonsFromDatabase()
                Log.i("pokemons", pokemons.size.toString())
                withContext(Dispatchers.Main) {  // Cambiar al hilo principal para actualizar LiveData
                    _listaPokemon.value = pokemons
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {  // Manejo de errores en el hilo principal
                    _error.value = e.message
                    _isLoading.value = false
                }
            }
        }
    }



}
