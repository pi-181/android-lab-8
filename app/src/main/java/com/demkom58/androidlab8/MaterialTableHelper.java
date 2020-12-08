package com.demkom58.androidlab8;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MaterialTableHelper extends AbstractSQLiteHelper {

    public MaterialTableHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version, "material");
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.d("myLogs", "| Create |" + database.toString());
        String query =
                "create table if not exists material (" +
                " material_id INTEGER not null" +
                "  constraint material_pk" +
                "   primary key autoincrement," +
                " material_name text not null" +
                ");";
        database.execSQL(query);
    }

    public void insert(String materialName) {
        ContentValues values = new ContentValues();
        values.put("material_name", materialName);
        super.insert(values);
    }
}
