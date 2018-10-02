package org.ayannah.jcc.doyouknowit.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {


    @SerializedName("clues")
    private List<Clues> clues;


    public Category(List<Clues> clues) {
        this.setClues(clues);
    }

    public List<Clues> getClues() {
        return clues;
    }

    public void setClues(List<Clues> clues) {
        this.clues = clues;
    }
}