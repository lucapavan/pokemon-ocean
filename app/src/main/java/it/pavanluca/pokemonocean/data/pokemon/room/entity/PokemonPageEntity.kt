package it.pavanluca.pokemonocean.data.pokemon.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Luca Pavan on 22/11/21
 */
@Entity(tableName = "pages")
data class PokemonPageEntity(
    @PrimaryKey val pageNumber: Int,
    val pokemonNames: List<String>?
)