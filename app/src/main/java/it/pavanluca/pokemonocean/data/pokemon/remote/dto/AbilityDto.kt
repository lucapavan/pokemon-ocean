package it.pavanluca.pokemonocean.data.pokemon.remote.dto

import com.squareup.moshi.Json

/**
 * Created by Luca Pavan on 19/11/21
 */
data class AbilityDto(
    @Json(name = "ability")
    val ability: SpeciesDto?,
    @Json(name = "is_hidden")
    val isHidden: Boolean?,
    @Json(name = "slot")
    val slot: Int?
)