package com.axell.pokedex.feature.pokemons.repository

import com.axell.pokedex.core.exception.Failure
import com.axell.pokedex.core.exception.Failure.ServerError
import com.axell.pokedex.core.functional.Either
import com.axell.pokedex.core.platform.NetworkHandler
import com.axell.pokedex.feature.pokemons.entity.PokemonEntity
import com.axell.pokedex.feature.pokemons.entity.PokemonsEntity
import com.axell.pokedex.feature.pokemons.service.PokemonService
import retrofit2.Call
import javax.inject.Inject

interface PokemonRepository {
    fun pokemons(): Either<Failure, List<PokemonEntity>>

    class Network @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: PokemonService
    ) : PokemonRepository {

        override fun pokemons(): Either<Failure, List<PokemonEntity>> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> request(
                    service.fetchPokemons(),
                    {
                        it.results
                    },
                    default = PokemonsEntity(count = 0, next = null, previous = null, results = listOf())
                )
                false -> {
                    Either.Left(Failure.NetworkConnectionError)
                }
            }
        }

        private fun <T, R> request(
            call: Call<T>,
            transform: (T) -> R,
            default: T
        ): Either<Failure, R> {
            return try {
                val response = call.execute()

                when (response.isSuccessful) {
                    true -> {
                        val test = response.body().toString()
                        Either.Right(transform((response.body() ?: default)))
                    }
                    false -> Either.Left(ServerError)
                }
            } catch (exception: Throwable) {
                Either.Left(ServerError)
            }
        }

    }
}