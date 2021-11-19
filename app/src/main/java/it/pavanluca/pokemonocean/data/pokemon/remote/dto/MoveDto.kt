package it.pavanluca.pokemonocean.data.pokemon.remote.dto

import com.squareup.moshi.Json

/**
 * Created by Luca Pavan on 19/11/21
 */
data class MoveDto(
    @Json(name = "move")
    val move: SpeciesDto?
)