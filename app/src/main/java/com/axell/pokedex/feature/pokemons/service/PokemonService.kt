package com.axell.pokedex.feature.pokemons.service

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonService @Inject constructor(retrofit: Retrofit) : PokemonApi {

    private val pokemonApi by lazy { retrofit.create(PokemonApi::class.java) }

    override suspend fun fetchPokemons(offset: Int, limit: Int) = pokemonApi.fetchPokemons(offset = offset, limit = limit)
}
