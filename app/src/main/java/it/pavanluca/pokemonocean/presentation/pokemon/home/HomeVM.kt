package it.pavanluca.pokemonocean.presentation.pokemon.home

import dagger.hilt.android.lifecycle.HiltViewModel
import io.uniflow.android.AndroidDataFlow
import io.uniflow.core.flow.data.UIError
import io.uniflow.core.flow.data.UIEvent
import io.uniflow.core.flow.data.UIState
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

    init {
        getPokemonList(currentPage)
    }

    fun getPokemonList(page: Int) = action {
        sendEvent { UIEvent.Loading }
        runCatching {
            val pokemon = getPokemonListUseCase(page)
            setState { PokemonListLoaded(pokemon) }
            sendEvent { UIEvent.Success }
        }.getOrElse {
            sendEvent { UIEvent.Error(it.message, it) }
            setState { UIState.Failed(it.message, it) }
        }
    }

    fun getPokemon(name: String) = action {
        runCatching {
            val pokemon = getPokemonUseCase(name)
            setState { PokemonLoaded(pokemon) }
        }.getOrElse {
            sendEvent { UIEvent.Error(it.message, it) }
            setState { UIState.Failed(it.message, it) }
        }
    }

    override fun onEndReached() {
        getPokemonList(page = ++currentPage)
    }
}