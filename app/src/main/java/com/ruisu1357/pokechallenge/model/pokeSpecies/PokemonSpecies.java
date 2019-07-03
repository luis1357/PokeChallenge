package com.ruisu1357.pokechallenge.model.pokeSpecies;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import com.ruisu1357.pokechallenge.model.general.FlavorText;

public class PokemonSpecies {

    @SerializedName("flavor_text_entries")
    ArrayList<FlavorText> flavor_text_entries;

    public ArrayList<FlavorText> getFlavor_text_entries() {
        return flavor_text_entries;
    }
}
