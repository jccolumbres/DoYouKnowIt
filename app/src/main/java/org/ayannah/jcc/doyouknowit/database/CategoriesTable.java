package org.ayannah.jcc.doyouknowit.database;

public class CategoriesTable {
    public static final String TABLE_CATEGORIES = "categories";
    public static final String COLUMN_CATEGORY_ID = "categoryId";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_COUNT = "count";

    public static final String[] ALL_COLUMNS = {COLUMN_CATEGORY_ID, COLUMN_TITLE, COLUMN_COUNT};
    
    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_CATEGORIES + "(" +
            COLUMN_CATEGORY_ID + " TEXT PRIMARY KEY," +
            COLUMN_TITLE + " TEXT," +
            COLUMN_COUNT + " INTEGER" + ");";

    public static final String SQL_DELETE = "DROP TABLE " + TABLE_CATEGORIES;

}
