package com.esprit.validationlastwoop;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AvailabiltyInterface extends AppCompatActivity {
    private EditText fromDateEditText, toDateEditText;
    private Button btnnext;

    private DatabaseHelper DatabaseHelper;

    private String selectedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.availability_interface);

        DatabaseHelper = new DatabaseHelper(this);

        fromDateEditText = findViewById(R.id.fromDateEditText);
        toDateEditText = findViewById(R.id.toDateEditText);
        btnnext = findViewById(R.id.Next_button);

        Intent receivedIntent = getIntent();
        if (receivedIntent != null) {
            selectedData = receivedIntent.getStringExtra("selectedData");
            Toast.makeText(this, "Selected data from VariousActivity: " + selectedData, Toast.LENGTH_SHORT).show();
        }

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fromDate = fromDateEditText.getText().toString().trim();
                String toDate = toDateEditText.getText().toString().trim();

                if (isDatesOccupied(fromDate, toDate)) {
                    // Les dates sont occupées, définissez la couleur en rouge
                    fromDateEditText.setTextColor(Color.RED);
                    toDateEditText.setTextColor(Color.RED);
                    Toast.makeText(AvailabiltyInterface.this, "Les dates sont occupées.", Toast.LENGTH_SHORT).show();
                } else {
                    // Les dates ne sont pas occupées, définissez la couleur par défaut
                    fromDateEditText.setTextColor(Color.BLACK);
                    toDateEditText.setTextColor(Color.BLACK);

                    String toastMessage = "From Date: " + fromDate + "\nTo Date: " + toDate;
                    Toast.makeText(AvailabiltyInterface.this, toastMessage, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AvailabiltyInterface.this, Optioninterface.class);
                    intent.putExtra("fromDate", fromDate);
                    intent.putExtra("toDate", toDate);

                    if (selectedData != null) {
                        intent.putExtra("selectedData", selectedData);
                    }
                    startActivity(intent);
                    saveDataToDatabase(fromDate, toDate);
                }
            }
        });
    }

    private void saveDataToDatabase(String fromDate, String toDate) {
        SQLiteDatabase db = DatabaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.COLUMN_DATE_DEBUT, fromDate);
        values.put(DatabaseHelper.COLUMN_DATE_FIN, toDate);

        long newRowId = db.insert(DatabaseHelper.TABLE_TRIPSAVAILAIBILTIES, null, values);

        if (newRowId != -1) {
            Toast.makeText(getApplicationContext(), "Data saved to database with ID: " + newRowId, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Error saving data to database", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isDatesOccupied(String fromDate, String toDate) {
        return DatabaseHelper.isDatesOccupied(fromDate, toDate);
    }
}