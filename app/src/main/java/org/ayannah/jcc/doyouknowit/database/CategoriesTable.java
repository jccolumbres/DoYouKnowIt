package org.ayannah.jcc.doyouknowit.database;

public class CategoriesTable {
    private static final String TABLE_CATEGORIES = "categories";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CATEGORYID = "categoryId";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_COUNT = "count";

    public static final String SQL_CREATE = "CREATE TABLE " + TABLE_CATEGORIES + "(" +
            COLUMN_ID + " TEXT PRIMARY KEY," +
            COLUMN_CATEGORYID + " TEXT," +
            COLUMN_TITLE + " TEXT," +
            COLUMN_COUNT + " INTEGER" + ");";

    public static final String SQL_DELETE = "DROP TABLE " + TABLE_CATEGORIES;

}
