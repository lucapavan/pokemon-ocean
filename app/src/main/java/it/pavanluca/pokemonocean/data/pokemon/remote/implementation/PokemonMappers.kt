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
        type?.toEntity()
    )
}

fun SpeciesDto.toEntity(): Species {
    return Species(
        name,
        url
    )
}