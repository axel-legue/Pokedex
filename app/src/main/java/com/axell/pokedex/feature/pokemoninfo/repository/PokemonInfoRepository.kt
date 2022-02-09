package com.axell.pokedex.feature.pokemoninfo.repository

import com.axell.pokedex.core.exception.Failure
import com.axell.pokedex.core.functional.Either
import com.axell.pokedex.core.platform.NetworkHandler
import com.axell.pokedex.feature.pokemoninfo.entity.PokemonInfoEntity
import com.axell.pokedex.feature.pokemoninfo.service.PokemonInfoService
import javax.inject.Inject

interface PokemonInfoRepository {
    suspend fun pokemonInfo(name: String): Either<Failure, PokemonInfoEntity>

    class Network @Inject constructor(
        private val networkHandler: NetworkHandler,
        private val service: PokemonInfoService
    ) : PokemonInfoRepository {

        override suspend fun pokemonInfo(name: String): Either<Failure, PokemonInfoEntity> {
            return when (networkHandler.isNetworkAvailable()) {
                true -> request(
                    response = service.fetchPokemonInfo(name = name),
                    default = PokemonInfoEntity(
                        id = 0,
                        name = "",
                        height = 0,
                        weight = 0,
                        experience = 0,
                        types = listOf(),
                        hp = 0,
                        attack = 0,
                        defense = 0,
                        speed = 0,
                        exp = 0
                    ),
                    transform = {
                        it
                    }
                )
                false -> Either.Left(Failure.NetworkConnectionError)
            }
        }

        private fun <T, R> request(
            response: T,
            default: T,
            transform: (T) -> R
        ): Either<Failure, R> {
            return try {
                Either.Right(transform((response ?: default)))
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError)
            }
        }
    }
}
