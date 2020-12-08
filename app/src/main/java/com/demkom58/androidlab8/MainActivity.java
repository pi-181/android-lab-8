package com.demkom58.androidlab8;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private VendorTableHelper vendorTable = null;
    private MaterialTableHelper materialTable = null;
    private MainTableHelper mainTable = null;

    private EditText
            mhMaterialIdInput, mhVendorIdInput,
            mhReceiveDateInput, mhConsigmentNumberInput,
            mhWarehouseNumberInput, mhReponsiblePersonInput,
            mhCountInput, mhUnitsInput,
            mhCostsInput, mhResultInput;
    private EditText vVendorNameInput, vResultInput;
    private EditText mMaterialNameInput, mResultInput;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        final String databaseName = "myDB";
        materialTable = new MaterialTableHelper(this, databaseName, null, 1);
        materialTable.deleteTable();
        materialTable.onCreate(materialTable.getWritableDatabase());
        vendorTable = new VendorTableHelper(this, databaseName, null, 1);
        vendorTable.deleteTable();
        vendorTable.onCreate(vendorTable.getWritableDatabase());
        materialTable = new MaterialTableHelper(this, databaseName, null, 1);
        mainTable = new MainTableHelper(this, databaseName, null, 1);
        mainTable.deleteTable();
        mainTable.onCreate(mainTable.getWritableDatabase());

        mhMaterialIdInput = findViewById(R.id.mhMaterialIdInput);
        mhVendorIdInput = findViewById(R.id.mhVendorIdInput);
        mhReceiveDateInput = findViewById(R.id.mhReceiveDateInput);
        mhConsigmentNumberInput = findViewById(R.id.mhConsigmentNumberInput);
        mhWarehouseNumberInput = findViewById(R.id.mhWarehouseNumberInput);
        mhReponsiblePersonInput = findViewById(R.id.mhReponsiblePersonInput);
        mhCountInput = findViewById(R.id.mhCountInput);
        mhUnitsInput = findViewById(R.id.mhUnitsInput);
        mhCostsInput = findViewById(R.id.mhCostsInput);
        mhResultInput = findViewById(R.id.mhResultField);

        vVendorNameInput = findViewById(R.id.vVendorNameInput);
        vResultInput = findViewById(R.id.vResultField);

        mMaterialNameInput = findViewById(R.id.mMaterialNameInput);
        mResultInput = findViewById(R.id.mResultField);

        findViewById(R.id.mhButtonEnterData).setOnClickListener(this::insertIntoMhDatabase);
        findViewById(R.id.mhButtonOutputData).setOnClickListener(v -> mhResultInput.setText(mainTable.getContent()));
        findViewById(R.id.mhButtonDelete).setOnClickListener(v -> mainTable.deleteAll());

        findViewById(R.id.vButtonEnterData).setOnClickListener(this::insertIntoVDatabase);
        findViewById(R.id.vButtonOutputData).setOnClickListener(v -> vResultInput.setText(vendorTable.getContent()));
        findViewById(R.id.vButtonDelete).setOnClickListener(v -> vendorTable.deleteAll());

        findViewById(R.id.mButtonEnterData).setOnClickListener(this::insertIntoMDatabase);
        findViewById(R.id.mButtonOutputData).setOnClickListener(v -> mResultInput.setText(materialTable.getContent()));
        findViewById(R.id.mButtonDelete).setOnClickListener(v -> materialTable.deleteAll());
    }

    public void insertIntoMhDatabase(View view) {
        try {
            mainTable.insert(
                    Integer.parseInt(mhMaterialIdInput.getText().toString()),
                    Integer.parseInt(mhVendorIdInput.getText().toString()),
                    mhReceiveDateInput.getText().toString(),
                    Integer.parseInt(mhConsigmentNumberInput.getText().toString()),
                    Integer.parseInt(mhWarehouseNumberInput.getText().toString()),
                    mhReponsiblePersonInput.getText().toString(),
                    Integer.parseInt(mhCountInput.getText().toString()),
                    mhUnitsInput.getText().toString(),
                    Integer.parseInt(mhCostsInput.getText().toString())
            );
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();;
        }
    }

    public void insertIntoVDatabase(View view) {
        try {
            String vendor = vVendorNameInput.getText().toString();
            if (vendor.isEmpty()) return;
            vendorTable.insert(vendor);
            vVendorNameInput.setText("");
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void insertIntoMDatabase(View view) {
        try {
            String material = mMaterialNameInput.getText().toString();
            if (material.isEmpty()) return;
            materialTable.insert(material);
            mMaterialNameInput.setText("");
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();;
        }
    }

}