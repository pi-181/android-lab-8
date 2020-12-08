package com.demkom58.androidlab8;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private MyOpenHelper myHelper = null;
    private EditText field1, field2, result;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        myHelper = new MyOpenHelper(this, "myDB", null, 1);

        field1 = findViewById(R.id.field1);
        field2 = findViewById(R.id.field2);
        result = findViewById(R.id.resultField);

        findViewById(R.id.buttonEnterData).setOnClickListener(this::insertIntoDatabase);
        findViewById(R.id.buttonOutputData).setOnClickListener(this::readDatabase);
        findViewById(R.id.buttonDelete).setOnClickListener(this::deleteDatabase);
    }

    public void insertIntoDatabase(View view) {
        if (field1.getText().toString().equals("") ||
                field2.getText().toString().equals(""))
            return;

        Log.d("myLogs",
                "Insert INTO DB (" + field1.getText().toString() + "," + field2.getText().toString() + ")");

        database = myHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(myHelper.FIELD_NAME_1, field1.getText().toString());
        values.put(myHelper.FIELD_NAME_2, field2.getText().toString());

        database.insert(myHelper.TABLE_NAME, null, values);
        database.close();

        field1.setText("");
        field2.setText("");
    }

    public void readDatabase(View view) {
        result.setText("");
        Log.d("myLogs", "READ FROM DB");
        database = myHelper.getReadableDatabase();

        String[] columns = {"_id", myHelper.FIELD_NAME_1, myHelper.FIELD_NAME_2};
        Cursor cursor = database.query(myHelper.TABLE_NAME, columns, null,
                null, null, null, "_id");

        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.moveToFirst()) {
                do {
                    result.setText(result.getText().toString() + "\n" + cursor.getString(0) + ") "
                            + cursor.getString(1) + "," + cursor.getString(2));
                } while (cursor.moveToNext());
            }
        }

        database.close();
    }

    public void deleteDatabase(View view) {
        Log.d("myLogs", "Delete Database");
        database = myHelper.getWritableDatabase();
        database.delete(myHelper.TABLE_NAME, null, null);
        database.close();
    }

}