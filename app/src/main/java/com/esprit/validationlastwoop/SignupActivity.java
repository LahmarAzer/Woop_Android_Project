package com.esprit.validationlastwoop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    EditText edname, edipass, edemail;
    UserDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_interface);
        edemail = findViewById(R.id.email);
        edipass = findViewById(R.id.password);
        edname = findViewById(R.id.username);

        dbHelper = new UserDatabaseHelper(this);

        Button signUpButton = findViewById(R.id.sign_up_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    private void signUp() {
        String username = edname.getText().toString().trim();
        String email = edemail.getText().toString().trim();
        String password = edipass.getText().toString().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();

            return;
        }

        long result = dbHelper.insertUser(username, email, password);

        if (result != -1) {
            Toast.makeText(this, "Signup successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, SigninActivity.class);
            startActivity(intent);
            finish();
            // You can redirect the user to another activity or perform any other action here
        } else {
            Toast.makeText(this, "Error signing up", Toast.LENGTH_SHORT).show();
        }
    }
}
