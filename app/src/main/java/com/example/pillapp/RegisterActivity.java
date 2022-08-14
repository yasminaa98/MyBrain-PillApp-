package com.example.pillapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    private EditText mEmail, mPassword, mAge, mOccupation, mNom;
    Button mSubmit;
    Spinner spinner;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final Spinner spinner = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.members, R.layout.spinner_items);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        mSubmit = findViewById(R.id.register_submit);
        mEmail = findViewById(R.id.register_email);
        mPassword = findViewById(R.id.register_mdp);
        mAge = findViewById(R.id.age);
        mOccupation = findViewById(R.id.occupation);
        mNom = findViewById(R.id.nom);
        auth = FirebaseAuth.getInstance();
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mPassword.getText().toString().isEmpty() ) {
                    mPassword.setError("entrer votre mot de passe avec au moins 6 carateres");
                    mPassword.requestFocus();
                } else if (mEmail.getText().toString().isEmpty()) {
                    mEmail.setError("entrer votre email");
                    mEmail.requestFocus();

                } else if (mPassword.getText().toString().isEmpty() && mEmail.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "votre coordonnées sont vides", Toast.LENGTH_LONG).show();

                } else {

                    auth.createUserWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString()).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Connexion non reussite." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                String user_id = Objects.requireNonNull(auth.getCurrentUser()).getUid();
                                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                                Map newPost = new HashMap();
                                newPost.put("email", mEmail.getText().toString());
                                newPost.put("nom", mNom.getText().toString());
                                newPost.put("age", mAge.getText().toString());
                                newPost.put("relation", spinner.getSelectedItem().toString());
                                newPost.put("Occupation", mOccupation.getText().toString());
                                current_user_db.setValue(newPost);
                                startActivity(new Intent(RegisterActivity.this, MenuActivity.class));
                                finish();

                            }
                        }
                    });

                }
            }
        });
    }

    }

