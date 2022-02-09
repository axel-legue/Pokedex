package com.axell.pokedex.feature.pokemons

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.axell.pokedex.core.functional.getOrElse
import com.axell.pokedex.feature.pokemons.entity.PokemonEntity
import com.axell.pokedex.feature.pokemons.repository.PokemonRepository
import javax.inject.Inject

class PokemonSource @Inject constructor(private val pokemonRepository: PokemonRepository) : PagingSource<Int, PokemonEntity>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonEntity>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonEntity> {
        val nextPage = params.key ?: 1
        val pokemonList = pokemonRepository.pokemons(nextPage)

        return if (pokemonList.isLeft) {
            LoadResult.Error(Exception("Error"))
        } else {
            LoadResult.Page(
                data = pokemonList.getOrElse(emptyList()),
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage.plus(1)
            )
        }
    }
}
