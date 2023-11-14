package com.esprit.lastwooptravelproject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class addTrip extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TripDatabaseHelper dbHelper;
    private ArrayList<String> TripView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_trip_form);

        Button buttonSubmit = findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(view -> {
            // Retrieve the EditText fields and CheckBoxes
            EditText editTextHostName = findViewById(R.id.edittext_hostName);
            EditText editTextTitre = findViewById(R.id.edittext_titre);
            EditText editTextLocation = findViewById(R.id.edittext_location);

            // Validate and collect the data entered
            String hostName = editTextHostName.getText().toString().trim();
            String titre = editTextTitre.getText().toString().trim();
            String location = editTextLocation.getText().toString().trim();


            // Add offer logic here, use UserDatabaseHelper to add to the database
            TripDatabaseHelper dbHelper = new TripDatabaseHelper(addTrip.this);
            boolean success = dbHelper.addTrip2(hostName, titre, location); // Adjust your addOffer method to return boolean for success

            // Check if add was successful and return accordingly
            if (success) {
                Toast.makeText(addTrip.this, "Trip added successfully!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK); // Set the result as OK
                finish(); // Finish and close the AddOfferForm activity
            } else {
                Toast.makeText(addTrip.this, "Failed to add trip. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

