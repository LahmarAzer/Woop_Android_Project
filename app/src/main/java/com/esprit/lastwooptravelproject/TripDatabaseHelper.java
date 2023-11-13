package com.esprit.lastwooptravelproject;

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
    private static final int DATABASE_VERSION = 1;

    // Table Name
    public static final String TABLE_TRIPS = "trips";

    // Table columns
    public static final String _ID = "_id";
    public static final String COLUMN_IMAGE_PATH = "image_path";
    public static final String COLUMN_TITLE = "titre";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_HOST_NAME = "host_name";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_NB_FEEDBACK = "nb_feedback";
    public static final String COLUMN_LAST_ACTIVITY = "last_activity";
    public static final String COLUMN_REPLY_RATE = "reply_rate";
    public static final String COLUMN_BIO = "bio";
    public static final String COLUMN_HELP_DESCRIPTION = "help_description";
    public static final String COLUMN_NB_WOOPERS = "nb_woopers";
    public static final String COLUMN_HOURS_DESCRIPTION = "hours_description";
    public static final String COLUMN_TYPE_OF_HELP = "type_of_help";
    public static final String COLUMN_LANGUAGES = "languages";
    public static final String COLUMN_AMENITIES = "amenities";

    // Creating table query
    private static final String CREATE_TABLE_TRIPS = "CREATE TABLE " + TABLE_TRIPS + "(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_IMAGE_PATH + " TEXT NOT NULL, " +
            COLUMN_TITLE + " TEXT, " +
            COLUMN_LOCATION + " TEXT, " +
            COLUMN_HOST_NAME + " TEXT, " +
            COLUMN_RATING + " REAL, " +
            COLUMN_NB_FEEDBACK + " INTEGER, " +
            COLUMN_LAST_ACTIVITY + " REAL, " +
            COLUMN_REPLY_RATE + " REAL, " +
            COLUMN_BIO + " TEXT, " +
            COLUMN_HELP_DESCRIPTION + " TEXT, " +
            COLUMN_NB_WOOPERS + " INTEGER, " +
            COLUMN_HOURS_DESCRIPTION + " TEXT, " +
            COLUMN_TYPE_OF_HELP + " TEXT, " +
            COLUMN_LANGUAGES + " TEXT, " +
            COLUMN_AMENITIES + " TEXT);";

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

    public ArrayList<Trip> getAllTrips2() {
        ArrayList<Trip> trips = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                _ID ,
                COLUMN_TITLE ,
                COLUMN_LOCATION ,
                COLUMN_HOST_NAME ,
                COLUMN_RATING ,
                COLUMN_NB_FEEDBACK ,
                COLUMN_LAST_ACTIVITY ,
                COLUMN_REPLY_RATE ,
                COLUMN_BIO ,
                COLUMN_HELP_DESCRIPTION ,
                COLUMN_NB_WOOPERS ,
                COLUMN_HOURS_DESCRIPTION ,
                COLUMN_TYPE_OF_HELP ,
                COLUMN_LANGUAGES ,
                COLUMN_AMENITIES
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
                long tripId = tripIdColumnIndex != -1 ? cursor.getLong((int) tripIdColumnIndex) : -1L;

                int titleColumnIndex = cursor.getColumnIndex(COLUMN_TITLE);
                String titre = titleColumnIndex != -1 ? cursor.getString(titleColumnIndex) : "";

                int locationColumnIndex = cursor.getColumnIndex(COLUMN_LOCATION);
                String location = locationColumnIndex != -1 ? cursor.getString(locationColumnIndex) : "";

                int hostNameColumnIndex = cursor.getColumnIndex(COLUMN_HOST_NAME);
                String hostName = hostNameColumnIndex != -1 ? cursor.getString(hostNameColumnIndex) : "";

                int ratingColumnIndex = cursor.getColumnIndex(COLUMN_RATING);
                float rating = ratingColumnIndex != -1 ? cursor.getFloat(ratingColumnIndex) : 0;

                int feedbackColumnIndex = cursor.getColumnIndex(COLUMN_NB_FEEDBACK);
                int nbFeedback = feedbackColumnIndex != -1 ? cursor.getInt(feedbackColumnIndex) : 0;

                int lastActivityColumnIndex = cursor.getColumnIndex(COLUMN_LAST_ACTIVITY);
                double lastActivity = lastActivityColumnIndex != -1 ? cursor.getDouble(lastActivityColumnIndex) : 0;

                int replyRateColumnIndex = cursor.getColumnIndex(COLUMN_REPLY_RATE);
                double replyPercentage = replyRateColumnIndex != -1 ? cursor.getDouble(replyRateColumnIndex) : 0;

                int bioColumnIndex = cursor.getColumnIndex(COLUMN_BIO);
                String bio = bioColumnIndex != -1 ? cursor.getString(bioColumnIndex) : "";

                int helpDescriptionColumnIndex = cursor.getColumnIndex(COLUMN_HELP_DESCRIPTION);
                String helpDescription = helpDescriptionColumnIndex != -1 ? cursor.getString(helpDescriptionColumnIndex) : "";

                int nbWoopersColumnIndex = cursor.getColumnIndex(COLUMN_NB_WOOPERS);
                int nbWoopers = nbWoopersColumnIndex != -1 ? cursor.getInt(nbWoopersColumnIndex) : 0;

                int hoursDescriptionColumnIndex = cursor.getColumnIndex(COLUMN_HOURS_DESCRIPTION);
                String hoursDescription = hoursDescriptionColumnIndex != -1 ? cursor.getString(hoursDescriptionColumnIndex) : "";

                int typeOfHelpColumnIndex = cursor.getColumnIndex(COLUMN_TYPE_OF_HELP);
                String typeOfHelp = typeOfHelpColumnIndex != -1 ? cursor.getString(typeOfHelpColumnIndex) : "";

                int languagesColumnIndex = cursor.getColumnIndex(COLUMN_LANGUAGES);
                String languages = languagesColumnIndex != -1 ? cursor.getString(languagesColumnIndex) : "";

                int amenitiesColumnIndex = cursor.getColumnIndex(COLUMN_AMENITIES);
                String amenities = amenitiesColumnIndex != -1 ? cursor.getString(amenitiesColumnIndex) : "";

                // Create a new Trip object using the read values (modify the Trip constructor to match your design)
                Trip trip = new Trip(tripId, titre, location, hostName, rating, nbFeedback, lastActivity, replyPercentage, bio, helpDescription, nbWoopers, hoursDescription, typeOfHelp, languages, amenities);

                // Add the trip to your list
                trips.add(trip);

            } while (cursor.moveToNext());



        cursor.close();
        }

        db.close();
        return trips;
    }

    void addTrip(String title, String hostName, String location,String bio, String helpDescription,String hoursDescription, Float rating, Integer nbFeedback, Double lastActivity, Double replyRate, Integer nbWoopers){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Add values for each column based on the method parameters
        //cv.put(COLUMN_IMAGE_PATH, imagePath);
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_LOCATION, location);
        cv.put(COLUMN_HOST_NAME, hostName);
        cv.put(COLUMN_RATING, rating);
        cv.put(COLUMN_NB_FEEDBACK, nbFeedback);
        cv.put(COLUMN_LAST_ACTIVITY, lastActivity);
        cv.put(COLUMN_REPLY_RATE, replyRate);
        cv.put(COLUMN_BIO, bio);
        cv.put(COLUMN_HELP_DESCRIPTION, helpDescription);
        cv.put(COLUMN_NB_WOOPERS, nbWoopers);
        cv.put(COLUMN_HOURS_DESCRIPTION, hoursDescription);
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



}
