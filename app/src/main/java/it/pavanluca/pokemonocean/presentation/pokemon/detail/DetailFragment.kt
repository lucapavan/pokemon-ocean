package it.pavanluca.pokemonocean.presentation.pokemon.detail

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import it.pavanluca.pokemonocean.R
import it.pavanluca.pokemonocean.databinding.DetailFragmentBinding
import it.pavanluca.pokemonocean.databinding.PokemonStatLayoutBinding
import it.pavanluca.pokemonocean.domain.pokemon.models.Pokemon
import it.pavanluca.pokemonocean.presentation.extensions.capitalizeLocale
import it.pavanluca.pokemonocean.presentation.extensions.formattedId
import javax.inject.Inject

/**
 * Created by Luca Pavan on 20/11/21
 */
@AndroidEntryPoint
class DetailFragment @Inject constructor(
    private val glide: RequestManager
) : Fragment() {

    private lateinit var binding: DetailFragmentBinding

    private val args by navArgs<DetailFragmentArgs>()

    private var primaryColor: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater, container, false)

        setupPokemonImage(args.pokemon)
        setupPokemonTypes(args.pokemon)
        setupPokemonDetails(args.pokemon)

        return binding.root
    }

    private fun setupPokemonImage(pokemon: Pokemon) {
        glide.load(pokemon.detailImageUrl)
            .into(binding.imagePokemon)
    }

    private fun setupPokemonDetails(pokemon: Pokemon) {
        with(binding) {
            backgroundPokemon.setBackgroundColor(Color.parseColor(primaryColor))

            idLabel.text = pokemon.id.formattedId()

            pokemon.stats?.filter {
                it.baseStat != null && !it.name.isNullOrBlank()
            }?.forEach { stat ->
                val bindedLayout = PokemonStatLayoutBinding.inflate(layoutInflater)

                val parsedName = stat.name!!.replace("-", " ", false)
                bindedLayout.statLabel.text = parsedName.capitalizeLocale()

                when (stat.name) {
                    "hp" -> bindedLayout.statIndicator.setProgressCompat(stat.baseStat!!, false)
                    "attack" -> bindedLayout.statIndicator.setProgressCompat(stat.baseStat!!, false)
                    "defense" -> bindedLayout.statIndicator.setProgressCompat(stat.baseStat!!, false)
                    "special-attack" -> bindedLayout.statIndicator.setProgressCompat(stat.baseStat!!, false)
                    "special-defense" -> bindedLayout.statIndicator.setProgressCompat(stat.baseStat!!, false)
                    "speed" -> bindedLayout.statIndicator.setProgressCompat(stat.baseStat!!, false)
                    "accuracy" -> bindedLayout.statIndicator.setProgressCompat(stat.baseStat!!, false)
                    "evasion" -> bindedLayout.statIndicator.setProgressCompat(stat.baseStat!!, false)
                }

                binding.statsContainer.addView(bindedLayout.root)
            }
        }
    }

    private fun setupPokemonTypes(
        pokemon: Pokemon
    ) {
        pokemon.types?.filter {
            !it.name.isNullOrBlank()
        }?.forEach { type ->
            type.name?.let {
                val chip = layoutInflater.inflate(R.layout.pokemon_type_layout, null, false) as Chip
                chip.id = ViewCompat.generateViewId()
                chip.isCheckable = false
                chip.text = type.name
                chip.chipBackgroundColor = ColorStateList.valueOf(Color.parseColor(type.color))

                if (primaryColor.isNullOrBlank()) {
                    primaryColor = type.color
                }

                binding.cgPokemonTypes.addView(chip)
            }
        } ?: run {
            binding.cgPokemonTypes.visibility = View.GONE
        }
    }
}