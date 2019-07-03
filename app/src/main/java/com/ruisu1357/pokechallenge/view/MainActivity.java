package com.ruisu1357.pokechallenge.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ruisu1357.pokechallenge.R;
import com.ruisu1357.pokechallenge.controller.PokeClient;
import com.ruisu1357.pokechallenge.controller.PokeapiService;
import com.ruisu1357.pokechallenge.model.general.NameUrl;
import com.ruisu1357.pokechallenge.model.pokemon.PokemonResponse;
import com.ruisu1357.pokechallenge.utils.PokemonListAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PokemonListAdapter.ItemClickListener{

    private static final String TAG = "POKEDEX";

    private RecyclerView recyclerView;
    private PokemonListAdapter pokemonListAdapter;

    private int offset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id. recyclerView);
        pokemonListAdapter = new PokemonListAdapter(this, this);
        recyclerView.setAdapter(pokemonListAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);

        offset = 0;
        GetData(offset);

    }

    private void GetData(int offset) {
        PokeapiService service = PokeClient.getRetrofit().create(PokeapiService.class);
        Call<PokemonResponse> pokemonRespuestaCall = service.obtenerListaPokemon(807,offset);

        pokemonRespuestaCall.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {

                if(response.isSuccessful()){
                    PokemonResponse pokemonResponse = response.body();
                    assert pokemonResponse != null;
                    ArrayList<NameUrl> pokeList = pokemonResponse.getResults();

                    pokemonListAdapter.addPokemonList(pokeList);

                }else{
                    Log.e(TAG, "onResponse :" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Log.e(TAG, "onFailure :" + t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(int clickedItemIndex) {
        Intent intentPokemonActivity = new Intent(MainActivity.this, DetailActivity.class);
        intentPokemonActivity.putExtra("id", clickedItemIndex+1);
        startActivity(intentPokemonActivity);
    }
}
