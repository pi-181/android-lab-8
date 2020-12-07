package com.demkom58.androidlab8;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyOpenHelper extends SQLiteOpenHelper {

    public String TABLE_NAME = "first_table";
    public String FIELD_NAME_1 = "first_field";
    public String FIELD_NAME_2 = "second_field";

    MyOpenHelper(Context ct, String nm, SQLiteDatabase.CursorFactory cf, int vs) {
        super(ct, nm, cf, vs);
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        Log.d("myLogs", "| Upgrade |" + DB.toString());
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        Log.d("myLogs", "| Create |" + DB.toString());
        String query = "create table " + TABLE_NAME + " ( _id integer primary key autoincrement, "
                + FIELD_NAME_1 + " TEXT, " + FIELD_NAME_2 + " TEXT)";
        DB.execSQL(query);
    }
}
