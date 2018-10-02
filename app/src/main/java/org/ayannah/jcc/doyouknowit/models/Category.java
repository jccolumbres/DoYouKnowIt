package org.ayannah.jcc.doyouknowit.models;

public class Category {
    private String id;
    private String title;
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
