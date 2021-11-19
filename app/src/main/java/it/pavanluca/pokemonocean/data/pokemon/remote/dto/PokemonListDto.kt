package it.pavanluca.pokemonocean.data.pokemon.remote.dto

import com.squareup.moshi.Json

/**
 * Created by Luca Pavan on 19/11/21
 */
data class PokemonListDto(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "next")
    val next: String?,
    @Json(name = "previous")
    val previous: String?,
    @Json(name = "results")
    val results: ArrayList<SpeciesDto>?
)