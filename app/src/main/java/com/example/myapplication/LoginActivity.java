package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
Button btnEnter;
Spinner btnDrop;
TextView tvProfile;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnEnter=findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(this);
        btnDrop=findViewById(R.id.btnDrop);
        String[] items = new String[]{"1", "2", "three"};
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(LoginActivity.this, android.R.layout.simple_spinner_dropdown_item,items);
        btnDrop.setAdapter(adapter);


    }


    @Override
    public void onClick(View v) {
        if(v==btnEnter){
            finish();
        }
    }
}