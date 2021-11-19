package it.pavanluca.pokemonocean.data.pokemon.remote.api

import it.pavanluca.pokemonocean.data.pokemon.remote.dto.PokemonDto
import it.pavanluca.pokemonocean.data.pokemon.remote.dto.PokemonListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Luca Pavan on 19/11/21
 */
interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int
    ) : PokemonListDto

    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name: String
    ) : PokemonDto
}