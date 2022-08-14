package com.example.pillapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Main4Activity extends AppCompatActivity {
    EditText edit;
    Button bu2;
    Button bu3;
    FirebaseAuth mFirebaseAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
        edit=findViewById(R.id.editText1);
        bu2=findViewById(R.id.button2);
        bu3=findViewById(R.id.button3);
        mFirebaseAuth= FirebaseAuth.getInstance();
        bu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit.getText().toString().isEmpty()) {
                    edit.setError("entrer votre email");
                    edit.requestFocus();
                    return;
                }
                    mFirebaseAuth.sendPasswordResetEmail(edit.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Main4Activity.this, "nous avons envoyer votre nouvelle mot de passer a l'email correspendant ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Main4Activity.this, "envoie non reussi", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }
        });
        bu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main4Activity.this,LoginActivity.class);
                startActivity(i);
            }
        });


    }

}

