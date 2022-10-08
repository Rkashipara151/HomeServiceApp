package com.example.homeserviceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.internal.InternalTokenProvider;

public class DashBoard extends AppCompatActivity {

    TextView Name;
    ImageView imageviewPr,imgviewCleaning,imgviewElectrician,imgviewPlumbing,imgviewPainting;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://homeserviceapp-80b0c-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        Name = findViewById(R.id.Name);
        imageviewPr = findViewById(R.id.imageviewPr);
        imgviewCleaning = findViewById(R.id.imgviewCleaning);
        imgviewElectrician = findViewById(R.id.imgviewElectrician);
        imgviewPlumbing =  findViewById(R.id.imgviewPlumbing);
        imgviewPainting = findViewById(R.id.imgviewPainting);


        imageviewPr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pr = new Intent(DashBoard.this,ProfileActivity.class);
                startActivity(pr);
            }
        });

        imgviewCleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c =new Intent(DashBoard.this,Cleaning.class);
                startActivity(c);
            }
        });
        imgviewElectrician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent e = new Intent(DashBoard.this,Electrician.class);
                startActivity(e);
            }
        });

        imgviewPlumbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pl = new Intent(DashBoard.this,Plumbing.class);
                startActivity(pl);
            }
        });

        imgviewPainting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paint=new Intent(DashBoard.this,Painting.class);
                startActivity(paint);
            }
        });

    }

}