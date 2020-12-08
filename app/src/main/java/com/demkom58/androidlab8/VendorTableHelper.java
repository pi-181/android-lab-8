package com.demkom58.androidlab8;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class VendorTableHelper extends AbstractSQLiteHelper{

    public VendorTableHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version, "vendor");
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.d("myLogs", "| Create |" + database.toString());
        String query =
                "create table if not exists vendor (" +
                        " vendor_id INTEGER not null" +
                        "  constraint vendor_pk" +
                        "   primary key autoincrement," +
                        " vendor_name text not null" +
                        ");";
        database.execSQL(query);
    }

    public void insert(String vendorName) {
        ContentValues values = new ContentValues();
        values.put("vendor_name", vendorName);
        super.insert(values);
    }
}
