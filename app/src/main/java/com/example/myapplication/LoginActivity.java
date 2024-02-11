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
        String[] people = new String[]{};
        for (int i = 0; i < DataManager.GetHomes().size(); i++) {
            if (DataManager.GetHomes().get(i).getEmail().equals(etEmail.getText().toString())) {
                home = DataManager.GetHomes().get(i);
            }
            for (int j = 0; j < home.getPeople().size(); j++) {
                people[j] = home.getPeople().get(i).getItems().toString();
            }
            btnEnter = findViewById(R.id.btnEnter);
            btnEnter.setOnClickListener(this);
            btnDrop = findViewById(R.id.btnDrop);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(LoginActivity.this, android.R.layout.simple_spinner_dropdown_item, people);
            btnDrop.setAdapter(adapter);
            auth = FirebaseAuth.getInstance();
            etEmail = findViewById(R.id.etEmail);
            etPassword = findViewById(R.id.etPassword);
            tvError = findViewById(R.id.tvError);
            tvSignUp = findViewById(R.id.tvSignUp);
            tvSignUp.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if(v==tvSignUp){
            Intent intent=new Intent(this,SignUpActivity.class);
            startActivityForResult(intent,0);
        }
        if(v==btnEnter) {

            if (!etEmail.getText().toString() .equals("") && !etPassword.getText().toString().equals("")&&!btnDrop.getSelectedItem().equals(null)) {
                DBManager.getAuth().signInWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("User Auth", "User Sign in successfully");
                            for(int i=0;i<home.getPeople().size();i++){
                                if(home.getPeople().get(i).getItems().equals(btnDrop.getSelectedItem().toString())){
                                    person=home.getPeople().get(i);
                                }
                            }
                            getIntent().putExtra("EMAIL",etEmail.getText().toString());
                            finish();
                        } else if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                            Log.d("User Auth", "problem");
                        }{

                        }
                    }
                });

            }
            else if(etEmail.getText().toString().equals("")& etPassword.getText().toString().equals("")) {
                etEmail.setError("please enter email");
                etPassword.setError("please enter password");
                tvError.setText("email and password are empty");
            }
            else if(etEmail.getText().toString().equals("")&!etPassword.getText().toString().equals("")){
                etPassword.setError("please enter email");
                tvError.setText("email is empty");
            }
            else{
                etPassword.setError("please enter password");
                tvError.setText("password is empty");
            }

        }
    }
    public Person getPerson(){
        return this.person;
    }
}