package com.example.devguidenewandroidappdevelopment.ui.pokemondetail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devguidenewandroidappdevelopment.model.PokemonRepository
import com.plcoding.jetpackcomposepokedex.data.remote.responses.Pokemon
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val repository: PokemonRepository = PokemonRepository()
) : ViewModel() {

    private val _pokemonInfo: MutableState<Pokemon?> = mutableStateOf(null)

    fun getPokemonInfo(pokemonName: String): Pokemon? {
        viewModelScope.launch {
            _pokemonInfo.value = repository.getPokemonInfo(pokemonName.lowercase())
        }
        return _pokemonInfo.value
    }

}