package com.axell.pokedex.feature.pokemoninfo.service

import com.axell.pokedex.feature.pokemoninfo.entity.PokemonInfoEntity
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonInfoService @Inject constructor(retrofit: Retrofit) : PokemonInfoApi {

    private val pokemonInfoApi by lazy { retrofit.create(PokemonInfoApi::class.java) }

    override suspend fun fetchPokemonInfo(name: String): PokemonInfoEntity = pokemonInfoApi.fetchPokemonInfo(name = name)
}
