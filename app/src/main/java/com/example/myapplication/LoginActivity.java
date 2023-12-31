package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
Button btnEnter;
Spinner btnDrop;
EditText etEmail,etPassword;
TextView tvError,tvSignUp;
FirebaseAuth auth;

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
        auth=FirebaseAuth.getInstance();
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        tvError=findViewById(R.id.tvError);
        tvSignUp=findViewById(R.id.tvSignUp);
        tvSignUp.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v==tvSignUp){
            Intent intent=new Intent(this,SignUpActivity.class);
            startActivityForResult(intent,0);
        }
        if(v==btnEnter){
            finish();
        }
    }
}