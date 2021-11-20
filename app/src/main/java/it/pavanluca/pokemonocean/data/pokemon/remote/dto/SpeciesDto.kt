package it.pavanluca.pokemonocean.data.pokemon.remote.dto

import com.squareup.moshi.JsonClass

/**
 * Created by Luca Pavan on 19/11/21
 */
@JsonClass(generateAdapter = true)
data class SpeciesDto(
    val name: String?,
    val url: String?
)