package com.esprit.validationlastwoop;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TestList extends AppCompatActivity {
    private ListView listView;
    private TripDatabaseHelper DatabaseHelper;
    private ArrayList<String> TripView;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_list);


        listView = findViewById(R.id.listViewtrip);

        DatabaseHelper = new TripDatabaseHelper(this);



        TripView = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, TripView);
        listView.setAdapter(adapter);



        loadTrips();

    }


    private void loadTrips() {
        // Replace "columnName" with the actual column names from your trips table
        Cursor cursor = DatabaseHelper.getAlltrips();

        if (cursor.moveToFirst()) {
            int titleIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TITLE);
            int hostnameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_HOST_NAME);
            int locationIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_LOCATION);


            if (titleIndex != -1 && hostnameIndex != -1 && locationIndex != -1) {
                do {
                    String title = cursor.getString(titleIndex);
                    String name = cursor.getString(hostnameIndex);
                    String location = cursor.getString(locationIndex);



                    // Build a string with trip information
                    String tripInfo = "title: " + title
                            + ", name: " + name
                            + ", location: " + location;


                    TripView.add(tripInfo);
                } while (cursor.moveToNext());
            }
        }

        cursor.close();
        adapter.notifyDataSetChanged();
    }


}