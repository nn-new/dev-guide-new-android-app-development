package com.example.devguidenewandroidappdevelopment.model

import com.example.devguidenewandroidappdevelopment.model.api.PokeWebService
import com.plcoding.jetpackcomposepokedex.data.remote.responses.Pokemon
import com.plcoding.jetpackcomposepokedex.data.remote.responses.PokemonList

class PokemonRepository(private val pokeWebService: PokeWebService = PokeWebService()) {
    suspend fun getPokemonList(): PokemonList {
        return pokeWebService.getPokemonList(20, 0)
    }

    suspend fun getPokemonInfo(pokemonName: String): Pokemon {
        return pokeWebService.getPokemonInfo(pokemonName)
    }
}