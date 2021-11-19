package it.pavanluca.pokemonocean.domain.pokemon.use_case

import it.pavanluca.pokemonocean.domain.pokemon.models.Pokemon
import it.pavanluca.pokemonocean.domain.pokemon.repository.Repository
import javax.inject.Inject

/**
 * Created by Luca Pavan on 19/11/21
 */
class GetPokemonUseCase @Inject constructor(
    private val repo: Repository
) {

    suspend operator fun invoke(name: String) : Pokemon {
        return repo.getPokemon(name)
    }
}