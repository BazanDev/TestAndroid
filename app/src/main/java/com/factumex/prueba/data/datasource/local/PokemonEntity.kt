package com.factumex.prueba.data.datasource.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "cat_pokemon",
    primaryKeys = ["ID"]
)
data class PokemonEntity(

    @ColumnInfo(name = "ID")
    var id: Int

)