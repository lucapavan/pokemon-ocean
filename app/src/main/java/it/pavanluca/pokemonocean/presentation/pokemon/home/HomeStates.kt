package it.pavanluca.pokemonocean.presentation.pokemon.home

import io.uniflow.core.flow.data.UIState
import it.pavanluca.pokemonocean.common.PokemonError
import it.pavanluca.pokemonocean.domain.pokemon.models.Pokemon

/**
 * Created by Luca Pavan on 19/11/21
 */
data class UIPokemonListLoaded(val pokemonNames: List<String>): UIState()

data class UIPokemonLoaded(val pokemon: Pokemon): UIState()

data class UIPokemonError(val error: PokemonError): UIState()
