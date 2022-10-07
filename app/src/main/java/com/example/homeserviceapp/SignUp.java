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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class SignUp extends AppCompatActivity {

    EditText Fname,Lname,email,Pass,Cpass;
    Button btnSignUp;
    TextView SLogin;
    String emailpatten = "[A_Za-z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressbar;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Fname = findViewById(R.id.Fname);
        Lname = findViewById(R.id.Lname);
        email = findViewById(R.id.email);
        Pass = findViewById(R.id.Pass);
        Cpass = findViewById(R.id.Cpass);
        btnSignUp = findViewById(R.id.btnSignUp);
        SLogin = findViewById(R.id.SLogin);
        mAuth = FirebaseAuth.getInstance();
        progressbar = new ProgressDialog(SignUp.this);

        SLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Login = new Intent(SignUp.this,LogIn.class);
                startActivity(Login);
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                perforauth();
            }
        });



    }

    private static void setOnClickListener(View.OnClickListener onClickListener) {
    }

    private void perforauth() {

        String Email = email.getText().toString();
        String Password = Pass.getText().toString();
        String Cpassword = Cpass.getText().toString();

        if(!Email.matches(emailpatten))
        {
            email.setError("Please Valid Email");
        }
        else if(Password.isEmpty() || Password.length()<6)
        {
            Pass.setError("Please proper Password");
        }
        else if(!Password.equals(Cpassword))
        {
            Cpass.setError("Please do not match Password");
        }
        else
        {
            progressbar.setMessage("Please Waiting....");
            progressbar.setTitle("Registration");
            progressbar.setCanceledOnTouchOutside(false);
            progressbar.show();

            mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
//                        progressbar.dismiss();
                        Toast.makeText(SignUp.this,"Registation successful",Toast.LENGTH_SHORT).show();
                        sendUserToNextActivity();
                    }
                    else
                    {
//                        progressbar.dismiss();
                        Toast.makeText(SignUp.this,"Registration Error"+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {

        Intent Main = new Intent(SignUp.this,LogIn.class);
        startActivity(Main);
        finish();
    }
}
