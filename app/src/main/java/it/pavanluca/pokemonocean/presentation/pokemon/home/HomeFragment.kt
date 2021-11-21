package it.pavanluca.pokemonocean.presentation.pokemon.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.uniflow.android.livedata.onEvents
import io.uniflow.android.livedata.onStates
import io.uniflow.core.flow.data.UIEvent
import io.uniflow.core.flow.data.UIState
import it.pavanluca.pokemonocean.databinding.HomeFragmentBinding
import it.pavanluca.pokemonocean.domain.pokemon.models.Pokemon
import it.pavanluca.pokemonocean.presentation.extensions.capitalizeLocale
import it.pavanluca.pokemonocean.presentation.widget.recyclerview.home.PokemonAdapter
import javax.inject.Inject

/**
 * Created by Luca Pavan on 19/11/21
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: HomeFragmentBinding

    private lateinit var viewModel: HomeVM

    @Inject
    lateinit var pokemonAdapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel = ViewModelProvider(requireActivity())[HomeVM::class.java]

        setupObservable()
        setupAdapter()
        setupRecycler()

        viewModel.getPokemonList()
    }

    private fun setupObservable() {
        onEvents(viewModel) { event ->
            when (event) {
                is UIEvent.Loading -> {
                    showLoading()
                }
                is UIEvent.Success -> {
                    hideLoading()
                }
            }
        }

        onStates(viewModel) { state ->
            when (state) {
                is UIState.Empty -> {
                    viewModel.getPokemonList()
                }
                is PokemonListLoaded -> {
                    retrievePokemon(state.pokemonNames)
                }
                is PokemonLoaded -> {
                    addPokemon(state.pokemon)
                }
            }
        }
    }

    private fun setupAdapter() {
        pokemonAdapter.setOnClickListener { pokemon ->
            findNavController().navigate(
                HomeFragmentDirections.actionShowDetail(
                    pokemon,
                    pokemon.name.capitalizeLocale()
                )
            )
        }
    }

    private fun setupRecycler() {
        binding.rwPokemon.apply {
            adapter = pokemonAdapter
        }
    }

    private fun retrievePokemon(pokemonNames: List<String>) {
        pokemonNames.forEach { name ->
            viewModel.getPokemon(name)
        }
    }

    private fun addPokemon(pokemon: Pokemon) {
        pokemonAdapter.addPokemon(pokemon)
    }

    private fun showLoading() {
        if (binding.progressHorizontal.visibility != View.VISIBLE) {
            binding.progressHorizontal.visibility = View.VISIBLE
        }
    }

    private fun hideLoading() {
        if (binding.progressHorizontal.visibility != View.GONE) {
            binding.progressHorizontal.visibility = View.GONE
        }
    }
}