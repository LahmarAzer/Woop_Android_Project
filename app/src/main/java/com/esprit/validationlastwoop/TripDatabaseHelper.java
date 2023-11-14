package com.esprit.validationlastwoop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.widget.Toast;

import java.util.ArrayList;

public class TripDatabaseHelper extends SQLiteOpenHelper {


    private Context context;
    // Database Information
    private static final String DATABASE_NAME = "trip.db";
    private static final int DATABASE_VERSION = 4;

    // Table Name
    public static final String TABLE_TRIPS = "tripdetails";

    // Table columns
    public static final String _ID = "_id";
    public static final String COLUMN_TITLE = "titre";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_HOST_NAME = "host_name";
    public static final String COLUMN_BIO = "bio";

    // Creating table query
    private static final String CREATE_TABLE_TRIPS = "CREATE TABLE " + TABLE_TRIPS + "(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
           // COLUMN_IMAGE_PATH + " TEXT NOT NULL, " +
            COLUMN_TITLE + " TEXT, " +
            COLUMN_LOCATION + " TEXT, " +
            COLUMN_HOST_NAME + " TEXT, " +
            COLUMN_BIO + " TEXT);";

    public TripDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_TRIPS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIPS);
        onCreate(db);
    }

    public Cursor getAlltrips() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_TRIPS, null);
    }

    void addTrip(String title, String hostName, String location,String bio, String helpDescription,String hoursDescription, Float rating, Integer nbFeedback, Double lastActivity, Double replyRate, Integer nbWoopers){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Add values for each column based on the method parameters
        //cv.put(COLUMN_IMAGE_PATH, imagePath);
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_LOCATION, location);
        cv.put(COLUMN_HOST_NAME, hostName);
        cv.put(COLUMN_BIO, bio);
       // cv.put(COLUMN_TYPE_OF_HELP, typeOfHelp);
      //  cv.put(COLUMN_LANGUAGES, languages);
      //  cv.put(COLUMN_AMENITIES, amenities);

        // Insert the trip data into the TRIPS table
        long result = db.insert(TABLE_TRIPS, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed to add trip", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Trip added successfully!", Toast.LENGTH_SHORT).show();
        }
    }


    // Add methods for CRUD operations on trips (insert, update, delete, query)

    // Get all trips in the database
    public ArrayList<Trip> getAllTrips2() {
        ArrayList<Trip> trips = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                _ID,
                COLUMN_TITLE,
                COLUMN_LOCATION,
                COLUMN_HOST_NAME,
                COLUMN_BIO
        };

        Cursor cursor = db.query(
                TABLE_TRIPS,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                long tripIdColumnIndex = cursor.getColumnIndex(_ID);
                long tripId = tripIdColumnIndex != -1 ? cursor.getInt((int) tripIdColumnIndex) : -1L;

                int nameColumnIndex = cursor.getColumnIndex(COLUMN_TITLE);
                String titre = nameColumnIndex != -1 ? cursor.getString(nameColumnIndex) : "";

                int locationColumnIndex = cursor.getColumnIndex(COLUMN_LOCATION);
                String location = locationColumnIndex != -1 ? cursor.getString(locationColumnIndex) : "";

                int bioColumnIndex = cursor.getColumnIndex(COLUMN_BIO);
                String bio = bioColumnIndex != -1 ? cursor.getString(bioColumnIndex) : "";

                int HostNameColumnIndex = cursor.getColumnIndex(COLUMN_HOST_NAME);
                float hostname = HostNameColumnIndex != -1 ? cursor.getFloat(HostNameColumnIndex) : -1;

                Trip trip = new Trip(tripId, titre, location, bio, hostname);
                trips.add(trip);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return trips;
    }

    // Add this method to your TripDatabaseHelper class

    public boolean addTrip2(String hostName, String titre, String location) {
        // Get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_HOST_NAME, hostName);
        values.put(COLUMN_TITLE, titre);
        values.put(COLUMN_LOCATION, location);

        // Inserting Row
        long rowId = db.insert(TABLE_TRIPS, null, values);
        db.close(); // Closing database connection

        return rowId != -1; // Return true if insert is successful
    }
    public void addInitialTrips() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues trip1 = new ContentValues();
        trip1.put(COLUMN_TITLE, "Trip 1");
        trip1.put(COLUMN_LOCATION, "Location 1");
        trip1.put(COLUMN_HOST_NAME, "Host 1");
        trip1.put(COLUMN_BIO, "Bio 1");

        ContentValues trip2 = new ContentValues();
        trip2.put(COLUMN_TITLE, "Trip 2");
        trip2.put(COLUMN_LOCATION, "Location 2");
        trip2.put(COLUMN_HOST_NAME, "Host 2");
        trip2.put(COLUMN_BIO, "Bio 2");

        ContentValues trip3 = new ContentValues();
        trip3.put(COLUMN_TITLE, "Trip 3");
        trip3.put(COLUMN_LOCATION, "Location 3");
        trip3.put(COLUMN_HOST_NAME, "Host 3");
        trip3.put(COLUMN_BIO, "Bio 3");

        db.insert(TABLE_TRIPS, null, trip1);
        db.insert(TABLE_TRIPS, null, trip2);
        db.insert(TABLE_TRIPS, null, trip3);

        db.close();
    }

}
