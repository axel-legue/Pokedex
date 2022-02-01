package com.axell.pokedex

enum class PokedexScreen() {
    Home,
    Pokemons,
    Favorites,
    Items,
    Moves,
    Types,
    PokemonDetails;

    companion object {
        fun fromRoute(route: String?): PokedexScreen =
            when (route?.substringBefore("/")) {
                Home.name -> Home
                Pokemons.name -> Pokemons
                Favorites.name -> Favorites
                Items.name -> Items
                Moves.name -> Moves
                Types.name -> Types
                PokemonDetails.name -> PokemonDetails
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}
