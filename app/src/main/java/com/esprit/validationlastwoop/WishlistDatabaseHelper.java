package com.esprit.validationlastwoop;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class WishlistDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "trip.db";
    private static final int DATABASE_VERSION = 1;

    // Define the table and columns for the wishlist
    private static final String TABLE_WISHLIST = "wishlist";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_LOCATION = "location";

    public WishlistDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the wishlist table
        String createTableQuery = "CREATE TABLE " + TABLE_WISHLIST + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_LOCATION + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }

    // Method to add a trip to the wishlist
    public void addToWishlist(Trip trip) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Implement the logic to insert a trip into the wishlist table
        // You should insert the trip's title and location
        // Example: db.insert(TABLE_WISHLIST, null, contentValues);
        db.close();
    }

    // Method to remove a trip from the wishlist
    public void removeFromWishlist(int itemId) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Implement the logic to remove a trip from the wishlist table based on its ID
        // Example: db.delete(TABLE_WISHLIST, COLUMN_ID + "=?", new String[]{String.valueOf(itemId)});
        db.close();
    }

    // Method to retrieve the wishlist items
    public List<Trip> getWishlistItems() {
        List<Trip> wishlistItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Implement the logic to retrieve wishlist items from the wishlist table
        // Example: Cursor cursor = db.query(TABLE_WISHLIST, columns, null, null, null, null, null);
        // Iterate through the cursor and add wishlist items to the list
        // Close the cursor and return the list
        db.close();
        return wishlistItems;
    }
}
