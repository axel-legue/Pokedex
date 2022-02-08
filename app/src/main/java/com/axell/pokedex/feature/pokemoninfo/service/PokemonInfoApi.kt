package com.axell.pokedex.feature.pokemoninfo.service

import com.axell.pokedex.feature.pokemoninfo.entity.PokemonInfoEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonInfoApi {

    @GET("pokemon/{name}")
    suspend fun fetchPokemonInfo(@Path("name") name: String): PokemonInfoEntity
}
