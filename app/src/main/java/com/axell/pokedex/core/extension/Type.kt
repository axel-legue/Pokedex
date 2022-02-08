package com.axell.pokedex.core.extension

import com.axell.pokedex.feature.pokemoninfo.entity.PokemonInfoEntity
import com.axell.pokedex.model.PokemonType

fun PokemonInfoEntity.Type.toPokemonType(): PokemonType {
    return when (this.name) {
        "Normal".lowercase() -> PokemonType.Normal
        "Fighting".lowercase() -> PokemonType.Fighting
        "Flying".lowercase() -> PokemonType.Flying
        "Poison".lowercase() -> PokemonType.Poison
        "Ground".lowercase() -> PokemonType.Ground
        "Rock".lowercase() -> PokemonType.Rock
        "Bug".lowercase() -> PokemonType.Bug
        "Ghost".lowercase() -> PokemonType.Ghost
        "Steel".lowercase() -> PokemonType.Steel
        "Fire".lowercase() -> PokemonType.Fire
        "Water".lowercase() -> PokemonType.Water
        "Grass".lowercase() -> PokemonType.Grass
        "lectric".lowercase() -> PokemonType.Electric
        "Psychic".lowercase() -> PokemonType.Psychic
        "Ice".lowercase() -> PokemonType.Ice
        "Dragon".lowercase() -> PokemonType.Dragon
        "Dark".lowercase() -> PokemonType.Dark
        "Fairy".lowercase() -> PokemonType.Fairy
        "Unknown".lowercase() -> PokemonType.Unknown
        else -> PokemonType.Unknown
    }
}
