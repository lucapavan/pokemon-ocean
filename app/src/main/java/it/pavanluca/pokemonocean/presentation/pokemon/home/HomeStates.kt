package it.pavanluca.pokemonocean.presentation.pokemon.home

import io.uniflow.core.flow.data.UIState
import it.pavanluca.pokemonocean.domain.pokemon.models.Pokemon

/**
 * Created by Luca Pavan on 19/11/21
 */
data class PokemonListLoaded(val pokemonNames: List<String>): UIState()

data class PokemonLoaded(val pokemon: Pokemon): UIState()
