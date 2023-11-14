package com.esprit.validationlastwoop;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Optioninterface extends AppCompatActivity {

    private DatabaseHelper DatabaseHelper;



    private RelativeLayout relativeLayout;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private Button Next_button;
    private SearchView searchView;
    private GridLayout gridLayout1;
    private GridLayout gridLayout2;
    private GridLayout gridLayout3;
    private GridLayout gridLayout4;
    private GridLayout gridLayout5;
    private GridLayout gridLayout6;
    private ImageView imageView;
    private ImageView imageView22;
    private ImageView imageView33;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private TextView chooseWork;
    private LinearLayout buttonsLayout;
    private Button buttonOne;
    private Button buttonTwo;
    private Button buttonMoreThanTwo;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_interface);

        DatabaseHelper = new DatabaseHelper(this);


        buttonOne=findViewById(R.id.buttonOne);
        buttonTwo=findViewById(R.id.buttonTwo);
        buttonMoreThanTwo=findViewById(R.id.buttonMoreThanTwo);

        checkBox1=findViewById(R.id.checkBox1);
        checkBox2=findViewById(R.id.checkBox2);
        checkBox3=findViewById(R.id.checkBox3);
        checkBox4=findViewById(R.id.checkBox4);
        checkBox5=findViewById(R.id.checkBox5);
        checkBox6=findViewById(R.id.checkBox6);

        Next_button=findViewById(R.id.Next_button);

        Next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder selectedValues = new StringBuilder();

                if (checkBox1.isChecked()) {
                    selectedValues.append("Wifi, yes");
                }
                if (checkBox2.isChecked()) {
                    selectedValues.append("Smoke detector , yes");
                }
                if (checkBox3.isChecked()) {
                    selectedValues.append("PetHouse, yes");
                }
                if (checkBox4.isChecked()) {
                    selectedValues.append("washing machine, yes");
                }
                if (checkBox5.isChecked()) {
                    selectedValues.append("bed, yes");
                }
                if (checkBox6.isChecked()) {
                    selectedValues.append("kitchen, yes");
                }

                String selectedData = selectedValues.toString();
                if (selectedData.endsWith(", ")) {
                    selectedData = selectedData.substring(0, selectedData.length() - 6);
                }


                String fromDate = getIntent().getStringExtra("fromDate");
                String toDate = getIntent().getStringExtra("toDate");

                if (fromDate != null && toDate != null) {
                    Toast.makeText(Optioninterface.this, "From Date: " + fromDate + "\nTo Date: " + toDate, Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(Optioninterface.this, tripDetails.class);
                startActivity(intent);
                saveDataToDatabase(selectedData);


            }
        });

    }
    private void saveDataToDatabase(String selectedData) {
        SQLiteDatabase db = DatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_WIFI, checkBox1.isChecked() ? "Yes" : "No");
        values.put(DatabaseHelper.COLUMN_SMOKE, checkBox2.isChecked() ? "Yes" : "No");
        values.put(DatabaseHelper.COLUMN_PETHOUSE, checkBox3.isChecked() ? "Yes" : "No");
        values.put(DatabaseHelper.COLUMN_WASHINGMACHINE, checkBox4.isChecked() ? "Yes" : "No");
        values.put(DatabaseHelper.COLUMN_BED, checkBox5.isChecked() ? "Yes" : "No");
        values.put(DatabaseHelper.COLUMN_KITCHEN, checkBox6.isChecked() ? "Yes" : "No");
        long newRowId = db.insert(DatabaseHelper.TABLE_TRIPSOPTIONS, null, values);
        if (newRowId != -1) {
            Toast.makeText(getApplicationContext(), "Data saved to database with ID: " + newRowId, Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "Error saving data to database", Toast.LENGTH_SHORT).show();
        }

    }



}























/*
     @Override
            public void onClick(View v) {
                StringBuilder selectedValues = new StringBuilder();

                if (checkBox1.isChecked()) {
                    selectedValues.append("Wifi, yes");
                }
                if (checkBox2.isChecked()) {
                    selectedValues.append("Smoke detector , yes");
                }
                if (checkBox3.isChecked()) {
                    selectedValues.append("PetHouse, yes");
                }
                if (checkBox4.isChecked()) {
                    selectedValues.append("washing machine, yes");
                }
                if (checkBox5.isChecked()) {
                    selectedValues.append("bed, yes");
                }
                if (checkBox6.isChecked()) {
                    selectedValues.append("kitchen, yes");
                }

                String selectedData = selectedValues.toString();
                if (selectedData.endsWith(", ")) {
                    selectedData = selectedData.substring(0, selectedData.length() - 2);
                }

                // Log.d("SelectedData", selectedData);
                // Toast.makeText(getApplicationContext(), "Selected values: " + selectedData, Toast.LENGTH_SHORT).show();

                // You can use the selectedData as needed here or pass it to another activity

                // Retrieve the selectedData from the intent
                String receivedSelectedData = getIntent().getStringExtra("selectedData");
                if (receivedSelectedData != null) {
                    Toast.makeText(Optioninterface.this, "Selected Data from AvailabiltyInterface: " + receivedSelectedData, Toast.LENGTH_SHORT).show();
                }

                // Retrieve the fromDate and toDate from the intent
                String fromDate = getIntent().getStringExtra("fromDate");
                String toDate = getIntent().getStringExtra("toDate");

                if (fromDate != null && toDate != null) {
                    Toast.makeText(Optioninterface.this, "From Date: " + fromDate + "\nTo Date: " + toDate, Toast.LENGTH_SHORT).show();
                }

                // Save the selectedData to the database
                saveDataToDatabase(receivedSelectedData);
            }
        });
     */

