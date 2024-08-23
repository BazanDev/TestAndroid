package com.factumex.prueba.data.repository

import com.factumex.prueba.data.datasource.local.PokemonEntity
import com.factumex.prueba.data.datasource.remote.api.ApiServicePokemon
import com.factumex.prueba.data.datasource.remote.dto.PokemonResponseDto
import javax.inject.Inject


class PokemonRepositoryImpl @Inject constructor(
    private val apiServicePokemon: ApiServicePokemon
) : PokemonRepository {

    override suspend fun getAllPokemon(offset: Int, limit: Int): PokemonResponseDto {
        val response = apiServicePokemon.getPokemonList(offset, limit)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("No data received")
        } else {
            // Manejar error según el código de estado
            throw Exception("Error fetching Pokémon: ${response.code()}")
        }
    }


}