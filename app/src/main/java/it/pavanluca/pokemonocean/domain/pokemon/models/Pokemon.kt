package it.pavanluca.pokemonocean.domain.pokemon.models

/**
 * Created by Luca Pavan on 19/11/21
 */
data class Pokemon(
    val id: Int,
    val name: String,
    val abilities: ArrayList<Ability>?,
    val moves: ArrayList<Move>?,
    val imageUrl: String?,
    val stats: ArrayList<Stat>?,
    val types: ArrayList<PokemonType>?
)
