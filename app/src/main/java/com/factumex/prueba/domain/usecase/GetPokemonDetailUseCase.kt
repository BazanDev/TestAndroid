package com.factumex.prueba.domain.usecase

import android.util.Log
import com.factumex.prueba.data.datasource.local.PokemonEntity
import com.factumex.prueba.data.datasource.remote.dto.PokemonDetailDto
import com.factumex.prueba.data.repository.PokemonRepository
import retrofit2.Response
import javax.inject.Inject


class GetPokemonDetailUseCase @Inject constructor(
    private val repository: PokemonRepository
) {


    // Nuevo método para obtener los detalles de un Pokémon
    suspend fun getPokemonDetail(id: Int): Response<PokemonDetailDto> {
        return repository.getPokemonDetail(id)
    }


    suspend fun getPokemonsFromDatabase(): List<PokemonEntity> {
        Log.i("Llego 1 ", "getPokemonsFromDatabase")
        return repository.getPokemonsFromDatabase()
    }

}
