package it.pavanluca.pokemonocean.data.pokemon.repository

import it.pavanluca.pokemonocean.common.Constants
import it.pavanluca.pokemonocean.common.PokemonError
import it.pavanluca.pokemonocean.data.pokemon.remote.api.PokemonApi
import it.pavanluca.pokemonocean.data.pokemon.remote.implementation.toEntity
import it.pavanluca.pokemonocean.domain.pokemon.models.Pokemon
import it.pavanluca.pokemonocean.domain.pokemon.repository.Repository
import javax.inject.Inject

/**
 * Created by Luca Pavan on 19/11/21
 */
class RepositoryImpl @Inject constructor(
    private val apiClient: PokemonApi
) : Repository {

    override suspend fun getPokemonList(page: Int): List<String> {
        val offset = page * Constants.POKEMON_PER_PAGE
        return runCatching {
            apiClient.getPokemonList(
                offset = offset
            ).results!!.mapNotNull { it.name }
        }.getOrElse {
            throw PokemonError.asNoData()
        }
    }

    override suspend fun getPokemon(name: String): Pokemon {
        return runCatching {
            apiClient.getPokemon(name).toEntity()!!
        }.getOrElse {
            throw it.localizedMessage?.let { localizedMessage ->
                PokemonError(localizedMessage)
            } ?: it.message?.let { message ->
                PokemonError(message)
            } ?: PokemonError.asNoData()
        }
    }

}