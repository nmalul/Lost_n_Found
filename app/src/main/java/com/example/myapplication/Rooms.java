package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Rooms extends AppCompatActivity implements View.OnClickListener {
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        btnBack=findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btnBack){
            finish();
        }
    }
}