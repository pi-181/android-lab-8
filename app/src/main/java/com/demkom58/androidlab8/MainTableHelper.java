package com.demkom58.androidlab8;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MainTableHelper extends AbstractSQLiteHelper {

    public MainTableHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version, "material_history");
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.d("myLogs", "| Create |" + database.toString());
        String query =
                "create table if not exists material_history (" +
                " transaction_id INTEGER not null" +
                "  constraint material_history_pk" +
                "   primary key autoincrement," +
                " material_id int" +
                "  constraint material_history_material_material_id_fk" +
                "   references material," +
                " vendor_id int" +
                "  constraint material_history_vendor_vendor_id_fk" +
                "   references vendor," +
                " receive_date text," +
                " consignment_number int," +
                " warehouse_number int," +
                " responsible_person text," +
                " material_count int," +
                " units text," +
                " costs int" +
                ");";

        database.execSQL(query);
    }

    public void insert(int materialId, int vendorId, String receiveDate, int consignmentNumber, int warehouseNumber,
                       String responsiblePerson, int materialCount, String units, int costs) {
        ContentValues values = new ContentValues();
        values.put("material_id", materialId);
        values.put("vendor_id", vendorId);
        values.put("receive_date", receiveDate);
        values.put("consignment_number", consignmentNumber);
        values.put("warehouse_number", warehouseNumber);
        values.put("responsible_person", responsiblePerson);
        values.put("material_count", materialCount);
        values.put("units", units);
        values.put("costs", costs);
        super.insert(values);
    }

}


