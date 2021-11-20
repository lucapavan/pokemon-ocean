package it.pavanluca.pokemonocean.data.pokemon.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created by Luca Pavan on 19/11/21
 */
@JsonClass(generateAdapter = true)
data class AbilityDto(
    val ability: SpeciesDto?,
    @Json(name = "is_hidden")
    val isHidden: Boolean?,
    val slot: Int?
)