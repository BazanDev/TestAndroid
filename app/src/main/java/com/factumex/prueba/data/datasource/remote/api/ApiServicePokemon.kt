package com.factumex.prueba.data.datasource.remote.api

import com.factumex.prueba.data.datasource.remote.dto.PokemonDetailDto
import com.factumex.prueba.data.datasource.remote.dto.PokemonResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServicePokemon {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PokemonResponseDto>


    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: Int): Response<PokemonDetailDto>

}