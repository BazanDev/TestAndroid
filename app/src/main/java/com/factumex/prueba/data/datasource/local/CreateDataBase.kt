package com.factumex.prueba.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        PokemonEntity::class
    ], version = 1, exportSchema = false
)

abstract class CreateDataBase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}