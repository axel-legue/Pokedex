package com.axell.pokedex.core.extension
import androidx.compose.ui.graphics.Color
import com.axell.pokedex.R
import com.axell.pokedex.model.PokemonType
import com.axell.pokedex.ui.theme.Bug
import com.axell.pokedex.ui.theme.Dark
import com.axell.pokedex.ui.theme.Dragon
import com.axell.pokedex.ui.theme.Electric
import com.axell.pokedex.ui.theme.Fairy
import com.axell.pokedex.ui.theme.Fighting
import com.axell.pokedex.ui.theme.Fire
import com.axell.pokedex.ui.theme.Flying
import com.axell.pokedex.ui.theme.Ghost
import com.axell.pokedex.ui.theme.Grass
import com.axell.pokedex.ui.theme.Ground
import com.axell.pokedex.ui.theme.Ice
import com.axell.pokedex.ui.theme.Normal
import com.axell.pokedex.ui.theme.Poison
import com.axell.pokedex.ui.theme.Psychic
import com.axell.pokedex.ui.theme.Rock
import com.axell.pokedex.ui.theme.Steel
import com.axell.pokedex.ui.theme.Unknown
import com.axell.pokedex.ui.theme.Water

fun PokemonType.toColor(): Color {
    return when (this) {
        PokemonType.Normal -> Normal
        PokemonType.Fighting -> Fighting
        PokemonType.Flying -> Flying
        PokemonType.Poison -> Poison
        PokemonType.Ground -> Ground
        PokemonType.Rock -> Rock
        PokemonType.Bug -> Bug
        PokemonType.Ghost -> Ghost
        PokemonType.Steel -> Steel
        PokemonType.Fire -> Fire
        PokemonType.Water -> Water
        PokemonType.Grass -> Grass
        PokemonType.Electric -> Electric
        PokemonType.Psychic -> Psychic
        PokemonType.Ice -> Ice
        PokemonType.Dragon -> Dragon
        PokemonType.Dark -> Dark
        PokemonType.Fairy -> Fairy
        PokemonType.Unknown -> Unknown
    }
}

fun PokemonType.toDrawable(): Int {
    return when (this) {
        PokemonType.Normal -> R.drawable.ic_normal
        PokemonType.Fighting -> R.drawable.ic_fighting
        PokemonType.Flying -> R.drawable.ic_flying
        PokemonType.Poison -> R.drawable.ic_poison
        PokemonType.Ground -> R.drawable.ic_ground
        PokemonType.Rock -> R.drawable.ic_rock
        PokemonType.Bug -> R.drawable.ic_bug
        PokemonType.Ghost -> R.drawable.ic_ghost
        PokemonType.Steel -> R.drawable.ic_steel
        PokemonType.Fire -> R.drawable.ic_fire
        PokemonType.Water -> R.drawable.ic_water
        PokemonType.Grass -> R.drawable.ic_grass
        PokemonType.Electric -> R.drawable.ic_electric
        PokemonType.Psychic -> R.drawable.ic_psychic
        PokemonType.Ice -> R.drawable.ic_ice
        PokemonType.Dragon -> R.drawable.ic_dragon
        PokemonType.Dark -> R.drawable.ic_dark
        PokemonType.Fairy -> R.drawable.ic_fairy
        PokemonType.Unknown -> R.drawable.ic_normal // Todo : add default drawable
    }
}
