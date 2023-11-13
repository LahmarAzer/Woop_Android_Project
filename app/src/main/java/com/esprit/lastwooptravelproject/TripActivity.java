package com.esprit.lastwooptravelproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TripActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TripDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips_list); // This is your activity layout that contains the RecyclerView
        displayTrips();
        final Button buttonOpenForm = findViewById(R.id.button_open_form);
        buttonOpenForm.setOnClickListener(v -> {
            // Inflate the dialog with custom view
            final View dialogView = LayoutInflater.from(TripActivity.this)
                    .inflate(R.layout.add_trip_form, null);
            // AlertDialog for the form
            final AlertDialog dialog = new AlertDialog.Builder(TripActivity.this)
                    .setView(dialogView)
                    .create();

            dialog.show();

            Button buttonSubmit = dialogView.findViewById(R.id.button_submit);
            buttonSubmit.setOnClickListener(view -> {
                // Retrieve the EditText fields and CheckBoxes
                EditText editTextTitle = dialogView.findViewById(R.id.edittext_titre);
                EditText editTextLocation = dialogView.findViewById(R.id.edittext_location);
                EditText editTextName = dialogView.findViewById(R.id.edittext_hostName);
                EditText editTextRating = dialogView.findViewById(R.id.edittext_rating);
                EditText editTextNbFeedback = dialogView.findViewById(R.id.edittext_nbFeedback);
                EditText editTextLastActivity = dialogView.findViewById(R.id.edittext_lastActivity);
                EditText editTextReplyPercentage = dialogView.findViewById(R.id.edittext_replyPercentage);
                EditText editTextBio = dialogView.findViewById(R.id.edittext_bio);
                EditText editTextHelpDescription = dialogView.findViewById(R.id.edittext_helpDescription);
                EditText editTextNbWoopers = dialogView.findViewById(R.id.edittext_nbWoopers);
                EditText editTextHoursDescription = dialogView.findViewById(R.id.edittext_hoursDescription);

                String Titre = editTextTitle.getText().toString().trim();
                String location = editTextLocation.getText().toString().trim();
                String name = editTextName.getText().toString().trim();

                String bio = editTextBio.getText().toString().trim();
                String helpDescription = editTextHelpDescription.getText().toString().trim();
                String hoursDescription = editTextHoursDescription.getText().toString().trim();

                // Debugging: Log the values retrieved from the EditTexts
                Log.d("TripActivity", "Trip Title: " + Titre);
                Log.d("TripActivity", "Trip Host Name: " + name);
                Log.d("TripActivity", "Trip Location: " + location);

                // Parse float for rating
                float rating = 0.0f;
                try {
                    rating = Float.parseFloat(editTextRating.getText().toString().trim());
                } catch (NumberFormatException e) {
                    Toast.makeText(TripActivity.this, "Please enter a valid rating", Toast.LENGTH_SHORT).show();
                    return; // Exit the method early if rating is not valid
                }

                // Parse int for number of feedback
                int nbFeedback = 0;
                try {
                    nbFeedback = Integer.parseInt(editTextNbFeedback.getText().toString().trim());
                } catch (NumberFormatException e) {
                    Toast.makeText(TripActivity.this, "Please enter a valid number of feedback", Toast.LENGTH_SHORT).show();
                    return; // Exit the method early if nbFeedback is not valid
                }

                // Parse double for last activity in hours
                double lastActivity = 0.0;
                try {
                    lastActivity = Double.parseDouble(editTextLastActivity.getText().toString().trim());
                } catch (NumberFormatException e) {
                    Toast.makeText(TripActivity.this, "Please enter a valid number of hours for last activity", Toast.LENGTH_SHORT).show();
                    return; // Exit the method early if lastActivity is not valid
                }

                // Parse double for reply percentage
                double replyPercentage = 0.0;
                try {
                    replyPercentage = Double.parseDouble(editTextReplyPercentage.getText().toString().trim());
                } catch (NumberFormatException e) {
                    Toast.makeText(TripActivity.this, "Please enter a valid reply percentage", Toast.LENGTH_SHORT).show();
                    return; // Exit the method early if replyPercentage is not valid
                }

                // Parse int for number of woopers
                int nbWoopers = 0;
                try {
                    nbWoopers = Integer.parseInt(editTextNbWoopers.getText().toString().trim());
                } catch (NumberFormatException e) {
                    Toast.makeText(TripActivity.this, "Please enter a valid number of woopers", Toast.LENGTH_SHORT).show();
                    return; // Exit the method early if nbWoopers is not valid
                }


                // Create an instance of UserDatabaseHelper and add the offer
                TripDatabaseHelper dbHelper = new TripDatabaseHelper(TripActivity.this);
                dbHelper.addTrip(Titre, name, location, bio,helpDescription,hoursDescription,rating,nbFeedback,lastActivity,replyPercentage,nbWoopers);

                // Dismiss the dialog after submission
                displayTrips();

                dialog.dismiss();
            });
            displayTrips();

        });

    }
    private void displayTrips() {
        TripDatabaseHelper dbHelper = new TripDatabaseHelper(TripActivity.this);
        ArrayList<Trip> trips = dbHelper.getAllTrips2();

        LinearLayout tripContainer = findViewById(R.id.tripsContainer);
        tripContainer.removeAllViews(); // Clear existing views

        // Inflate the layout for the static elements (rectangle and button)
        View headerView = getLayoutInflater().inflate(R.layout.trips_list, null);
        tripContainer.addView(headerView);

        for (Trip trip : trips) {
            // Inflate the offer card layout for each offer
            View tripCardView = getLayoutInflater().inflate(R.layout.trip_list_item, null);
            Log.d("TripActivity", "Trip Title: " + trip.getTitre());
            Log.d("TripActivity", "Trip name host: " + trip.getHostName());
            Log.d("TripActivity", "Trip location: " + trip.getLocation());
            Log.d("TripActivity", "Trip rating: " + trip.getRating());



            // Set offer details
            //ImageView offerImageView = offerCardView.findViewById(R.id.imageView);
            TextView itemTitle = tripCardView.findViewById(R.id.itemTitle);
            TextView itemLocation = tripCardView.findViewById(R.id.itemLocation);
            TextView textViewRating = tripCardView.findViewById(R.id.textViewRating);
            //RatingBar ratingBar = tripCardView.findViewById(R.id.ratingBar);

            // Assuming you have an 'image' field in your Offer class
            // Replace 'setImageResource' with the appropriate method based on your data
            // offerImageView.setImageResource(offer.getImage());

            // Set the text data
            itemTitle.setText(trip.getTitre());
            itemLocation.setText(trip.getLocation());
            textViewRating.setText(String.valueOf(trip.getRating()));

            //ratingBar.setRating(trip.getRating());

            // Add the inflated offer card view to the container
            tripContainer.addView(tripCardView);

            // Set a click listener for each offer card
            tripCardView.setOnClickListener(v -> {
                // Handle offer card click, if needed
                // You can access offer details using the offer object
                Toast.makeText(TripActivity.this, "Clicked on: " + trip.getTitre(), Toast.LENGTH_SHORT).show();
            });
        }
    }

}
