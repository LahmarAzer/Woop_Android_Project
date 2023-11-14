package com.esprit.validationlastwoop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class FriendDBHelper extends SQLiteOpenHelper {
    // Database Info
    private static final String DATABASE_NAME = "friendsDatabase";
    private static final int DATABASE_VERSION = 2;

    // Table Names
    private static final String TABLE_FRIENDS = "friends";

    // Friend Table Columns
    private static final String KEY_FRIEND_ID = "id";
    private static final String KEY_FRIEND_NAME = "name";
    private static final String KEY_FRIEND_LOCATION = "location";
    private static final String KEY_FRIEND_BIO = "bio";
    private static final String KEY_FRIEND_STATUS = "status";
    public FriendDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FRIENDS_TABLE = "CREATE TABLE " + TABLE_FRIENDS +
                "(" +
                KEY_FRIEND_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + // Define a primary key
                KEY_FRIEND_NAME + " TEXT," +
                KEY_FRIEND_LOCATION + " TEXT," +
                KEY_FRIEND_BIO + " TEXT," +
                KEY_FRIEND_STATUS + " INTEGER  DEFAULT 0" + // SQLite uses 0 (false) and 1 (true) for boolean
                ")";

        db.execSQL(CREATE_FRIENDS_TABLE);
        addInitialFriends(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for friend data, so its upgrade policy is
        // to simply discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDS);
        onCreate(db);
    }

    // Insert a new friend into the database
    public void addFriend(Friend friend) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_FRIEND_NAME, friend.getName());
            values.put(KEY_FRIEND_LOCATION, friend.getLocation());
            values.put(KEY_FRIEND_BIO, friend.getBio());
            values.put(KEY_FRIEND_STATUS, friend.getStatus());
            db.insertOrThrow(TABLE_FRIENDS, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            // Error in adding friend
        } finally {
            db.endTransaction();
        }
    }

    // Get all friends in the database
    public List<Friend> getAllFriends0() {
        List<Friend> friends = new ArrayList<>();

        String FRIENDS_SELECT_QUERY =
                String.format("SELECT * FROM %s WHERE %s = 0", TABLE_FRIENDS, KEY_FRIEND_STATUS);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(FRIENDS_SELECT_QUERY, null);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int idIndex = cursor.getColumnIndex(KEY_FRIEND_ID);
                    int nameIndex = cursor.getColumnIndex(KEY_FRIEND_NAME);
                    int locationIndex = cursor.getColumnIndex(KEY_FRIEND_LOCATION);
                    int bioIndex = cursor.getColumnIndex(KEY_FRIEND_BIO);
                    int statusIndex = cursor.getColumnIndex(KEY_FRIEND_STATUS);

                    if (idIndex != -1 && nameIndex != -1 && locationIndex != -1 && bioIndex != -1) {
                        int id = cursor.getInt(idIndex);
                        String name = cursor.getString(nameIndex);
                        String location = cursor.getString(locationIndex);
                        String bio = cursor.getString(bioIndex);
                        boolean status = cursor.getInt(statusIndex) != 0; // Convert integer to boolean

                        // Include the status in the constructor
                        Friend newFriend = new Friend(id, name, location, bio, status);
                        friends.add(newFriend);
                    }
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            // Error in getting friends
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return friends;
    }


    public List<Friend> getAllFriends1() {
        List<Friend> friends = new ArrayList<>();

        // Modify the WHERE clause to check for status = 1
        String FRIENDS_SELECT_QUERY =
                String.format("SELECT * FROM %s WHERE %s = 1", TABLE_FRIENDS, KEY_FRIEND_STATUS);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(FRIENDS_SELECT_QUERY, null);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int idIndex = cursor.getColumnIndex(KEY_FRIEND_ID);
                    int nameIndex = cursor.getColumnIndex(KEY_FRIEND_NAME);
                    int locationIndex = cursor.getColumnIndex(KEY_FRIEND_LOCATION);
                    int bioIndex = cursor.getColumnIndex(KEY_FRIEND_BIO);
                    int statusIndex = cursor.getColumnIndex(KEY_FRIEND_STATUS);

                    if (idIndex != -1 && nameIndex != -1 && locationIndex != -1 && bioIndex != -1) {
                        int id = cursor.getInt(idIndex);
                        String name = cursor.getString(nameIndex);
                        String location = cursor.getString(locationIndex);
                        String bio = cursor.getString(bioIndex);
                        boolean status = cursor.getInt(statusIndex) != 0; // This will be true since we are filtering where status is 1

                        // Include the status in the constructor
                        Friend newFriend = new Friend(id, name, location, bio, status);
                        friends.add(newFriend);
                    }
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            // Error in getting friends
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return friends;
    }
    public void updateFriendStatus(int friendId, int newStatus) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FRIEND_STATUS, newStatus);

        // Updating status for the friend with the specified ID
        db.update(TABLE_FRIENDS, values, KEY_FRIEND_ID + " = ?", new String[] { String.valueOf(friendId) });

        db.close();
    }

    // Update a friend's details
    public int updateFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FRIEND_NAME, friend.getName());
        values.put(KEY_FRIEND_LOCATION, friend.getLocation());
        values.put(KEY_FRIEND_BIO, friend.getBio());
        values.put(KEY_FRIEND_STATUS, friend.getStatus());
        // Updating profile picture url for friend with that id
        return db.update(TABLE_FRIENDS, values, KEY_FRIEND_ID + " = ?",
                new String[] { String.valueOf(friend.getId()) });
    }

    // Delete a friend from the database
    public void deleteFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FRIENDS, KEY_FRIEND_ID + " = ?",
                new String[] { String.valueOf(friend.getId()) });
        db.close();
    }

    public void addInitialFriends(SQLiteDatabase db) {
        // Names and locations for initial friends
        String[] names = {"Alice", "Bob", "Charlie", "David", "Eva", "Fiona", "George", "Hannah", "Ian", "Julia", "Kevin", "Luna"};
        String[] locations = {"New York", "Paris", "London", "Berlin", "Tokyo", "Sydney", "Cape Town", "Moscow", "Rio de Janeiro", "Toronto", "Beijing", "Mumbai"};

        try {
            for (int i = 0; i < names.length; i++) {
                ContentValues values = new ContentValues();
                values.put(KEY_FRIEND_NAME, names[i]);
                values.put(KEY_FRIEND_LOCATION, locations[i]);
                values.put(KEY_FRIEND_BIO, "Hello, I'm your friend " + names[i] + ".");
                values.put(KEY_FRIEND_STATUS, 0);
                // Insert the friend data using the db instance directly
                db.insertOrThrow(TABLE_FRIENDS, null, values);
            }
        } catch (Exception e) {
            // Error handling
        }
    }


}
