package it.pavanluca.pokemonocean.data.pokemon.room.mappers

import it.pavanluca.pokemonocean.common.Constants
import it.pavanluca.pokemonocean.data.pokemon.remote.mappers.getColorByName
import it.pavanluca.pokemonocean.data.pokemon.room.entity.*
import it.pavanluca.pokemonocean.domain.pokemon.models.*

/**
 * Created by Luca Pavan on 19/11/21
 */

//region ToEntity

fun PokemonEntity.toEntity(): Pokemon? {
    return if (id == null || name == null) {
        null
    } else {
        Pokemon(
            id,
            name.replace("-", " "),
            abilities?.mapTo(ArrayList()) { it.toEntity() },
            moves?.mapTo(ArrayList()) { it.toEntity() },
            imageUrl,
            Constants.BASE_DETAIL_SPRITE_URL + "$id.png",
            stats?.mapTo(ArrayList()) { it.toEntity() },
            types?.mapTo(ArrayList()) { it.toEntity() }
        )
    }
}

fun AbilityEntity.toEntity(): Ability {
    return Ability(
        ability,
        isHidden,
        slot
    )
}

fun MoveEntity.toEntity() = Move(move)

fun StatEntity.toEntity(): Stat {
    return Stat(
        baseStat,
        effort,
        stat
    )
}

fun TypeEntity.toEntity(): PokemonType {
    return PokemonType(
        slot,
        type,
        getColorByName(type)
    )
}

//endregion

//region FromEntity

fun Pokemon.fromEntity(): PokemonEntity {
    return PokemonEntity(
        id = id,
        name = name,
        abilities = abilities?.mapNotNull { it.fromEntity() },
        moves = moves?.mapNotNull { it.fromEntity() },
        imageUrl = imageUrl,
        detailImageUrl = Constants.BASE_DETAIL_SPRITE_URL + "$id.png",
        stats = stats?.mapNotNull { it.fromEntity() },
        types = types?.mapNotNull { it.fromEntity() }
    )
}

fun Ability.fromEntity(): AbilityEntity? {
    return ability?.let {
        AbilityEntity(
            ability,
            isHidden,
            slot
        )
    }
}

fun Move.fromEntity(): MoveEntity? {
    return move?.let {
        MoveEntity(move)
    }
}

fun Stat.fromEntity(): StatEntity? {
    return name?.let {
        StatEntity(
            name,
            baseStat,
            effort
        )
    }
}

fun PokemonType.fromEntity(): TypeEntity? {
    return name?.let {
        TypeEntity(
            name,
            slot
        )
    }
}

//endregion