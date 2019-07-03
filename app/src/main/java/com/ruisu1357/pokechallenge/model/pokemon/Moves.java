package com.ruisu1357.pokechallenge.model.pokemon;

import com.google.gson.annotations.SerializedName;

import com.ruisu1357.pokechallenge.model.general.NameUrl;

public class Moves {

    @SerializedName("move")
    private NameUrl move;

    public NameUrl getMove() {
        return move;
    }
}
