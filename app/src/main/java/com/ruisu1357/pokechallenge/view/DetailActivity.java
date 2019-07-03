package com.ruisu1357.pokechallenge.view;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ruisu1357.pokechallenge.R;
import com.ruisu1357.pokechallenge.controller.PokeClient;
import com.ruisu1357.pokechallenge.controller.PokeapiService;
import com.ruisu1357.pokechallenge.model.pokeSpecies.PokemonSpecies;
import com.ruisu1357.pokechallenge.model.pokemon.Pokemon;
import com.ruisu1357.pokechallenge.model.pokemon.Stats;
import com.ruisu1357.pokechallenge.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {


    private static final String TAG = "Poke ";
    private int id;

    private TextView pokeName, pokeType, pokeType2, pokeInfo, pokeStats, pokeDescription;
    private ImageView pokeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }

        pokeName = findViewById(R.id.pokeName);
        pokeType = findViewById(R.id.pokeType);
        pokeType2 = findViewById(R.id.pokeType2);
        pokeInfo = findViewById(R.id.pokeInfo);
        pokeStats = findViewById(R.id.pokeStats);
        pokeDescription = findViewById(R.id.pokeDescription);
        pokeImg = findViewById(R.id.pokeSprite);

        getPokemon(id);
    }

    private void getPokemon(final int pokeId){
        final PokeapiService service = PokeClient.getRetrofit().create(PokeapiService.class);
        Call<Pokemon> pokemonCall = service.obtenerPokemon(pokeId);

        pokemonCall.enqueue(new Callback<Pokemon>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()){
                    Pokemon pokemonResponse = response.body();
                    assert pokemonResponse != null;
                    pokeName.setText(pokemonResponse.getName());

                    if (pokemonResponse.getTypes().size() == 1) {
                        pokeType.setText(pokemonResponse.getTypes().get(0).getType().getName());

                    }else{
                        pokeType.setText(pokemonResponse.getTypes().get(0).getType().getName());
                        pokeType2.setText(pokemonResponse.getTypes().get(1).getType().getName());
                    }

                    Glide.with(DetailActivity.this)
                            .load(Constants.SPRITES_URL + pokeId +".png")
                            .into(pokeImg);

                    pokeInfo.setText(
                                    "Weight: " + pokemonResponse.getWeight()+"\n"+
                                    "Height: " + pokemonResponse.getHeight()+"\n"+
                                    "Species: " + pokemonResponse.getSpecies().getName()+"\n"+
                                    "Abilities: \n"
                    );

                    if (pokemonResponse.getAbilities().size() == 1){
                        pokeInfo.append("\t\t\t" + pokemonResponse.getAbilities().get(0).getAbility().getName() + "\n");
                    }else {
                        pokeInfo.append("\t\t\t" + pokemonResponse.getAbilities().get(0).getAbility().getName() + "\n");
                        pokeInfo.append("\t\t\t" + pokemonResponse.getAbilities().get(1).getAbility().getName() + "\n");
                    }

                    for (Stats stats : pokemonResponse.getStats()) {
                        pokeStats.append(
                                stats.getStat().getName()+ " " + stats.getBase_stat() + "\n");
                    }
                }else{
                    Log.e(TAG, "onResponse:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });

        Call<PokemonSpecies> pokemonSpeciesCall = service.obtenerPokemonSpecies(pokeId);

        pokemonSpeciesCall.enqueue(new Callback<PokemonSpecies>() {
            @Override
            public void onResponse(Call<PokemonSpecies> call, Response<PokemonSpecies> response) {
                if (response.isSuccessful()){
                    PokemonSpecies pokemonSpecies = response.body();
                    for(int i=0 ; i <= pokemonSpecies.getFlavor_text_entries().size(); i++){
                        if (pokemonSpecies.getFlavor_text_entries().get(i).getLanguage().getName().equals("en")){
                            pokeDescription.setText(pokemonSpecies.getFlavor_text_entries().get(i).getFlavor_text());
                            break;
                        }else{
                            pokeDescription.setText("");
                        }
                    }
                }else {
                    Log.e(TAG,  "onResponse: " + response.errorBody());

                }
            }

            @Override
            public void onFailure(Call<PokemonSpecies> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }


        });
    }
}
