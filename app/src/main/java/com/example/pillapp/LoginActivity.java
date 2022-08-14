package com.example.pillapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText mPassword;
    EditText mEmail;
    Button b1;
    private FirebaseAuth mFirebaseAuth;
    TextView mForgot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPassword = findViewById(R.id.editText2);
        mEmail = findViewById(R.id.editText1);
        b1 = findViewById(R.id.button);
        mForgot = findViewById(R.id.activity_main4);
        mFirebaseAuth = FirebaseAuth.getInstance();
        b1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                if (mPassword.getText().toString().isEmpty()) {
                    mPassword.setError("entrer votre mot de passe");
                    mPassword.requestFocus();
                } else if (mEmail.getText().toString().isEmpty()) {
                    mEmail.setError("entrer votre email");
                    mEmail.requestFocus();
                } else if (mPassword.getText().toString().isEmpty() && mEmail.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "votre coordonnées sont vides", Toast.LENGTH_LONG).show();
                } else {
                    mFirebaseAuth.signInWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString()).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "connexion resussite", Toast.LENGTH_LONG).show();
                                FirebaseUser user = mFirebaseAuth.getCurrentUser();
                                Intent i = new Intent(LoginActivity.this, MenuActivity.class);
                                startActivity(i);
                                finish();
                            } else {

                                Toast.makeText(LoginActivity.this, "connexion non reussite ,essayer une autre fois", Toast.LENGTH_LONG).show();

                            }

                        }


                    });


                }
            }
        });
        mForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, Main4Activity.class);
                startActivity(i);
            }
        });

    }



}



