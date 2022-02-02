package com.axell.pokedex.feature.pokemons.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.axell.pokedex.core.interactor.UseCase
import com.axell.pokedex.core.platform.BaseViewModel
import com.axell.pokedex.feature.pokemons.entity.PokemonEntity
import com.axell.pokedex.feature.pokemons.usecase.FetchPokemons
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(private val fetchPokemons: FetchPokemons) : BaseViewModel() {

    private var _pokemons: MutableLiveData<List<PokemonEntity>> = MutableLiveData()
    fun pokemons(): LiveData<List<PokemonEntity>> = _pokemons

    fun loadPokemons() = fetchPokemons(UseCase.None(), viewModelScope) {
        it.fold(::handleFailure, ::handlePokemonList)
    }

    private fun handlePokemonList(pokemons: List<PokemonEntity>) {
        Timber.d("pokemons: $pokemons ")
        _pokemons.postValue(pokemons)
    }
}
