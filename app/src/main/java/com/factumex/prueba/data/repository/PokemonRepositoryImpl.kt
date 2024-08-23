package com.factumex.prueba.data.repository

import android.util.Log
import com.factumex.prueba.data.datasource.local.PokemonDao
import com.factumex.prueba.data.datasource.local.PokemonEntity
import com.factumex.prueba.data.datasource.remote.api.ApiServicePokemon
import com.factumex.prueba.data.datasource.remote.dto.PokemonDetailDto
import com.factumex.prueba.data.datasource.remote.dto.PokemonResponseDto
import retrofit2.Response
import javax.inject.Inject


class PokemonRepositoryImpl @Inject constructor(
    private val apiService: ApiServicePokemon,
    private val pokemonDao: PokemonDao
) : PokemonRepository {

    override suspend fun getAllPokemon(offset: Int, limit: Int): PokemonResponseDto {
        // Hacemos la llamada al apu
        val response: Response<PokemonResponseDto> = apiService.getPokemonList(offset, limit)

        if (response.isSuccessful) {
            val pokemonResponse = response.body() ?: throw Exception("Response error")

            // Opcionalmente, persiste los datos en la base de datos local
            val pokemonEntities = pokemonResponse.results.map { dto ->
                val id = obtenerId(dto.url)
                val detailResponse = apiService.getPokemonDetail(id)

                if (detailResponse.isSuccessful) {
                    val detail = detailResponse.body() ?: throw Exception("Detalle error")

                    PokemonEntity(
                        id = detail.id,
                        name = detail.name,
                        imageUrl = detail.sprites.frontDefault,
                        height = detail.height,
                        weight = detail.weight,
                        types = detail.types.joinToString(", ") { it.type.name },
                        isFavorite = detail.isFavorite
                    )
                } else {
                    throw Exception("Failed getAllPokemon: ${detailResponse.code()}")
                }
            }

            // Guardar en base local
            pokemonDao.insertAll(pokemonEntities)

            return pokemonResponse
        } else {
            throw Exception("Fallo getAllPokemon: ${response.code()}")
        }
    }

    override suspend fun updatePokemon(pokemon: PokemonEntity) {
        pokemonDao.updatePokemon(pokemon)
    }

    override suspend fun getPokemonDetail(id: Int): Response<PokemonDetailDto> {
        return apiService.getPokemonDetail(id)
    }

    override suspend fun getPokemonsFromDatabase(): List<PokemonEntity> {
        return try {
            Log.i("getAllPokemon(2)", "Llego")
            val pokemons = pokemonDao.getAllPokemon() // Esto debe ejecutarse en un hilo de fondo
            Log.i("getAllPokemon(3)", "llego")
            pokemons
        } catch (e: Exception) {
            Log.e("getAllPokemon(4)", "Error: ${e.message}")
            emptyList()
        }
    }


    private fun obtenerId(url: String): Int {
        return url.split("/").dropLast(1).last().toInt()
    }
}
