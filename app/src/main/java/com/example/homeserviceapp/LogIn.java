package com.example.homeserviceapp;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    EditText EmailAddress,Password;
    Button LogInBtn;
    TextView Forgorpassword;
    ImageView google;
    String emailpatten = "[A_Za-z0-9._-]+@[a-z]+\\.+[a-z]+";
    TextView signu;
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
        signu = findViewById(R.id.signu);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(LogIn.this);


        Forgorpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fp = new Intent(LogIn.this,ForgotPassword.class);
                startActivity(fp);
                finish();
            }
        });

        signu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sp = new Intent(LogIn.this,SignUp.class);
                startActivity(sp);
            }
        });

        LogInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                perforauth();
            }
        });
    }

    private void perforauth() {
        String Email = EmailAddress.getText().toString();
        String password = Password.getText().toString();

        if(!Email.matches(emailpatten))
        {
            EmailAddress.setError("Please Valid Email");
        }
        else if(password.isEmpty() || password.length()<6)
        {
            Password.setError("Please proper Password");
        }

        else
        {
            progressDialog.setMessage("Please Waiting....");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

        }
        mAuth.signInWithEmailAndPassword(Email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    progressDialog.dismiss();
                    Toast.makeText(LogIn.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    sendUserToNextActivvity();
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(LogIn.this,"Login Error"+task.getException(),Toast.LENGTH_SHORT).show();
                }
            }

            private void sendUserToNextActivvity() {
                Intent Main = new Intent(LogIn.this, DashBoard.class);
                startActivity(Main);
            }
        });
    }
}