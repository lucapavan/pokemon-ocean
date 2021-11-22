package it.pavanluca.pokemonocean.domain.pokemon.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Luca Pavan on 19/11/21
 */
@Parcelize
data class Pokemon(
    val id: Int,
    val name: String,
    val abilities: List<Ability>?,
    val moves: List<Move>?,
    val imageUrl: String?,
    val detailImageUrl: String,
    val stats: List<Stat>?,
    val types: List<PokemonType>?
) : Parcelable
