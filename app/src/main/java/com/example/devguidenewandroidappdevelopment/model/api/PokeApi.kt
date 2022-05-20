package com.example.devguidenewandroidappdevelopment.model.api

import com.plcoding.jetpackcomposepokedex.data.remote.responses.Pokemon
import com.plcoding.jetpackcomposepokedex.data.remote.responses.PokemonList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String
    ): Pokemon
}
class PokeWebService {

    private var api: PokeApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(PokeApi::class.java)
    }

    suspend fun getPokemonList(limit: Int, offset: Int): PokemonList {
        return api.getPokemonList(limit, offset)
    }

    suspend fun getPokemonInfo(pokemonName: String): Pokemon {
        return api.getPokemonInfo(pokemonName)
    }
}