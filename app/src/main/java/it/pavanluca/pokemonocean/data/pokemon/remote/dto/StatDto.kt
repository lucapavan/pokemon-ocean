package it.pavanluca.pokemonocean.data.pokemon.remote.dto

import com.squareup.moshi.Json

/**
 * Created by Luca Pavan on 19/11/21
 */
data class StatDto(
    @Json(name = "base_stat")
    val baseStat: Int?,
    @Json(name = "base_stat")
    val effort: Int?,
    @Json(name = "base_stat")
    val stat: SpeciesDto?
)