package org.ayannah.jcc.doyouknowit.models;

import android.content.ContentValues;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.ayannah.jcc.doyouknowit.database.CategoriesTable;

public class Categories implements Parcelable {


    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("count")
    private int count;

    public Categories() {
    }

    public Categories(String categoryId, String id, String title, int count) {
        this.id = id;
        this.title = title;
        this.count = count;
    }

    public ContentValues toValues() {
        ContentValues values = new ContentValues();
        values.put(CategoriesTable.COLUMN_CATEGORY_ID, id);
        values.put(CategoriesTable.COLUMN_TITLE, title);
        values.put(CategoriesTable.COLUMN_COUNT, count);


        return values;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeInt(this.count);
    }

    protected Categories(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.count = in.readInt();
    }

    public static final Parcelable.Creator<Categories> CREATOR = new Parcelable.Creator<Categories>() {
        @Override
        public Categories createFromParcel(Parcel source) {
            return new Categories(source);
        }

        @Override
        public Categories[] newArray(int size) {
            return new Categories[size];
        }
    };
}
