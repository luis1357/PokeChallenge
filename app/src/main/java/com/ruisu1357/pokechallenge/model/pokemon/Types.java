package com.ruisu1357.pokechallenge.model.pokemon;

import com.google.gson.annotations.SerializedName;

import com.ruisu1357.pokechallenge.model.general.NameUrl;

public class Types {

    @SerializedName("slot")
    private double slot;

    private NameUrl type;

    public double getSlot() {
        return slot;
    }

    public NameUrl getType() {
        return type;
    }
}
