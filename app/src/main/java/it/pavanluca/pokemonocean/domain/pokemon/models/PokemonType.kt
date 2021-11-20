package it.pavanluca.pokemonocean.domain.pokemon.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Luca Pavan on 19/11/21
 */
@Parcelize
data class PokemonType(
    val slot: Int?,
    val name: String?,
    val color: String?
) : Parcelable