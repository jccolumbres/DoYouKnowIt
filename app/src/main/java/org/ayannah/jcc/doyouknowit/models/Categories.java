package org.ayannah.jcc.doyouknowit.models;

import com.google.gson.annotations.SerializedName;

public class Categories {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("count")
    private int count;

    public Categories(String id, String title, int count) {
        this.setId(id);
        this.setTitle(title);
        this.setCount(count);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
