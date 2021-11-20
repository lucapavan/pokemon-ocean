package it.pavanluca.pokemonocean.domain.pokemon.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Luca Pavan on 19/11/21
 */
@Parcelize
data class Species(
    val name: String?,
    val url: String?
) : Parcelable