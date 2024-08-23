package com.factumex.prueba.domain.usecase

import com.factumex.prueba.data.datasource.local.PokemonEntity
import com.factumex.prueba.data.repository.PokemonRepository
import javax.inject.Inject

class UpdatePokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(pokemon: PokemonEntity) {
        repository.updatePokemon(pokemon)
    }
}
