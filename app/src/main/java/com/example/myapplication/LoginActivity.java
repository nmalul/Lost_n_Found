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
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
Button btnEnter;
Spinner btnDrop;
EditText etEmail,etPassword;
TextView tvError,tvSignUp;
FirebaseAuth auth;
Home home;
Person person;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Initialize UI elements
        btnEnter = findViewById(R.id.btnEnter);
        btnDrop = findViewById(R.id.btnDrop);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvError = findViewById(R.id.tvError);
        tvSignUp = findViewById(R.id.tvSignUp);

        // Initialize authentication instance
        auth = FirebaseAuth.getInstance();

        // Set click listeners
        btnEnter.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);

        // Pull homes data
        DataManager.pullHomes();

        // Start location change service
        Intent intent = new Intent(this, LocationChangeService.class);
        startService(intent);

        // Populate spinner with people
        String[] people = new String[DataManager.GetHomes().size()];
        for (int i = 0; i < DataManager.GetHomes().size(); i++) {
            if (DataManager.GetHomes().get(i).getEmail().equals(etEmail.getText().toString())) {
                home = DataManager.GetHomes().get(i);
                for (int j = 0; j < home.getPeople().size(); j++) {
                    people[j] = home.getPeople().get(j).getName().toString();
                }
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(LoginActivity.this, android.R.layout.simple_spinner_dropdown_item, people);
        btnDrop.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        if(v==tvSignUp){
            Intent intent=new Intent(this,SignUpActivity.class);
            startActivityForResult(intent,0);
        }
        if(v==btnEnter) {

            if (!etEmail.getText().toString().equals("") && !etPassword.getText().toString().equals("")/*&&btnDrop.getSelectedItem()!=null*/) {
                DBManager.getAuth().signInWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("User Auth", "User Sign in successfully");
                            getIntent().putExtra("EMAIL",etEmail.getText().toString());
                            Intent intent=new Intent(LoginActivity.this,PersonPicker.class);
                            intent.putExtra("EMAIL",etEmail.getText().toString());
                            startActivityForResult(intent,0);
                        } else if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                            Log.d("User Auth", "problem");
                        }
                    }
                });
            }
            else if(etEmail.getText().toString().equals("")&& etPassword.getText().toString().equals("")) {
                etEmail.setError("please enter email");
                etPassword.setError("please enter password");
                tvError.setText("email and password are empty");
            }
            else if(etEmail.getText().toString().equals("")&&!etPassword.getText().toString().equals("")){
                etPassword.setError("please enter email");
                tvError.setText("email is empty");
            }
            else{
                etPassword.setError("please enter password");
                tvError.setText("password is empty");
            }

        }
    }
}