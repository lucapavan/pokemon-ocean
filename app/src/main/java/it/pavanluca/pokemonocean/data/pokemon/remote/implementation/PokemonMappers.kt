package it.pavanluca.pokemonocean.data.pokemon.remote.implementation

import it.pavanluca.pokemonocean.data.pokemon.remote.dto.*
import it.pavanluca.pokemonocean.domain.pokemon.models.*

/**
 * Created by Luca Pavan on 19/11/21
 */

fun PokemonDto.toEntity(): Pokemon? {
    return if (id == null || name == null) {
        null
    } else {
        Pokemon(
            id,
            name,
            abilities?.mapTo(ArrayList()) { it.toEntity() },
            moves?.mapTo(ArrayList()) { it.toEntity() },
            sprites?.frontDefault,
            stats?.mapTo(ArrayList()) { it.toEntity() },
            types?.mapTo(ArrayList()) { it.toEntity() }
        )
    }
}

fun AbilityDto.toEntity(): Ability {
    return Ability(
        ability?.toEntity(),
        isHidden,
        slot
    )
}

fun MoveDto.toEntity(): Move {
    return Move(
        move?.toEntity()
    )
}

fun StatDto.toEntity(): Stat {
    return Stat(
        baseStat,
        effort,
        stat?.toEntity()
    )
}

fun TypeDto.toEntity(): PokemonType {
    return PokemonType(
        slot,
        type?.toEntity()?.name,
        getColorByName(type?.toEntity()?.name)
    )
}

fun getColorByName(name: String?): String? {
    return when (name) {
        "bug" -> "#A6B91A"
        "dark" -> "#705746"
        "dragon" -> "#6F35FC"
        "electric" -> "#F7D02C"
        "fairy" -> "#D685AD"
        "fighting" -> "#C22E28"
        "fire" -> "#EE8130"
        "flying" -> "#A98FF3"
        "ghost" -> "#735797"
        "grass" -> "#7AC74C"
        "ground" -> "#E2BF65"
        "ice" -> "#96D9D6"
        "normal" -> "#A8A77A"
        "poison" -> "#A33EA1"
        "psychic" -> "#F95587"
        "rock" -> "#B6A136"
        "steel" -> "#B7B7CE"
        "water" -> "#6390F0"
        else -> null
    }
}

fun SpeciesDto.toEntity(): Species {
    return Species(
        name,
        url
    )
}