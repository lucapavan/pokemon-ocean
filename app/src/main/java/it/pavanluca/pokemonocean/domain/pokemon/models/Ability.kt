package it.pavanluca.pokemonocean.domain.pokemon.models

/**
 * Created by Luca Pavan on 19/11/21
 */
data class Ability(
    val ability: Species?,
    val isHidden: Boolean?,
    val slot: Int?
)