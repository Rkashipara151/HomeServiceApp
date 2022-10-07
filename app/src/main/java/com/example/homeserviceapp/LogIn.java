package com.example.homeserviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LogIn extends AppCompatActivity {

    EditText EmailAddress,Password;
    Button LogInBtn;
    TextView Forgorpassword;
    ImageView google;
    String emailpatten = "[A_Za-z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        EmailAddress = findViewById(R.id.EmailAddress);
        Password = findViewById(R.id.Password);
        LogInBtn = findViewById(R.id.LogInBtn);
        Forgorpassword = findViewById(R.id.Forgorpassword);
        google = findViewById(R.id.google);

        Forgorpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fp = new Intent(LogIn.this,ForgotPassword.class);
                startActivity(fp);
            }
        });



    }
}