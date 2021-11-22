package it.pavanluca.pokemonocean.data.pokemon.room.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import it.pavanluca.pokemonocean.data.pokemon.room.entity.PokemonPageEntity

/**
 * Created by Luca Pavan on 22/11/21
 */
@Dao
interface PokemonPagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(page: PokemonPageEntity): Long

    @Query("SELECT * FROM pages")
    suspend fun selectAll(): List<PokemonPageEntity>

    @Query("SELECT * FROM pages WHERE pageNumber == :page ORDER BY pageNumber ASC LIMIT 1")
    suspend fun filterByPage(page: Int): PokemonPageEntity
}