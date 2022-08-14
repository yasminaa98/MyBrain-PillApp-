package com.example.pillapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class PatientprofileActivity extends AppCompatActivity {
    EditText pnom ,psituation,page,penfants;
    Button patient_register;
    private String currentUserUid;
    FirebaseAuth mFirebaseAuth;
    String n,s,a,e;
    DatabaseReference reference;
    String Users = "Users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientprofile);

        pnom=findViewById(R.id.patient_nom);
        psituation=findViewById(R.id.patient_situation);
        page=findViewById(R.id.patient_age);
        penfants=findViewById(R.id.patient_enfant);
        patient_register=findViewById(R.id.patient_register);
        patient_register = findViewById(R.id.patient_register);
        SharedPreferences SHprefs= PreferenceManager.getDefaultSharedPreferences(this);
        final String n =SHprefs.getString("n", "");
        pnom.setText(n);
        final String s = SHprefs.getString("s", "");
        psituation.setText(""+s);
        final String a = SHprefs.getString("a", "");
        page.setText(a);
        final String e = SHprefs.getString("e", "");
        penfants.setText(e);
        patient_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a =page.getText().toString().trim();
                String n =pnom.getText().toString().trim();
                String e=penfants.getText().toString().trim();
                String s=psituation.getText().toString().trim();
                SharedPreferences SHprefs= PreferenceManager.getDefaultSharedPreferences(PatientprofileActivity.this);
                SharedPreferences.Editor editorsh=SHprefs.edit();
                editorsh.putString("a", a);
                editorsh.putString("n", n);
                editorsh.putString("s", s);
                editorsh.putString("e", e);
                editorsh.commit();
                savepAge(page.getText().toString());
                savepSituation(psituation.getText().toString());
                savepNom(pnom.getText().toString());
                savepEnfants(penfants.getText().toString());
                Toast.makeText(PatientprofileActivity.this, "vos coordonnes ont etes enregistrer avec succes", Toast.LENGTH_LONG).show();
                Intent i = new Intent(PatientprofileActivity.this, MenuActivity.class);
                startActivity(i);

            }

        });
    }
    private void savepAge(String input) {

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        reference = mDatabase.getReference().child("Information de patient").child("age de patient");
        reference.setValue(input);

    }
    private void savepSituation(String input) {

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        reference = mDatabase.getReference().child("Information de patient").child("situation civile de patient");
        reference.setValue(input);

    }
    private void savepNom(String input) {

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        reference = mDatabase.getReference().child("Information de patient").child("Nom et prenom de patient");
        reference.setValue(input);

    }
    private void savepEnfants(String input) {

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        reference = mDatabase.getReference().child("Information de patient").child("Nombre d'enfants de patient");
        reference.setValue(input);

    }

    }

