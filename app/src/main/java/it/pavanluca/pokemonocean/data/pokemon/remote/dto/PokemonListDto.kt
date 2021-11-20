package it.pavanluca.pokemonocean.data.pokemon.remote.dto

import com.squareup.moshi.JsonClass

/**
 * Created by Luca Pavan on 19/11/21
 */
@JsonClass(generateAdapter = true)
data class PokemonListDto(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<SpeciesDto>?
)