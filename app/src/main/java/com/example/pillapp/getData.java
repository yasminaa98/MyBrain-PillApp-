package com.example.pillapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


@RequiresApi(api = Build.VERSION_CODES.O)
public class getData extends AppCompatActivity {
    TextView med1, med2, med3;
    DatabaseReference ref;
    Button retour ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getdata);
        med1 = findViewById(R.id.prisemed1);
        med2 = findViewById(R.id.prisemed2);
        med3 = findViewById(R.id.prisemed3);
        retour=findViewById(R.id.retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getData.this, MenuActivity.class);
                startActivity(i);

            }
        });

        ref = FirebaseDatabase.getInstance().getReference().child("comprimés").child("verification de prise");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String medi1 = dataSnapshot.child("1ere prise").getValue().toString();
                String medi2 = dataSnapshot.child("2eme prise").getValue().toString();
                String medi3 = dataSnapshot.child("3eme prise").getValue().toString();
                med1.setText(medi1);
                med2.setText(medi2);
                med3.setText(medi3);
                String notif = "Surveillez les prises des pilulles !";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getData.this).setSmallIcon(R.drawable.pillsnotif).setContentTitle("Pillues").setContentText(notif).setAutoCancel(true);
                builder.setDefaults(Notification.DEFAULT_SOUND);
                Intent intent = new Intent(getData.this, getData.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("notification", notif);
                PendingIntent pendingIntent = PendingIntent.getActivity(getData.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, builder.build());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }


        });

    }




    }




