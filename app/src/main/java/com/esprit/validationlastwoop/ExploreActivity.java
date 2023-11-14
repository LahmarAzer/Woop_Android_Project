package com.esprit.validationlastwoop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExploreActivity     extends AppCompatActivity  {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.explore_interface);
            Button  WorkLink = findViewById(R.id.Next_button);

            WorkLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Intent to start SignupActivity
                    Intent intent = new Intent(ExploreActivity.this, WorkActivity.class);
                    startActivity(intent);
                }
            });



}};
