package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.parse.SignUpCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class SignUp extends AppCompatActivity {
    public static final String TAG = "SignUp";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnSave;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_sign_up );

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnSave=findViewById (R.id.btn_sign);
        btnSave.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick sign up button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                signUser(username, password);
            }
        } );
    }

    private void signUser(String username, String password) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground ( new SignUpCallback ( ) {
            @Override
            public void done(ParseException e) {
                if (e != null ){
                    Log.e("TAG", "Issue with login", e);
                    Toast.makeText(SignUp.this, "Issue with sign up!", Toast.LENGTH_SHORT).show();

                    return;
                }
                ParseUser.logOut();
                ParseUser.getCurrentUser ( );
                goLoginActivity();

                Toast.makeText(SignUp.this, "User signed with success!", Toast.LENGTH_SHORT).show();

            }

        } );

    }

    private void goLoginActivity() {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}