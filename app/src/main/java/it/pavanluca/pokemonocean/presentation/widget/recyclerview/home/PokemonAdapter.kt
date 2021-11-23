package it.pavanluca.pokemonocean.presentation.widget.recyclerview.home

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.google.android.material.chip.Chip
import it.pavanluca.pokemonocean.R
import it.pavanluca.pokemonocean.databinding.ListItemPokemonBinding
import it.pavanluca.pokemonocean.domain.pokemon.models.Pokemon
import it.pavanluca.pokemonocean.presentation.extensions.capitalizeLocale
import javax.inject.Inject

/**
 * Created by Luca Pavan on 19/11/21
 */
class PokemonAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<PokemonAdapter.PokemonVH>() {

    private var itemClick: ((Pokemon) -> Unit)? = null

    private val differ = AsyncListDiffer(this, PokemonDiffCallback())

    var items: List<Pokemon>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonVH {
        return PokemonVH(
            ListItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            LayoutInflater.from(parent.context)
        )
    }

    override fun onBindViewHolder(holder: PokemonVH, position: Int) {
        val pokemon = items[position]
        holder.viewBinding.apply {
            textPokemonName.text = pokemon.name.capitalizeLocale()

            cardContainer.setOnClickListener {
                itemClick?.invoke(pokemon)
            }

            glide.load(pokemon.imageUrl)
                .centerCrop()
                .into(imagePokemon)

            setupPokemonTypes(this, pokemon, holder.layoutInflater)
        }
    }

    override fun getItemCount() = items.size

    fun setOnClickListener(listener: ((Pokemon) -> Unit)) {
        itemClick = listener
    }

    fun addPokemon(pokemon: Pokemon) {
        if (pokemon !in items) {
            val tempList = items.toMutableList()
            tempList.add(pokemon)
            items = tempList
        }
    }

    private fun setupPokemonTypes(
        binding: ListItemPokemonBinding,
        pokemon: Pokemon,
        layoutInflater: LayoutInflater
    ) {
        binding.cgPokemonTypes.removeAllViews()
        pokemon.types?.filter {
            !it.name.isNullOrBlank()
        }?.forEach { type ->
            type.name?.let {
                val chip = layoutInflater.inflate(R.layout.pokemon_type_layout, null, false) as Chip
                chip.id = ViewCompat.generateViewId()
                chip.isCheckable = false
                chip.text = type.name
                chip.chipBackgroundColor = ColorStateList.valueOf(Color.parseColor(type.color))

                binding.cgPokemonTypes.addView(chip)
            }
        } ?: run {
            binding.cgPokemonTypes.visibility = View.GONE
        }
    }

    inner class PokemonVH(
        val viewBinding: ListItemPokemonBinding,
        val layoutInflater: LayoutInflater
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        init {
            //added ripple effect to feedback the user tap
            val typedValue = TypedValue()
            itemView.context.theme.resolveAttribute(
                android.R.attr.selectableItemBackground,
                typedValue,
                true
            )
            viewBinding.root.setBackgroundResource(typedValue.resourceId)
        }

    }
}