package com.ruisu1357.pokechallenge.model.pokemon;

import com.google.gson.annotations.SerializedName;

import com.ruisu1357.pokechallenge.model.general.NameUrl;

public class Abilities {

    @SerializedName("ability")
    private NameUrl ability;

    @SerializedName("is_hidden")
    private Boolean is_hidden;

    @SerializedName("slot")
    private double slot;

    public NameUrl getAbility() {
        return ability;
    }

    public Boolean getIs_hidden() {
        return is_hidden;
    }

    public double getSlot() {
        return slot;
    }
}
