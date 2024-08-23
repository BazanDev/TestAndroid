package com.factumex.prueba.data.repository

import com.factumex.prueba.data.datasource.local.PokemonEntity
import com.factumex.prueba.data.datasource.remote.dto.PokemonResponseDto

interface PokemonRepository {

    suspend fun getAllPokemon(inicio: Int, final: Int): PokemonResponseDto
}