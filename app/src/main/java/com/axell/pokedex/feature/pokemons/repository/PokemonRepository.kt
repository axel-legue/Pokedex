package com.axell.pokedex.feature.pokemons.repository

import com.axell.pokedex.core.constant.QUERY_LIMIT
import com.axell.pokedex.core.exception.Failure
import com.axell.pokedex.core.exception.Failure.ServerError
import com.axell.pokedex.core.functional.Either
import com.axell.pokedex.core.platform.NetworkHandler
import com.axell.pokedex.feature.pokemons.entity.PokemonEntity
import com.axell.pokedex.feature.pokemons.entity.PokemonsEntity
import com.axell.pokedex.feature.pokemons.service.PokemonService
import javax.inject.Inject

interface PokemonRepository {
    suspend fun pokemons(page: Int): Either<Failure, List<PokemonEntity>>

    class Network @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: PokemonService
    ) : PokemonRepository {

        override suspend fun pokemons(page: Int): Either<Failure, List<PokemonEntity>> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> request(
                    service.fetchPokemons(offset = page * QUERY_LIMIT, limit = QUERY_LIMIT),
                    {
                        it.results
                    },
                    default = PokemonsEntity(count = 0, next = null, previous = null, results = listOf())
                )
                false -> Either.Left(Failure.NetworkConnectionError)
            }
        }

        private fun <T, R> request(
            response: T,
            transform: (T) -> R,
            default: T
        ): Either<Failure, R> {
            return try {
                Either.Right(transform((response ?: default)))
            } catch (exception: Throwable) {
                Either.Left(ServerError)
            }
        }
    }
}
