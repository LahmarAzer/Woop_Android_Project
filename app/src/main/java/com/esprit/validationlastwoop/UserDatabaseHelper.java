package com.esprit.validationlastwoop;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    // Database Information
    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 2;

    // Table Name
    public static final String TABLE_USERS = "users";

    // Table columns
    public static final String _ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    // Creating table query
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USERNAME + " TEXT NOT NULL, " +
            COLUMN_EMAIL + " TEXT NOT NULL, " +
            COLUMN_PASSWORD + " TEXT NOT NULL);";

    public UserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

  /*  @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }*/
  @Override
  public void onCreate(SQLiteDatabase db) {
      try {
          db.execSQL(CREATE_TABLE_USERS);
          Log.d("Database Operations", "Table users created successfully.");
         // logAllUsers();
      } catch (Exception e) {
          Log.d("Database Operations", "Error creating table users.");
          e.printStackTrace();

      }
  }
    /*public void logAllUsers() {
        List<User> users = getAllUsers(); // Your existing method to fetch all users
        for (User user : users) {
            Log.d("Database Operations", "User: " + user.toString());
        }
    }*/


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public long insertUser(String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        long newRowId = db.insert(TABLE_USERS, null, values);
        db.close();
        return newRowId;
    }

    public boolean updateUser(long userId, String username, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        int numRowsUpdated = db.update(TABLE_USERS, values, _ID + " = ?", new String[]{String.valueOf(userId)});
        db.close();
        return numRowsUpdated > 0;
    }

    public boolean deleteUser(long userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(TABLE_USERS, _ID + " = ?", new String[]{String.valueOf(userId)});
        db.close();
        return deletedRows > 0;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") User user = new User(
                        cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD))
                );
                users.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return users;
    }

    @SuppressLint("Range")
    public User getUser(long userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = _ID + " = ?";
        String[] selectionArgs = { String.valueOf(userId) };
        Cursor cursor = db.query(TABLE_USERS, null, selection, selectionArgs, null, null, null);

        User user = null;
        if (cursor.moveToFirst()) {
            user = new User(
                    cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD))
            );
        }
        cursor.close();
        db.close();
        return user;
    }


}