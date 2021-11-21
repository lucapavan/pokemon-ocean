package it.pavanluca.pokemonocean.presentation.pokemon.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
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
import it.pavanluca.pokemonocean.BuildConfig
import it.pavanluca.pokemonocean.R
import it.pavanluca.pokemonocean.common.PokemonError
import it.pavanluca.pokemonocean.databinding.HomeFragmentBinding
import it.pavanluca.pokemonocean.domain.pokemon.models.Pokemon
import it.pavanluca.pokemonocean.presentation.MainActivity
import it.pavanluca.pokemonocean.presentation.extensions.capitalizeLocale
import it.pavanluca.pokemonocean.presentation.widget.recyclerview.PagedScrollListener
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[HomeVM::class.java]

        savedInstanceState?.let {
            if (it.containsKey(ITEMS_KEY)) {
                val list = it.getParcelableArrayList<Pokemon>(ITEMS_KEY) as List<Pokemon>
                pokemonAdapter.items = list
            }
        }
    }

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

        setupObservable()
        setupAdapter()
        setupRecycler()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(ITEMS_KEY, ArrayList(pokemonAdapter.items))
        super.onSaveInstanceState(outState)
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.getNetworkCapabilities(cm.activeNetwork)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
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
                is UIPokemonListLoaded -> {
                    retrievePokemon(state.pokemonNames)
                }
                is UIPokemonLoaded -> {
                    addPokemon(state.pokemon)
                }
                is UIPokemonError -> {
                    hideLoading()
                    showDialog(state.error)
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
            clearOnScrollListeners()
            addOnScrollListener(PagedScrollListener(viewModel))
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

    private fun showDialog(error: PokemonError) {
        if (BuildConfig.DEBUG) {
            Log.e("HomeFragment", "ShowError: ${error.message}")
        }

        if (!isNetworkAvailable()) {
            showDialog(R.string.internet_connection_error_message)
            return
        }

        error.messageToShow?.let { errorMessage ->
            (requireActivity() as MainActivity?)?.showDialogError(errorMessage) {
                viewModel.retryToGetPokemonList()
            }
        }
    }

    private fun showDialog(resStringId: Int) {
        (requireActivity() as MainActivity?)?.showDialogError(resStringId) {

            viewModel.retryToGetPokemonList()
        }
    }

    companion object {
        private const val ITEMS_KEY = "items_key"
    }
}