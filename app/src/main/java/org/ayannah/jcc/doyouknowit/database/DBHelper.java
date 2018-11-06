package org.ayannah.jcc.doyouknowit.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.ayannah.jcc.doyouknowit.models.Categories;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "categories.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CategoriesTable.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CategoriesTable.SQL_DELETE);
        onCreate(db);
    }
}
