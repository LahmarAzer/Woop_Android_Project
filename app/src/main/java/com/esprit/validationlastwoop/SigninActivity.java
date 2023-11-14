package com.esprit.validationlastwoop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SigninActivity extends AppCompatActivity {
    EditText edusername, edpassword;
    Button btnSignin;
    UserDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_interface);
        edusername = findViewById(R.id.username);
        edpassword = findViewById(R.id.password);
        btnSignin = findViewById(R.id.sign_in_button);
        dbHelper = new UserDatabaseHelper(this);

        TextView signUpLink = findViewById(R.id.sign_up_link);
        TextView ForgetLink = findViewById(R.id.forgot_password);

        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to start SignupActivity
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });





        ForgetLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to start SignupActivity
                Intent intent = new Intent(SigninActivity.this, ForgetActivity.class);
                startActivity(intent);
            }
        });


        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String username = edusername.getText().toString().trim();
        String password = edpassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = dbHelper.getUserByUsernameAndPassword(username, password);

        if (user != null) {
            // Successful login, you can redirect the user to another activity or perform any other action here
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ExploreActivity.class);
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}
