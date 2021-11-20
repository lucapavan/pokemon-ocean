package it.pavanluca.pokemonocean.presentation.pokemon

import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import it.pavanluca.pokemonocean.domain.pokemon.use_case.GetPokemonListUseCase
import it.pavanluca.pokemonocean.domain.pokemon.use_case.GetPokemonUseCase
import javax.inject.Inject

/**
 * Created by Luca Pavan on 19/11/21
 */
@HiltViewModel
class HomeVM @Inject constructor(
    private var getPokemonListUseCase: GetPokemonListUseCase,
    private var getPokemonUseCase: GetPokemonUseCase
) : AndroidDataFlow() {

    fun getPokemonList(page: Int = 0) = action {
        val pokemon = getPokemonListUseCase(page)
        setState { PokemonListLoaded(pokemon) }
    }

    fun getPokemon(name: String) = action {
        val pokemon = getPokemonUseCase(name)
        setState { PokemonLoaded(pokemon) }
    }
}