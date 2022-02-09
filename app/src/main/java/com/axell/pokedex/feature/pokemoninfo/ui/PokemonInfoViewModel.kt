package com.axell.pokedex.feature.pokemoninfo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.axell.pokedex.core.di.DefaultDispatcher
import com.axell.pokedex.core.platform.BaseViewModel
import com.axell.pokedex.feature.pokemoninfo.entity.PokemonInfoEntity
import com.axell.pokedex.feature.pokemoninfo.usecase.FetchPokemonInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PokemonInfoViewModel @Inject constructor(
    private val fetchPokemonInfo: FetchPokemonInfo,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : BaseViewModel() {
    private var _pokemonInfo: MutableLiveData<PokemonInfoEntity> = MutableLiveData()
    fun pokemonInfo(): LiveData<PokemonInfoEntity> = _pokemonInfo

    fun loadPokemonInfo(name: String) = fetchPokemonInfo(params = name, scope = viewModelScope, dispatcher = defaultDispatcher) {
        Timber.d("[POKEMON] loadPokemonInfo name: $name")
        it.fold(::handleFailure, ::handlePokemonInfo)
    }

    private fun handlePokemonInfo(pokemonInfo: PokemonInfoEntity) {
        Timber.d("[POKEMON] handlePokemonInfo info: $pokemonInfo")
        _pokemonInfo.postValue(pokemonInfo)
    }
}
