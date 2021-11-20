package it.pavanluca.pokemonocean.presentation.widget.recyclerview

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import it.pavanluca.pokemonocean.databinding.ListItemPokemonBinding
import it.pavanluca.pokemonocean.domain.pokemon.models.Pokemon
import javax.inject.Inject

/**
 * Created by Luca Pavan on 19/11/21
 */
class PokemonAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<PokemonAdapter.PokemonVH>() {

    private var itemClick: ((Pokemon) -> Unit)? = null

    private val differ = AsyncListDiffer(this, PokemonDiffCallback())

    private var items: List<Pokemon>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.PokemonVH {
        return PokemonVH(
            ListItemPokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PokemonAdapter.PokemonVH, position: Int) {
        val pokemon = items[position]
        holder.viewBinding.apply {
            textPokemonName.text = pokemon.name

            cardContainer.setOnClickListener {
                itemClick?.invoke(pokemon)
            }

            glide.load(pokemon.imageUrl)
                .centerCrop()
                .into(imagePokemon)
        }
    }

    override fun getItemCount() = items.size

    fun setOnClickListener(listener: ((Pokemon) -> Unit)) {
        itemClick = listener
    }

    fun addPokemon(pokemon: Pokemon) {
        val tempList = items.toMutableList()
        tempList.add(pokemon)
        items = tempList
    }

    inner class PokemonVH(val viewBinding: ListItemPokemonBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

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