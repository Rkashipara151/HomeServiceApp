package com.example.homeserviceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class ForgotPassword extends AppCompatActivity {

    EditText Femail;
    Button Resetpass;
    TextView signup;
    String emailpatten = "[A_Za-z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progrssbar;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Femail = findViewById(R.id.Femail);
        Resetpass = findViewById(R.id.Resetpass);
        signup = findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();
        progrssbar = new ProgressDialog(ForgotPassword.this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sp = new Intent(ForgotPassword.this,SignUp.class);
                startActivity(sp);
                finish();
            }
        });

        Resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perforauth();
            }
        });
    }

    private void perforauth() {

        String Email = Femail.getText().toString();
        if (!Email.matches(emailpatten)) {
            Femail.setError("Please Valid Email");
        } else {
            progrssbar.setMessage("Please Waiting....");
            progrssbar.setTitle("Sending...");
            progrssbar.setCanceledOnTouchOutside(false);
            progrssbar.show();
        }

        mAuth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(ForgotPassword.this,"Email Sent in spam",Toast.LENGTH_SHORT).show();
                    sendUserToNextActivity();
                }
                else
                {
                     Toast.makeText(ForgotPassword.this,"Error"+task.getException(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendUserToNextActivity() {
        Intent Main = new Intent(ForgotPassword.this, LogIn.class);
        startActivity(Main);
        finish();

    }
}