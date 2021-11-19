package it.pavanluca.pokemonocean.domain.pokemon.use_case

import it.pavanluca.pokemonocean.domain.pokemon.repository.Repository
import javax.inject.Inject

/**
 * Created by Luca Pavan on 19/11/21
 */
class GetPokemonListUseCase @Inject constructor(
    private val repo: Repository
) {

    var isLastPageReached: Boolean = false
        private set

    suspend operator fun invoke(page: Int = 0) : List<String> {
        val names = repo.getPokemonList(page)
        isLastPageReached = names.isEmpty()
        return names
    }
}