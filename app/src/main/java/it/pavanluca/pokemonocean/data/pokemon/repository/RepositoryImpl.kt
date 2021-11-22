package it.pavanluca.pokemonocean.data.pokemon.repository

import it.pavanluca.pokemonocean.common.Constants
import it.pavanluca.pokemonocean.common.PokemonError
import it.pavanluca.pokemonocean.data.pokemon.remote.api.PokemonApi
import it.pavanluca.pokemonocean.data.pokemon.remote.mappers.toEntity
import it.pavanluca.pokemonocean.data.pokemon.room.database.PokemonDao
import it.pavanluca.pokemonocean.data.pokemon.room.database.PokemonPagesDao
import it.pavanluca.pokemonocean.data.pokemon.room.entity.PokemonPageEntity
import it.pavanluca.pokemonocean.data.pokemon.room.mappers.fromEntity
import it.pavanluca.pokemonocean.data.pokemon.room.mappers.toEntity
import it.pavanluca.pokemonocean.domain.pokemon.models.Pokemon
import it.pavanluca.pokemonocean.domain.pokemon.repository.Repository
import javax.inject.Inject

/**
 * Created by Luca Pavan on 19/11/21
 */
class RepositoryImpl @Inject constructor(
    private val apiClient: PokemonApi,
    private val localDatabase: PokemonDao,
    private val localNameDatabase: PokemonPagesDao
) : Repository {

    override suspend fun getPokemonList(page: Int): List<String> {
        return getLocalNames(page) ?: getRemoteNames(page)
    }

    private suspend fun getLocalNames(page: Int) = runCatching {
        localNameDatabase.filterByPage(page).pokemonNames!!
    }.getOrNull()

    private suspend fun getRemoteNames(page: Int): List<String> {
        val offset = page * Constants.POKEMON_PER_PAGE
        return runCatching {
            val remote = apiClient.getPokemonList(offset = offset)
                .results!!
                .mapNotNull { it.name }

            localNameDatabase.insert(PokemonPageEntity(page, remote))

            remote
        }.getOrElse {
            throw setupError(it)
        }
    }

    override suspend fun getPokemon(name: String): Pokemon {
        return getLocalPokemon(name) ?: getRemotePokemon(name)
    }

    private suspend fun getLocalPokemon(name: String) = runCatching {
        localDatabase.filterByName(name).toEntity()
    }.getOrNull()

    private suspend fun getRemotePokemon(name: String): Pokemon {
        return runCatching {
            val remote = apiClient.getPokemon(name).toEntity()!!
            val cached = remote.fromEntity()
            localDatabase.insert(cached)
            cached.toEntity()!!
        }.getOrElse {
            throw setupError(it)
        }
    }

    private fun setupError(it: Throwable): Throwable {
        return it.localizedMessage?.let { localizedMessage ->
            PokemonError(localizedMessage)
        } ?: it.message?.let { message ->
            PokemonError(message)
        } ?: PokemonError.asNoData()
    }
}