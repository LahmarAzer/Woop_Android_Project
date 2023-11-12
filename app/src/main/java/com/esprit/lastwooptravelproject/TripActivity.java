package com.esprit.lastwooptravelproject;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.MapView;
import com.squareup.picasso.Picasso;

public class TripActivity extends AppCompatActivity {

    private MapView mapView;
    private ImageView mainImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_details_interface);

        mapView = findViewById(R.id.mapView);
        mainImage = findViewById(R.id.tripImageView);

        // Initialize Google Map
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(googleMap -> {
            // TODO: Set up the map
        });

        // TODO: Load image from database into mainImage
        loadImageFromDatabase();

        // TODO: Set up other views like TextViews, RatingBar, etc.

        findViewById(R.id.ParticipateButton).setOnClickListener(v -> {
            // TODO: Implement contact functionality
        });
    }

    private void loadImageFromDatabase() {
        // TODO: Implement database access to get the image path
        String imagePath = "path_to_your_image_in_database"; // Replace with the actual image path

        if (imagePath != null && !imagePath.isEmpty()) {
            // Use Picasso to load and display the image
            Picasso.get().load(imagePath).into(mainImage);
        } else {
            // Handle the case where there is no image path.
            // You can set a default image or show a placeholder image.
            mainImage.setImageResource(R.drawable.default_image); // Replace with your default image resource.
        }
    }

    // Remember to override the lifecycle methods for mapView
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}
