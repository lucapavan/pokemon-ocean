package it.pavanluca.pokemonocean.domain.pokemon.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Luca Pavan on 19/11/21
 */
@Parcelize
data class Ability(
    val ability: Species?,
    val isHidden: Boolean?,
    val slot: Int?
) : Parcelable