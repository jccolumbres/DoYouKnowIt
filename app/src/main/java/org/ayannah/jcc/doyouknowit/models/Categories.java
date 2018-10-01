package org.ayannah.jcc.doyouknowit.models;

public class Categories {
    private int id;
    private String title;
    private int count;

    public Categories(int id, String title, int count) {
        this.setId(id);
        this.setTitle(title);
        this.setCount(count);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
