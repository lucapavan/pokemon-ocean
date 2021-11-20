package it.pavanluca.pokemonocean.data.pokemon.remote.dto

import com.squareup.moshi.JsonClass

/**
 * Created by Luca Pavan on 19/11/21
 */
@JsonClass(generateAdapter = true)
data class PokemonDto(
    val id: Int?,
    val name: String?,
    val abilities: List<AbilityDto>?,
    val moves: List<MoveDto>?,
    val sprites: SpritesDto?,
    val stats: List<StatDto>?,
    val types: List<TypeDto>?
)
