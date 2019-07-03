package com.ruisu1357.pokechallenge.model.general;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Names {

    @SerializedName("name")
    private String name;

    @SerializedName("lenguage")
    private ArrayList<NameUrl> lenguage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<NameUrl> getLenguage() {
        return lenguage;
    }

    public void setLenguage(ArrayList<NameUrl> lenguage) {
        this.lenguage = lenguage;
    }
}
