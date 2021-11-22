package it.pavanluca.pokemonocean.data.pokemon.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Luca Pavan on 19/11/21
 */
@Entity(tableName = "stats")
data class StatEntity(
    @PrimaryKey val stat: String,
    val baseStat: Int?,
    val effort: Int?
)