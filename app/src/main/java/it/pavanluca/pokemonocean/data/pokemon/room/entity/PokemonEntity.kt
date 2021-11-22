package it.pavanluca.pokemonocean.data.pokemon.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Luca Pavan on 19/11/21
 */
@Entity(tableName = "pokemon")
data class PokemonEntity(
    @PrimaryKey val id: Int?,
    val name: String?,
    val abilities: List<AbilityEntity>?,
    val moves: List<MoveEntity>?,
    val imageUrl: String?,
    val detailImageUrl: String?,
    val stats: List<StatEntity>?,
    val types: List<TypeEntity>?
)
