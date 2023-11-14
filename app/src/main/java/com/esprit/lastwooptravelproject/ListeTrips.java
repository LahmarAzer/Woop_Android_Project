package com.esprit.lastwooptravelproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListeTrips extends AppCompatActivity {
    private static final int REQUEST_CODE_ADD_TRIP = 1; // Request code for starting AddOfferForm activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips_list);
        displayOffers();

        final Button buttonOpenForm = findViewById(R.id.button_open_form);

        buttonOpenForm.setOnClickListener(v -> {
            Intent intent = new Intent(ListeTrips.this, addTrip.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_TRIP); // Start AddOfferForm Activity for result
        });
    }


    private void displayOffers() {
        TripDatabaseHelper dbHelper = new TripDatabaseHelper(ListeTrips.this);
        ArrayList<Trip> trips = dbHelper.getAllTrips2();

        LinearLayout offerContainer = findViewById(R.id.tripsContainer);
        offerContainer.removeAllViews(); // Clear existing views

        // Inflate the layout for the static elements (rectangle and button)
        View headerView = getLayoutInflater().inflate(R.layout.trips_list, null);
        offerContainer.addView(headerView);

        for (Trip trip : trips) {
            // Inflate the offer card layout for each offer
            View offerCardView = getLayoutInflater().inflate(R.layout.trip_list_item, null);

            // Set offer details
            //ImageView offerImageView = offerCardView.findViewById(R.id.imageView);
           // TextView tripTitle = offerCardView.findViewById(R.id.itemTitle);
           // TextView tripLocation = offerCardView.findViewById(R.id.itemLocation);

            // Set the text data
           // tripTitle.setText(trip.getTitre());
          //  tripLocation.setText(trip.getLocation());

            // Add the inflated offer card view to the container
            offerContainer.addView(offerCardView);

            // Set a click listener for each offer card
            offerCardView.setOnClickListener(v -> {
                // Handle offer card click, if needed
                // You can access offer details using the offer object
                Toast.makeText(ListeTrips.this, "Clicked on: " + trip.getTitre(), Toast.LENGTH_SHORT).show();
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_TRIP && resultCode == RESULT_OK) {
            displayOffers(); // Refresh the list when we return from adding a new offer
        }
    }


}