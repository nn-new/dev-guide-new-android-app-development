package com.example.devguidenewandroidappdevelopment.ui.pokemonlist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devguidenewandroidappdevelopment.model.PokedexListEntry
import com.example.devguidenewandroidappdevelopment.model.PokemonRepository
import com.plcoding.jetpackcomposepokedex.data.remote.responses.Result
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val repository: PokemonRepository = PokemonRepository()
) : ViewModel() {

    var pokemonList = mutableStateOf<List<PokedexListEntry>>(listOf())

    init {
        viewModelScope.launch {
            val result = getPokemonList()
            pokemonList.value = result.map { entry ->
                val (number, url) = getUrlPokemon(entry.url)
                PokedexListEntry(entry.name.replaceFirstChar { it.uppercase() }, url, number)
            }
        }
    }

    private suspend fun getPokemonList(): List<Result> {
        return repository.getPokemonList().results
    }

    private fun getUrlPokemon(url: String): Pair<Int, String> {
        val number = when {
            url.endsWith("/") -> url.dropLast(1).takeLastWhile { it.isDigit() }
            else -> url.takeLastWhile { it.isDigit() }
        }
        return Pair(
            number.toInt(),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
        )
    }
}