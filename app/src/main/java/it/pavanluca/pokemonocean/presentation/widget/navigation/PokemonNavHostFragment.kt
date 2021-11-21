package it.pavanluca.pokemonocean.presentation.widget.navigation

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import it.pavanluca.pokemonocean.di.PokemonFragmentFactory
import javax.inject.Inject

/**
 * Created by Luca Pavan on 21/11/21
 */
@AndroidEntryPoint
class PokemonNavHostFragment() : NavHostFragment() {

    @Inject
    lateinit var pokemonFactory: PokemonFragmentFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        childFragmentManager.fragmentFactory = pokemonFactory
    }

}