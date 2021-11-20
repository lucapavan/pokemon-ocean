package it.pavanluca.pokemonocean.presentation.widget.recyclerview

import androidx.recyclerview.widget.DiffUtil
import it.pavanluca.pokemonocean.domain.pokemon.models.Pokemon

/**
 * Created by Luca Pavan on 19/11/21
 */
class PokemonDiffCallback: DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}