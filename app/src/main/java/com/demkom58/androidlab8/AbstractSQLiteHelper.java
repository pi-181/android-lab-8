package com.demkom58.androidlab8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public abstract class AbstractSQLiteHelper extends SQLiteOpenHelper {
    private final String tableName;
    protected Context context;

    public AbstractSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, String tableName) {
        super(context, name, factory, version);
        this.context = context;
        this.tableName = tableName;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.d("myLogs", "| Upgrade |" + database.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.d("myLogs", "| Upgrade |" + database.toString());
    }

    public void deleteTable() {
        getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + tableName);
    }

    public void deleteAll() {
        Log.d("myLogs", "Delete Database");
        SQLiteDatabase database = getWritableDatabase();
        database.delete(tableName, null, null);
        database.close();
    }

    public String getContent() {
        Log.d("myLogs", "READ FROM DB");
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.query(
                tableName, null, null, null, null, null, null
        );

        StringBuilder result = new StringBuilder();
        if (cursor != null) {
            cursor.moveToFirst();
            int columnCount = cursor.getColumnCount();
            if (cursor.moveToFirst()) {
                do {
                    result.append("\n").append(cursor.getString(0)).append(") ");
                    for (int i = 1; i < columnCount; i++)
                        result.append(i != 1 ? "," : "").append(cursor.getString(i));
                } while (cursor.moveToNext());
            }
        }

        database.close();
        return result.toString();
    }

    public void insert(ContentValues values) {
        Log.d("myLogs", "Insert INTO DB (" + values.toString() + ")");
        SQLiteDatabase database = getWritableDatabase();
        database.insert(tableName, null, values);
        database.close();
    }

    public String getTableName() {
        return tableName;
    }
}
