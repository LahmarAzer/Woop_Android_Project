package com.esprit.lastwooptravelproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import tripPackage.Amenity;
import tripPackage.Language;
import tripPackage.Trip;
import tripPackage.TripDatabaseHelper;
import tripPackage.TypeOfHelp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize the database helper
        TripDatabaseHelper dbHelper = new TripDatabaseHelper(this);

        // Add static trips to the database
        addStaticTrips(dbHelper);

        // Close the database connection
        dbHelper.close();
    }

    private void addStaticTrips(TripDatabaseHelper dbHelper) {




        // Create Trip1 objects with your static data
        Trip trip1 = new Trip();
        trip1.setImagePath("path_to_image1.png");
        trip1.setLocation("Location 1");
        trip1.setHostName("Host 1");
        trip1.setRating(4.5f);
        trip1.setNbFeedback(20);
        trip1.setLastActivity(24.5); // 24.5 hours
        trip1.setReplyPercentage(95.5); // 95.5%
        trip1.setBio("Bio for Trip 1");
        trip1.setHelpDescription("Help description for Trip 1");
        trip1.setNbWoopers(10);
        trip1.setHoursDescription("5 hours a day, 5 days a week");
        trip1.setAmenities(Amenity.WIFI); // Example amenity
        trip1.setTypeOfHelp(TypeOfHelp.TUTORING); // Example type of help
        trip1.setLanguages(Language.ENGLISH); // Example language



        // Create Trip2 objects with your static data
        Trip trip2 = new Trip();
        trip2.setImagePath("path_to_image2.png");
        trip2.setLocation("Location 2");
        trip2.setHostName("Host 2");
        trip2.setRating(4.5f);
        trip2.setNbFeedback(20);
        trip2.setLastActivity(24.5); // 24.5 hours
        trip2.setReplyPercentage(95.5); // 95.5%
        trip2.setBio("Bio for Trip 2");
        trip2.setHelpDescription("Help description for Trip 2");
        trip2.setNbWoopers(10);
        trip2.setHoursDescription("5 hours a day, 5 days a week");
        trip2.setAmenities(Amenity.WIFI); // Example amenity
        trip2.setTypeOfHelp(TypeOfHelp.TUTORING); // Example type of help
        trip2.setLanguages(Language.ENGLISH); // Example language





        // Create Trip3 objects with your static data
        Trip trip3= new Trip();
        trip3.setImagePath("path_to_image3.png");
        trip3.setLocation("Location 3");
        trip3.setHostName("Host 3");
        trip3.setRating(4.5f);
        trip3.setNbFeedback(20);
        trip3.setLastActivity(24.5); // 24.5 hours
        trip3.setReplyPercentage(95.5); // 95.5%
        trip3.setBio("Bio for Trip 3");
        trip3.setHelpDescription("Help description for Trip 3");
        trip3.setNbWoopers(10);
        trip3.setHoursDescription("5 hours a day, 5 days a week");
        trip3.setAmenities(Amenity.WIFI); // Example amenity
        trip3.setTypeOfHelp(TypeOfHelp.TUTORING); // Example type of help
        trip3.setLanguages(Language.ENGLISH); // Example language





        // Insert trips into the database
        dbHelper.insertTrip(trip1);
        dbHelper.insertTrip(trip2);
        dbHelper.insertTrip(trip3);

        // Add more trips as needed
    }
}