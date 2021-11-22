package it.pavanluca.pokemonocean.data.pokemon.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import it.pavanluca.pokemonocean.data.pokemon.room.entity.*
import it.pavanluca.pokemonocean.data.pokemon.room.mappers.EntityConverters

/**
 * Created by Luca Pavan on 22/11/21
 */
@Database(
    entities = [
        AbilityEntity::class,
        MoveEntity::class,
        PokemonEntity::class,
        PokemonPageEntity::class,
        StatEntity::class,
        TypeEntity::class
    ], version = 1
)
@TypeConverters(EntityConverters::class)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao() : PokemonDao

    abstract fun pokemonNamesDao(): PokemonPagesDao

    companion object {
        val DATABASE_NAME: String = "pokemon_db"
    }
}