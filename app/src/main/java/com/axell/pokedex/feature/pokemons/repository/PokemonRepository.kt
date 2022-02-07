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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

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
                false -> {
                    Either.Left(Failure.NetworkConnectionError)
                }
            }
        }

        private suspend fun <T, R> request(
            call: Call<T>,
            transform: (T) -> R,
            default: T
        ): Either<Failure, R> {
            return try {
                withContext(Dispatchers.Default) {
                    val response = call.execute()
                    when (response.isSuccessful) {
                        true -> Either.Right(transform((response.body() ?: default)))
                        false -> Either.Left(ServerError)
                    }
                }
            } catch (exception: Throwable) {
                Either.Left(ServerError)
            }
        }
    }
}
