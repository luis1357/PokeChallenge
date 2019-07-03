package com.ruisu1357.pokechallenge.controller;

import com.ruisu1357.pokechallenge.model.pokeMoves.Type;
import com.ruisu1357.pokechallenge.model.pokeSpecies.PokemonSpecies;
import com.ruisu1357.pokechallenge.model.pokeStats.Ability;
import com.ruisu1357.pokechallenge.model.pokemon.Pokemon;
import com.ruisu1357.pokechallenge.model.pokemon.PokemonResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeapiService {

    @GET("pokemon")
    Call<PokemonResponse> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{id}")
    Call<Pokemon> obtenerPokemon(@Path("id") int id);

    @GET("pokemon-species/{id}")
    Call<PokemonSpecies> obtenerPokemonSpecies(@Path("id") int id);

    @GET("/ability/{id}")
    Call<Ability> obtenerHabilidad(@Path("id") int id);

    @GET("/type/{id}")
    Call<Type> obtenerTipo(@Path("id") int id);
}
