package com.axell.pokedex.feature.pokemoninfo.usecase

import com.axell.pokedex.core.exception.Failure
import com.axell.pokedex.core.functional.Either
import com.axell.pokedex.core.interactor.UseCase
import com.axell.pokedex.feature.pokemoninfo.entity.PokemonInfoEntity
import com.axell.pokedex.feature.pokemoninfo.repository.PokemonInfoRepository
import javax.inject.Inject

class FetchPokemonInfo @Inject constructor(
    private val pokemonInfoRepository: PokemonInfoRepository
) : UseCase<PokemonInfoEntity, String>() {
    override suspend fun run(params: String): Either<Failure, PokemonInfoEntity> = pokemonInfoRepository.pokemonInfo(params)
}
