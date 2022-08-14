package com.example.pillapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("Registered")
public class WelcomeScreen extends AppCompatActivity implements View.OnClickListener {
    private TextView mRegister;
    private Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mRegister.setOnClickListener(this);
        mLogin.setOnClickListener(this);

    }
    private void initView() {
        mRegister = findViewById(R.id.activity_main_register);
        mLogin = findViewById(R.id.activity_main_login);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.activity_main_register:
                startActivity( new Intent(this,RegisterActivity.class));
                break;
            case R.id.activity_main_login:
                startActivity( new Intent(this,LoginActivity.class));
                break;
        }

    }
}
