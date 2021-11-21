package it.pavanluca.pokemonocean.presentation.pokemon.home

import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import io.uniflow.android.livedata.states
import io.uniflow.core.flow.data.UIEvent
import io.uniflow.core.flow.data.UIState
import it.pavanluca.pokemonocean.R
import it.pavanluca.pokemonocean.common.Constants
import it.pavanluca.pokemonocean.common.PokemonError
import it.pavanluca.pokemonocean.domain.pokemon.use_case.GetPokemonListUseCase
import it.pavanluca.pokemonocean.domain.pokemon.use_case.GetPokemonUseCase
import it.pavanluca.pokemonocean.presentation.widget.recyclerview.PageListener
import javax.inject.Inject

/**
 * Created by Luca Pavan on 19/11/21
 */
@HiltViewModel
class HomeVM @Inject constructor(
    private var getPokemonListUseCase: GetPokemonListUseCase,
    private var getPokemonUseCase: GetPokemonUseCase
) : AndroidDataFlow(), PageListener {

    private var currentPage = 0

    private var pokemonCounter = 0

    init {
        getPokemonList(currentPage)
    }

    fun retryToGetPokemonList() = getPokemonList(page = currentPage)

    private fun getPokemonList(page: Int) = action {
        sendEvent { UIEvent.Loading }
        runCatching {
            val pokemon = getPokemonListUseCase(page)
            setState { UIPokemonListLoaded(pokemon) }
            sendEvent { UIEvent.Success }
        }.getOrElse {
            setState { UIPokemonError(setupError(it)) }
        }
    }

    fun getPokemon(name: String) = action {
        runCatching {
            val pokemon = getPokemonUseCase(name)
            pokemonCounter++
            setState { UIPokemonLoaded(pokemon) }
        }.getOrElse {
            setState { UIPokemonError(setupError(it)) }
        }
    }

    private fun setupError(it: Throwable): PokemonError {
        return (it as PokemonError?)?.let { pokemonError ->
            if (pokemonError.isSilent) {
                PokemonError(messageToShow = R.string.error_message)
            } else {
                PokemonError.asNoData()
            }
        } ?: PokemonError(messageToShow = R.string.error_message)
    }

    override fun onEndReached() {
        if (states.value !is UIState.Loading &&
            pokemonCounter % Constants.POKEMON_PER_PAGE == 0 &&
            !getPokemonListUseCase.isLastPageReached
        ) {
            getPokemonList(page = ++currentPage)
        }
    }
}