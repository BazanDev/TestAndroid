package com.factumex.prueba.data.datasource.remote.api

import com.factumex.prueba.data.datasource.remote.dto.PokemonResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServicePokemon {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("inicio") inicio: Int,
        @Query("fin") fin: Int
    ): Response<PokemonResponseDto>

}