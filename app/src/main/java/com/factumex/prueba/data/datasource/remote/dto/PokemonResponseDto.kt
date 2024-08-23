package com.factumex.prueba.data.datasource.remote.dto

class PokemonResponseDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonDto>
)