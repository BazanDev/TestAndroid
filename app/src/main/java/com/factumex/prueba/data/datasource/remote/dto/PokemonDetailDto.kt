package com.factumex.prueba.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class PokemonDetailDto(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val types: List<TypeWrapper>,
    val isFavorite: Int,
)

data class Sprites(
    @SerializedName("front_default") val frontDefault: String
)

data class TypeWrapper(
    val type: Type
)

data class Type(
    val name: String
)