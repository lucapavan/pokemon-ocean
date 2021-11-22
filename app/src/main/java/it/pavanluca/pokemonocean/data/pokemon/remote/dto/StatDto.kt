package it.pavanluca.pokemonocean.data.pokemon.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Luca Pavan on 19/11/21
 */
@JsonClass(generateAdapter = true)
data class StatDto(
    @Json(name = "base_stat")
    val baseStat: Int?,
    val effort: Int?,
    val stat: SpeciesDto?
)