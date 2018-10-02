package org.ayannah.jcc.doyouknowit.models;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("clues")
    private Clues clues;

    public Category(String id, String title, Clues clues) {
        this.setId(id);
        this.setTitle(title);
        this.setClues(clues);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Clues getClues() {
        return clues;
    }

    public void setClues(Clues clues) {
        this.clues = clues;
    }
}
