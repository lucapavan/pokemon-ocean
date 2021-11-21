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
    val abilities: ArrayList<Ability>?,
    val moves: ArrayList<Move>?,
    val imageUrl: String?,
    val detailImageUrl: String,
    val stats: ArrayList<Stat>?,
    val types: ArrayList<PokemonType>?
) : Parcelable
