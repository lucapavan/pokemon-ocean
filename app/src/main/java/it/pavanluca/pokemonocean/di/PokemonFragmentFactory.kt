package it.pavanluca.pokemonocean.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import it.pavanluca.pokemonocean.presentation.pokemon.detail.DetailFragment
import javax.inject.Inject

/**
 * Created by Luca Pavan on 20/11/21
 */
class PokemonFragmentFactory @Inject constructor(
    private val glide: RequestManager
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            DetailFragment::class.java.name -> DetailFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }

}