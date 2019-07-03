package com.ruisu1357.pokechallenge.model.pokeMoves;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import com.ruisu1357.pokechallenge.model.general.Names;

public class Type {

    @SerializedName("names")
    private ArrayList<Names> names;

    public ArrayList<Names> getNames() {
        return names;
    }
}
