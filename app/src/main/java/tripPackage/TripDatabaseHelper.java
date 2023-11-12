package tripPackage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;

public class TripDatabaseHelper extends SQLiteOpenHelper {

    // Database Information
    private static final String DATABASE_NAME = "trip.db";
    private static final int DATABASE_VERSION = 1;

    // Table Name
    public static final String TABLE_TRIPS = "trips";

    // Table columns
    public static final String _ID = "_id";
    public static final String COLUMN_IMAGE_PATH = "image_path";
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
    public long insertTrip(Trip trip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Populate the ContentValues with trip data
        values.put(COLUMN_IMAGE_PATH, trip.getImagePath());
        values.put(COLUMN_LOCATION, trip.getLocation());
        values.put(COLUMN_HOST_NAME, trip.getHostName());
        values.put(COLUMN_RATING, trip.getRating());
        values.put(COLUMN_NB_FEEDBACK, trip.getNbFeedback());
        values.put(COLUMN_LAST_ACTIVITY, trip.getLastActivity());
        values.put(COLUMN_REPLY_RATE, trip.getReplyPercentage());
        values.put(COLUMN_BIO, trip.getBio());
        values.put(COLUMN_HELP_DESCRIPTION, trip.getHelpDescription());
        values.put(COLUMN_NB_WOOPERS, trip.getNbWoopers());
        values.put(COLUMN_HOURS_DESCRIPTION, trip.getHoursDescription());
        values.put(COLUMN_TYPE_OF_HELP, trip.getTypeOfHelp().name()); // Store enum as String
        values.put(COLUMN_LANGUAGES, trip.getLanguages().name()); // Store enum as String
        values.put(COLUMN_AMENITIES, trip.getAmenities().name()); // Store enum as String

        // Insert the trip data into the database
        long newRowId = db.insert(TABLE_TRIPS, null, values);
        db.close();
        return newRowId;
    }

    // Add methods for CRUD operations on trips (insert, update, delete, query)
}
