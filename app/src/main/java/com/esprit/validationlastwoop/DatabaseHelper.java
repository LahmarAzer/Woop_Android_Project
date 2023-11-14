package com.esprit.validationlastwoop;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "trip.db";
    public static final String TABLE_TRIPS = "trips";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CONSTRUCTION = "construction";
    public static final String COLUMN_GARDENING = "gardening";
    public static final String COLUMN_EDUCATING = "educating";
    public static final String COLUMN_SPORTS = "sports";
    public static final String COLUMN_BABYSITTING = "babysitting";
    public static final String COLUMN_DESIGNER = "designer";
    public static final String TABLE_TRIPSOPTIONS = "tripsOptions";
    public static final String COLUMN_ONE = "one";
    public static final String COLUMN_TWO = "two";
    public static final String COLUMN_MORE = "more";
    public static final String COLUMN_WIFI = "wifi";
    public static final String COLUMN_SMOKE = "smoke";
    public static final String COLUMN_PETHOUSE = "pethouse";
    public static final String COLUMN_WASHINGMACHINE = "washingmachine";
    public static final String COLUMN_BED = "bed";
    public static final String COLUMN_KITCHEN = "kitchen";
    public static final String TABLE_TRIPSAVAILAIBILTIES = "tripsAvailibilties";
    public static final String COLUMN_DATE_DEBUT = "date_debut";
    public static final String COLUMN_DATE_FIN = "date_fin";


    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TRIPS_TABLE = "CREATE TABLE " + TABLE_TRIPS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_CONSTRUCTION + " TEXT,"
                + COLUMN_GARDENING + " TEXT,"
                + COLUMN_EDUCATING + " TEXT,"
                + COLUMN_SPORTS + " TEXT,"
                + COLUMN_BABYSITTING + " TEXT,"
                + COLUMN_DESIGNER + " TEXT"
                + ")";
        String CREATE_TRIPSOPTIONS = "CREATE TABLE " + TABLE_TRIPSOPTIONS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_ONE + " INTEGER,"  // Change data type to INTEGER
                + COLUMN_TWO + " INTEGER,"   // Change data type to INTEGER
                + COLUMN_MORE + " INTEGER,"  // Change data type to INTEGER
                + COLUMN_WIFI + " TEXT,"
                + COLUMN_SMOKE + " TEXT,"
                + COLUMN_PETHOUSE + " TEXT,"
                + COLUMN_WASHINGMACHINE + " TEXT,"
                + COLUMN_BED + " TEXT,"
                + COLUMN_KITCHEN + " TEXT"
                + ")";
        String CREATE_TABLE_TRIPSAVAILAIBILTIES = "CREATE TABLE " + TABLE_TRIPSAVAILAIBILTIES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_DATE_DEBUT + " TEXT,"
                + COLUMN_DATE_FIN + " TEXT"
                + ")";
        db.execSQL(CREATE_TRIPS_TABLE);
        db.execSQL(CREATE_TRIPSOPTIONS);
        db.execSQL(CREATE_TABLE_TRIPSAVAILAIBILTIES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIPSOPTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIPSAVAILAIBILTIES);
        onCreate(db);
    }
    public Cursor getAlltrips() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_TRIPS, null);
    }

    public Cursor getAlltripsAvailabilties() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_TRIPSAVAILAIBILTIES, null);
    }
    public Cursor getAlltripsOptions() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_TRIPSOPTIONS, null);
    }

    public Cursor getLatestVarious() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_TRIPS + " ORDER BY " + COLUMN_ID + " DESC LIMIT 1";
        return db.rawQuery(query, null);
    }

    public String getLatestVarious1() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_TRIPS + " ORDER BY " + COLUMN_ID + " DESC LIMIT 1";

        Cursor cursor = db.rawQuery(query, null);

        String columnName = null;
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(COLUMN_CONSTRUCTION); // Use the correct column name
            if (columnIndex >= 0) {
                // Column exists, so retrieve the name of the column
                columnName = cursor.getColumnName(columnIndex);
            }
            cursor.close();
        }

        return columnName;
    }

    public Cursor getTripOptionsById(int tripOptionsId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] args = {String.valueOf(tripOptionsId)};
        return db.rawQuery("SELECT * FROM " + TABLE_TRIPSOPTIONS + " WHERE " + COLUMN_ID + " = ?", args);
    }

    public Cursor getTripAvailabilityById(int tripAvailabilityId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] args = {String.valueOf(tripAvailabilityId)};
        return db.rawQuery("SELECT * FROM " + TABLE_TRIPSAVAILAIBILTIES + " WHERE " + COLUMN_ID + " = ?", args);
    }


    public void deleteAllTrips() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRIPS, null, null);
        db.delete(TABLE_TRIPSOPTIONS, null, null);
        db.delete(TABLE_TRIPSAVAILAIBILTIES, null, null);
        db.close();
    }

    public boolean isDatesOccupied(String fromDate, String toDate) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TRIPSAVAILAIBILTIES +
                        " WHERE (" + COLUMN_DATE_DEBUT + " BETWEEN ? AND ?) OR (" + COLUMN_DATE_FIN + " BETWEEN ? AND ?)",
                new String[]{fromDate, toDate, fromDate, toDate});

        boolean isOccupied = cursor.getCount() > 0;

        cursor.close();

        return isOccupied;
    }





}