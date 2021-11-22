package it.pavanluca.pokemonocean.data.pokemon.room.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import it.pavanluca.pokemonocean.data.pokemon.room.entity.PokemonEntity

/**
 * Created by Luca Pavan on 22/11/21
 */
@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: PokemonEntity): Long

    @Query("SELECT * FROM pokemon")
    suspend fun selectAll(): List<PokemonEntity>

    @Query("SELECT * FROM pokemon WHERE upper(name) = upper(:name) ORDER BY name ASC LIMIT 1")
    suspend fun filterByName(name: String): PokemonEntity
}