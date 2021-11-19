package it.pavanluca.pokemonocean.domain.pokemon.repository

import it.pavanluca.pokemonocean.domain.pokemon.models.Pokemon

/**
 * Created by Luca Pavan on 19/11/21
 */
interface Repository {

    suspend fun getPokemonList(page: Int) : List<String>

    suspend fun getPokemon(name: String) : Pokemon
}