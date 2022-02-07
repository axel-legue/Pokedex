package com.axell.pokedex.feature.pokemons.service

import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Retrofit

@Singleton
class PokemonService @Inject constructor(retrofit: Retrofit) : PokemonApi {

    private val pokemonApi by lazy { retrofit.create(PokemonApi::class.java) }

    override fun fetchPokemons(offset: Int, limit: Int) = pokemonApi.fetchPokemons(offset = offset, limit = limit)
}
