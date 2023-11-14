package com.esprit.validationlastwoop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class tripDetails extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private TextView textViewVariousData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_details_interface);

        /*databaseHelper = new DatabaseHelper(this);

        // Initialize the layout element
        textViewVariousData = findViewById(R.id.textViewVariousData);

        // Get the latest "various" data from the database
        String variousData = databaseHelper.getLatestVarious1();

        if (variousData != null) {
            // "various" data exists, set it in the TextView
            textViewVariousData.setText(variousData);

            // Log the "various" data to the Android log
            Log.d("Various Data", variousData);
        } else {
            // Handle the case where "various" data is not available
            textViewVariousData.setText("Various Data Not Available");

            // Log a message indicating that "various" data was not found
            Log.d("Various Data", "Various data not found in the database");
        }*/

    // Ajoutez un gestionnaire d'événements au bouton
    Button participateButton = findViewById(R.id.ParticipateButtongettAll);
        participateButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Créez une intention pour naviguer vers l'activité testliste
            Intent intent = new Intent(tripDetails.this, testliste.class);
            startActivity(intent);
        }
    });

    }
}
