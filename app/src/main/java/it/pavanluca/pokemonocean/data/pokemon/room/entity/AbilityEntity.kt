package it.pavanluca.pokemonocean.data.pokemon.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Luca Pavan on 19/11/21
 */
@Entity(tableName = "abilities")
data class AbilityEntity(
    @PrimaryKey val ability: String,
    val isHidden: Boolean?,
    val slot: Int?
)