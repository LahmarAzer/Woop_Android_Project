package com.esprit.validationlastwoop;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VariousActivity extends AppCompatActivity {
    CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6;
    Button btnnext ;
    private DatabaseHelper databaseHelper; // Declare DatabaseHelper


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.various_interface);
        databaseHelper = new DatabaseHelper(this);

        checkBox1=findViewById(R.id.checkBox1);
        checkBox2=findViewById(R.id.checkBox2);
        checkBox3=findViewById(R.id.checkBox3);
        checkBox4=findViewById(R.id.checkBox4);
        checkBox5=findViewById(R.id.checkBox5);
        checkBox6=findViewById(R.id.checkBox6);
        btnnext=findViewById(R.id.next);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder selectedValues = new StringBuilder();

                if (checkBox1.isChecked()) {
                    selectedValues.append("construction, ");
                }
                if (checkBox2.isChecked()) {
                    selectedValues.append("Gardening, ");
                }
                if (checkBox3.isChecked()) {
                    selectedValues.append("educating, ");
                }
                if (checkBox4.isChecked()) {
                    selectedValues.append("sport, ");
                }
                if (checkBox5.isChecked()) {
                    selectedValues.append("basbysiting, ");
                }
                if (checkBox6.isChecked()) {
                    selectedValues.append("designer, ");
                }

                String selectedData = selectedValues.toString();
                if (selectedData.endsWith(", ")) {
                    selectedData = selectedData.substring(0, selectedData.length() - 6);
                }

                Log.d("SelectedData", selectedData);
                Toast.makeText(getApplicationContext(), "Selected values: " + selectedData, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(VariousActivity.this, AvailabiltyInterface.class);
                intent.putExtra("selectedData", selectedData);

                startActivity(intent);
                saveDataToDatabase(selectedData);

            }
        });

    }

    private void saveDataToDatabase(String selectedData) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_CONSTRUCTION, checkBox1.isChecked() ? "Yes" : "No");
        values.put(DatabaseHelper.COLUMN_GARDENING, checkBox2.isChecked() ? "Yes" : "No");
        values.put(DatabaseHelper.COLUMN_EDUCATING, checkBox3.isChecked() ? "Yes" : "No");
        values.put(DatabaseHelper.COLUMN_SPORTS, checkBox4.isChecked() ? "Yes" : "No");
        values.put(DatabaseHelper.COLUMN_BABYSITTING, checkBox5.isChecked() ? "Yes" : "No");
        values.put(DatabaseHelper.COLUMN_DESIGNER, checkBox6.isChecked() ? "Yes" : "No");
        long newRowId = db.insert(DatabaseHelper.TABLE_TRIPS, null, values);
        if (newRowId != -1) {
            Toast.makeText(getApplicationContext(), "Data saved to database with ID: " + newRowId, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Error saving data to database", Toast.LENGTH_SHORT).show();
        }

    }

}