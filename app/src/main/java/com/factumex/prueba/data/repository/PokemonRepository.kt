package com.factumex.prueba.data.repository

import com.factumex.prueba.data.datasource.local.PokemonEntity
import com.factumex.prueba.data.datasource.remote.dto.PokemonDetailDto
import com.factumex.prueba.data.datasource.remote.dto.PokemonResponseDto
import retrofit2.Response

interface PokemonRepository {

    suspend fun getAllPokemon(inicio: Int, final: Int): PokemonResponseDto
    suspend fun updatePokemon(pokemon: PokemonEntity)
    suspend fun getPokemonDetail(id: Int): Response<PokemonDetailDto>

    suspend fun getPokemonsFromDatabase(): List<PokemonEntity>
}