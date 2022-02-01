package com.axell.pokedex.feature.pokemons.usecase

import com.axell.pokedex.core.exception.Failure
import com.axell.pokedex.core.functional.Either
import com.axell.pokedex.core.interactor.UseCase
import com.axell.pokedex.feature.pokemons.entity.PokemonEntity
import com.axell.pokedex.feature.pokemons.repository.PokemonRepository
import javax.inject.Inject

class FetchPokemons @Inject constructor(private val pokemonRepository: PokemonRepository) : UseCase<List<PokemonEntity>, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, List<PokemonEntity>> = pokemonRepository.pokemons()
}
