package com.example.pillapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ChoixProfileActivity extends AppCompatActivity {
    ImageView user, patient ;
    Button retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_profile);
        user=findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChoixProfileActivity.this, profileActivity.class);
                startActivity(i);

            }
        });
        patient=findViewById(R.id.patient);
        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChoixProfileActivity.this, PatientprofileActivity.class);
                startActivity(i);

            }
        });
        retour=findViewById(R.id.retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChoixProfileActivity.this, MenuActivity.class);
                startActivity(i);

            }
        });
    }
}
