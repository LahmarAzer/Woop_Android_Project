package com.esprit.lastwooptravelproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class WooperActivity extends AppCompatActivity {

    private LinearLayout woopersContainer;
    private FriendDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_interface); // Replace with your actual layout file

        // Initialize your database helper
        dbHelper = new FriendDBHelper(this);

        // Get a reference to your container
        woopersContainer = findViewById(R.id.woopersContainer);

        // Load the friends from the database and display them
        loadFriends0();
    }

    private void loadFriends0() {
        // Clear the existing views if necessary
        woopersContainer.removeAllViews();
        // Get the list of friends from the database
        List<Friend> friendsList = dbHelper.getAllFriends0();

        // Get a LayoutInflater
        LayoutInflater inflater = LayoutInflater.from(this);

        for (Friend friend : friendsList) {
            // Inflate the friends_list_item layout for each friend
            View friendView = inflater.inflate(R.layout.woopers_list_item, woopersContainer, false);

            // Bind the friend data to the views
            TextView friendName = friendView.findViewById(R.id.friendName);
            TextView friendLocation = friendView.findViewById(R.id.friendLocation);
            Button addButton = friendView.findViewById(R.id.addFriendButton);
            // If you have an ImageView for a profile picture, bind it here as well

            friendName.setText(friend.getName());
            friendLocation.setText(friend.getLocation());
            // Set an OnClickListener for the button
            addButton.setTag(friend.getId());
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Retrieve the friend's ID from the button's tag
                    int friendId = (Integer) v.getTag();

                    // Update the friend's status in the database to 1 (meaning added)
                    dbHelper.updateFriendStatus(friendId, 1);

                    // Re-load the friends to refresh the list
                    loadFriends0();
                }
            });

            // Add the friendView to your container
            woopersContainer.addView(friendView);
        }
    }
}
