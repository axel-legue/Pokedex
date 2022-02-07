package com.axell.pokedex.feature.pokemons.service

import com.axell.pokedex.feature.pokemons.entity.PokemonsEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    fun fetchPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Call<PokemonsEntity>
}
