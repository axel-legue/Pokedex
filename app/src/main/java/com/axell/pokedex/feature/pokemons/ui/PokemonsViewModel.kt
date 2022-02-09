package com.axell.pokedex.feature.pokemons.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.axell.pokedex.core.platform.BaseViewModel
import com.axell.pokedex.feature.pokemons.PokemonSource
import com.axell.pokedex.feature.pokemons.entity.PokemonEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(private val pokemonSource: PokemonSource) : BaseViewModel() {

    fun getAllPokemons(): Flow<PagingData<PokemonEntity>> {
        return Pager(PagingConfig(pageSize = 20)) {
            pokemonSource
        }.flow.cachedIn(viewModelScope)
    }
}
