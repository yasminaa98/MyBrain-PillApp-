package com.example.pillapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class User_activity extends AppCompatActivity {
    private EditText mprise1,mprise2 , mprise3;
    private Button mSave;
    TimePickerDialog picker;
    String p1,p2,p3;
    private DatabaseReference mDatabaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mprise1 = findViewById(R.id.prise1);
        mprise1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                TimePickerDialog picker = new TimePickerDialog(User_activity.this,new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int sHour, int sMinute) {
                        mprise1.setText(String.format("%02d:%02d", sHour, sMinute) );
                    }
                }, hour, minutes, false);
                picker.show();

            }
        });


        mprise2 = findViewById(R.id.prise2);
        mprise2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                TimePickerDialog picker = new TimePickerDialog(User_activity.this,new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int sHour, int sMinute) {
                        mprise2.setText(String.format("%02d:%02d", sHour, sMinute) );
                    }
                }, hour, minutes, false);
                picker.show();

            }
        });
        mprise3 = findViewById(R.id.prise3);
        mprise3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                TimePickerDialog picker = new TimePickerDialog(User_activity.this,new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int sHour, int sMinute) {
                        mprise3.setText(String.format("%02d:%02d", sHour, sMinute) );
                    }
                }, hour, minutes, false);
                picker.show();
            }
        });
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        final String p1 =prefs.getString("p1", "");
        mprise1.setText(p1);
        final String p2 = prefs.getString("p2", "");
        mprise2.setText(""+p2);
        final String p3 = prefs.getString("p3", "");
        mprise3.setText(p3);
        mSave = findViewById(R.id.user_save);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mprise1.getText().toString().isEmpty()) {
                    mprise1.setError("veillez entrez le temps de prise");
                    mprise1.requestFocus();
                    return;
                }
                else if(mprise2.getText().toString().isEmpty()){
                    mprise2.setError("veillez entrez le temps de prise");
                    mprise2.requestFocus();
                    return;
                }
                else if(mprise3.getText().toString().isEmpty()){
                    mprise3.setError("veillez entrez le temps de prise");
                    mprise3.requestFocus();
                    return;
                }
                else {
                    String p1 =mprise1.getText().toString().trim();
                    String p2 =mprise2.getText().toString().trim();
                    String p3=mprise3.getText().toString().trim();
                    SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(User_activity.this);
                    SharedPreferences.Editor editor=prefs.edit();
                    editor.putString("p1", p1);
                    editor.putString("p2", p2);
                    editor.putString("p3", p3);
                    editor.commit();
                    saveData1(mprise1.getText().toString());
                    saveData2(mprise2.getText().toString());
                    saveData3(mprise3.getText().toString());
                    Toast.makeText(User_activity.this, "vos temps des prises ont etes enregistrer avec succes", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(User_activity.this, MenuActivity.class);
                    startActivity(i);
                }




            }

        });




    }
    private void saveData1(String input) {

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("temps").child("1ere prise");
        mDatabaseReference.setValue(input);


    }



    private void saveData2(String input) {

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("temps").child("2eme prise");
        mDatabaseReference.setValue(input);
    }

    private void saveData3(String input) {
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("temps").child("3eme prise");
        mDatabaseReference.setValue(input);
    }

    }








