package com.factumex.prueba.data.datasource.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PokemonDao {

    @Query("select * from cat_pokemon")
    fun getAllPokemon(): List<PokemonEntity>
}