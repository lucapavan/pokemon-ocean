package it.pavanluca.pokemonocean.data.pokemon.remote.dto

import com.squareup.moshi.JsonClass

/**
 * Created by Luca Pavan on 19/11/21
 */
@JsonClass(generateAdapter = true)
data class TypeDto(
    val slot: Int?,
    val type: SpeciesDto?
)