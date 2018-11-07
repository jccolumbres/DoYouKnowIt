package org.ayannah.jcc.doyouknowit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import org.ayannah.jcc.doyouknowit.activity.ListCategories;
import org.ayannah.jcc.doyouknowit.models.Categories;

import java.util.ArrayList;
import java.util.List;

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

    public void open()  {
        mDatabase = mDbHelper.getWritableDatabase();
        Log.i(TAG, "Database OPENED!");
    }

    public void close() {
        mDbHelper.close();
        Log.i(TAG, "Database CLOSED!");
    }

    public Categories createCategory(Categories categories) {
        ContentValues values = categories.toValues();
        mDatabase.insert(CategoriesTable.TABLE_CATEGORIES, null, values);
        return categories;
    }

    public void populateDB(List<Categories> dataSource){
        long count = getDBItemsCount();
        if (count == 0) {
            for (Categories category : dataSource) {
                createCategory(category);
                Log.i("XHITE","Category inserted: " + category.getTitle());
            }
        }else{
            Toast.makeText(mContext, "Data already saved", Toast.LENGTH_SHORT).show();
        }
    }
    public long getDBItemsCount() {
        return DatabaseUtils.queryNumEntries(mDatabase, CategoriesTable.TABLE_CATEGORIES);
    }

    public List<Categories> getDBItemsList(){
        List<Categories> categoriesList = new ArrayList<>();

        Cursor cursor = mDatabase.query(CategoriesTable.TABLE_CATEGORIES, CategoriesTable.ALL_COLUMNS,
                null, null, null, null, null);

        while(cursor.moveToNext()){
            Categories categories = new Categories();
            categories.setId(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_CATEGORY_ID)));
            categories.setTitle(cursor.getString(cursor.getColumnIndex(CategoriesTable.COLUMN_TITLE)));
            categories.setCount(cursor.getInt(cursor.getColumnIndex(CategoriesTable.COLUMN_COUNT)));
            categoriesList.add(categories);
        }
        cursor.close();
        return categoriesList;
    }
}
