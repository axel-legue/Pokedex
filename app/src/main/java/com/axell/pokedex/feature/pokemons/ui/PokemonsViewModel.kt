package com.axell.pokedex.feature.pokemons.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.axell.pokedex.core.platform.BaseViewModel
import com.axell.pokedex.feature.pokemons.PokemonSource
import com.axell.pokedex.feature.pokemons.entity.PokemonEntity
import com.axell.pokedex.feature.pokemons.usecase.FetchPokemons
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

@HiltViewModel
class PokemonsViewModel @Inject constructor(private val fetchPokemons: FetchPokemons, private val pokemonSource: PokemonSource) : BaseViewModel() {

    private var _pokemons: MutableLiveData<List<PokemonEntity>> = MutableLiveData()
    fun pokemons(): LiveData<List<PokemonEntity>> = _pokemons

    fun loadPokemons() = fetchPokemons(params = 1, viewModelScope) {
        it.fold(::handleFailure, ::handlePokemonList)
    }

    fun getAllPokemons(): Flow<PagingData<PokemonEntity>> {
        return Pager(PagingConfig(pageSize = 20)) {
            pokemonSource
        }.flow
    }

    private fun handlePokemonList(pokemons: List<PokemonEntity>) {
        Timber.d("pokemons: $pokemons ")
        _pokemons.postValue(pokemons)
    }
}
