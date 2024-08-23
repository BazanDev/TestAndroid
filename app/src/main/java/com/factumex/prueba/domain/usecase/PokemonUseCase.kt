package com.factumex.prueba.domain.usecase

import com.factumex.prueba.data.datasource.remote.dto.PokemonResponseDto
import com.factumex.prueba.data.repository.PokemonRepository
import javax.inject.Inject


class PokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(inicio: Int, final: Int): PokemonResponseDto {
        return repository.getAllPokemon(inicio, final)
    }
}
