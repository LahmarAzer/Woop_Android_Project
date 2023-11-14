package com.esprit.lastwooptravelproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class FriendActivity extends AppCompatActivity {

    private LinearLayout friendsContainer;
    private FriendDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_list); // Replace with your actual layout file

        // Initialize your database helper
        dbHelper = new FriendDBHelper(this);

        // Get a reference to your container
        friendsContainer = findViewById(R.id.friendsContainer);

        // Load the friends from the database and display them
        loadFriends1();
    }

    private void loadFriends1() {
        // Clear the existing views if necessary
        friendsContainer.removeAllViews();
        // Get the list of friends from the database
        List<Friend> friendsList = dbHelper.getAllFriends1();

        // Get a LayoutInflater
        LayoutInflater inflater = LayoutInflater.from(this);

        for (Friend friend : friendsList) {
            // Inflate the friends_list_item layout for each friend
            View friendView = inflater.inflate(R.layout.friends_list_item, friendsContainer, false);

            // Bind the friend data to the views
            TextView friendName = friendView.findViewById(R.id.friendName);
            TextView friendLocation = friendView.findViewById(R.id.friendLocation);
            TextView friendBio = friendView.findViewById(R.id.friendBio);
            Button deleteFriendButton = friendView.findViewById(R.id.deleteFriendButton);
            // If you have an ImageView for a profile picture, bind it here as well

            friendName.setText(friend.getName());
            friendLocation.setText(friend.getLocation());
            friendBio.setText(friend.getBio());
            // Set an OnClickListener for the button
            deleteFriendButton.setTag(friend.getId());
            deleteFriendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Retrieve the friend's ID from the button's tag
                    int friendId = (Integer) v.getTag();
                    // Update the friend's status in the database to 0 (meaning added)
                    dbHelper.updateFriendStatus(friendId, 0);

                    // Re-load the friends to refresh the list
                    loadFriends1();
                }
            });

            // Add the friendView to your container
            friendsContainer.addView(friendView);
        }
    }
}
