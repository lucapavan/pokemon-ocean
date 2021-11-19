package it.pavanluca.pokemonocean.data.pokemon.remote.dto

import com.squareup.moshi.Json

/**
 * Created by Luca Pavan on 19/11/21
 */
data class PokemonDto(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "abilities")
    val abilities: ArrayList<AbilityDto>?,
    @Json(name = "moves")
    val moves: ArrayList<MoveDto>?,
    @Json(name = "sprites")
    val sprites: SpritesDto?,
    @Json(name = "stats")
    val stats: ArrayList<StatDto>?,
    @Json(name = "types")
    val types: ArrayList<TypeDto>?
)
