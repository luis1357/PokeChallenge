package com.ruisu1357.pokechallenge.model.pokemon;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import com.ruisu1357.pokechallenge.model.general.NameUrl;

public class Pokemon {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("weight")
    private int weight;

    @SerializedName("height")
    private int height;

    @SerializedName("species")
    private NameUrl species;

    @SerializedName("types")
    private ArrayList<Types> types;

    @SerializedName("sprites")
    private Sprites sprites;


    @SerializedName("abilities")
    private ArrayList<Abilities> abilities;

    @SerializedName("moves")
    private ArrayList<Moves> moves;

    @SerializedName("stats")
    private ArrayList<Stats> stats;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public NameUrl getSpecies() {
        return species;
    }

    public ArrayList<Types> getTypes() {
        return types;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public ArrayList<Abilities> getAbilities() {
        return abilities;
    }

    public ArrayList<Moves> getMoves() {
        return moves;
    }

    public ArrayList<Stats> getStats() {
        return stats;
    }
}
