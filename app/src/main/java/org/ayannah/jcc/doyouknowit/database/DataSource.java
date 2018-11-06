package org.ayannah.jcc.doyouknowit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.ayannah.jcc.doyouknowit.models.Categories;

public class DataSource {

    public static Context mContext;
    public static SQLiteOpenHelper mDbHelper;
    public static SQLiteDatabase mDatabase;

    private static final String TAG = DataSource.class.getSimpleName();

    public DataSource(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(mContext);
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void open() {
        mDatabase = mDbHelper.getWritableDatabase();
        Log.i(TAG, "Database OPENED!");
    }

    public void close() {
        mDbHelper.close();
        Log.i(TAG, "Database CLOSED!");
    }

    public Categories createCategory(Categories categories){
        ContentValues values = categories.toValues();
        mDatabase.insert(CategoriesTable.TABLE_CATEGORIES,null,values);
        return categories;
    }

    public long getDBItems(){
        return DatabaseUtils.queryNumEntries(mDatabase,CategoriesTable.TABLE_CATEGORIES);
    }
}
