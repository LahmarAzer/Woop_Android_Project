package com.esprit.validationlastwoop;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_interface);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {

                Intent intent = new Intent(MenuActivity.this, home.class);

                startActivity(intent);
                return true;
            } else if (itemId == R.id.navigation_profile) {
                String email = getIntent().getStringExtra("email");

                Intent intent = new Intent(MenuActivity.this, profile.class);
                intent.putExtra("email", email);



                startActivity(intent);
                return true;
            } else if (itemId == R.id.navigation_trips) {
                Intent intent = new Intent(MenuActivity.this, mytrips.class);
                startActivity(intent);
                return true;
            }
            else if (itemId == R.id.navigation_wishlist) {
                Intent intent = new Intent(MenuActivity.this, wishlist.class);
                startActivity(intent);
                return true;
            }

            return false;
        });

    }
}