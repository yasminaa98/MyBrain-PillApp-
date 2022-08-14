package com.example.pillapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MenuActivity extends AppCompatActivity {
    ImageView his, log, time,mot, propos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        propos=findViewById(R.id.propos);
        propos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, ProposActivity.class);
                startActivity(i);

            }
        });


        log= findViewById(R.id.logout);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, WelcomeScreen.class);
                startActivity(i);

            }
        });
        his = findViewById(R.id.historique);
        his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, getData.class);
                startActivity(i);

            }
        });
        time = findViewById(R.id.temps);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, User_activity.class);
                startActivity(i);

            }
        });

        mot = findViewById(R.id.mot);
        mot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, ChoixProfileActivity.class);
                startActivity(i);

            }
        });



    }
}
