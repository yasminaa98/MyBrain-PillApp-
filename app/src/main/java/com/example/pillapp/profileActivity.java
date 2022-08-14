package com.example.pillapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Objects;

import static java.util.Collections.list;

public class profileActivity extends AppCompatActivity  {
    EditText age, Occupation, nom;
    TextView email;
    Button modification;
    Spinner spinner;
    private String currentUserUid;
    FirebaseAuth mFirebaseAuth;
    DatabaseReference reference;
    String Users = "Users";

    private String[] testArray;
    private String selectedItem;
    private SharedPreferences prefs;
    private String defautItem;
    private int Position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        spinner= findViewById(R.id.spinner);

        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        defautItem =prefs.getString("selectedItem", "");
        testArray=getResources().getStringArray(R.array.members);



        //Tawe ki té5ou default Item, a3mel boucle béch té5ou position mté3ha mi lista hédhi


        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.members, R.layout.spinner_items);
      //  adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);


        if (defautItem.length()>2)
        {
            getPosition();
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getItemAtPosition(position).toString();
                if (position!=0)
                {
                prefs= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor=prefs.edit();
                editor.putString("selectedItem",selectedItem);
                editor.apply();

            }}

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        
        age = findViewById(R.id.age);
        Occupation = findViewById(R.id.occupation);
        email = findViewById(R.id.register_email);
        nom = findViewById(R.id.nomprenom);
        modification = findViewById(R.id.user_modif);
        modification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveRelation(spinner.getSelectedItem().toString());
                saveAge(age.getText().toString());
                saveOccupation(Occupation.getText().toString());
                saveNom(nom.getText().toString());
                Toast.makeText(profileActivity.this, "vos coordonnes ont etes enregistrer avec succes", Toast.LENGTH_LONG).show();
                Intent i = new Intent(profileActivity.this, MenuActivity.class);
                startActivity(i);
            }
        });
        reference = FirebaseDatabase.getInstance().getReference().child(Users).child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()));
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    
                    User user = dataSnapshot.getValue(User.class);
                    age.setText(user.getAge());
                    email.setText(user.getEmail());
                    Occupation.setText(user.getOccupation());
                    nom.setText(user.getNom());
                    user.setSpinner(spinner.getSelectedItem().toString());

                }
            }



            public void onCancelled(@NonNull DatabaseError databaseError) {
            }


        });

    }

    private void getPosition() {

        for(int i=0;i<testArray.length;i++) {
            if (defautItem.equals(testArray[i])) {
                Position = i;
                Log.d("CurrentPosition", String.valueOf(Position));
                //   spinner.setSelection(Position);
            }
        }

        spinner.setSelection(Position);



    }


    private void saveAge(String input) {

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        reference = mDatabase.getReference().child(Users).child(FirebaseAuth.getInstance().getUid()).child("age");
        reference.setValue(input);

    }

    private void saveNom(String input) {

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        reference = mDatabase.getReference().child(Users).child(FirebaseAuth.getInstance().getUid()).child("nom");
        reference.setValue(input);

    }
    private void saveOccupation(String input) {

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        reference = mDatabase.getReference().child(Users).child(FirebaseAuth.getInstance().getUid()).child("Occupation");
        reference.setValue(input);

    }
    private void saveRelation(String input) {

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        reference = mDatabase.getReference().child(Users).child(FirebaseAuth.getInstance().getUid()).child("relation");
        reference.setValue(input);

    }



}
