package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth auth;
    FirebaseDatabase db;
    Button btnEnter;
    TextView tvError;

EditText etEmail,etPassword;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        btnEnter=findViewById(R.id.btnEnter);
        auth=FirebaseAuth.getInstance();
        btnEnter.setOnClickListener(this);
        tvError=findViewById(R.id.tvError);
        DatabaseReference dbRef=DBManager.getDatabase().getReference("message");
        dbRef.setValue("hello");
    }

    @Override
    public void onClick(View v) {
        if (v == btnEnter) {
            if (!etEmail.getText().toString().equals("") & !etPassword.getText().toString().equals("")) {
                DBManager.getAuth().createUserWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("user Auth", "user signed in successfully");
                            Toast.makeText(SignUpActivity.this, "successful sign-in", Toast.LENGTH_LONG).show();
                            Person person = new Person(etEmail.getText().toString());
                            Home home=new Home(etEmail.getText().toString());
                            Items item = new Items("a", "b", "c");
                            home.AddPerson(person);
                            Toast.makeText(SignUpActivity.this, home.getPeople().get(0).getEmail(), Toast.LENGTH_LONG).show();
                            DataManager.AddNewHome(home);
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivityForResult(intent, 0);
                        } else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Log.d("User Auth", "User creation failed");
                            tvError.setText("already exists");
                            Toast.makeText(SignUpActivity.this, "h", Toast.LENGTH_LONG).show();
                        } else if (task.getException() instanceof FirebaseAuthWeakPasswordException) {
                            Log.d("User Auth", "User creation failed");
                            tvError.setText("password is weak");
                            etPassword.setError("please enter stronger password");
                        } else if (task.getException() instanceof FirebaseAuthEmailException) {
                            Log.d("User Auth", "User creation failed");
                            tvError.setText("email isn't right");
                            etEmail.setError("please enter an email");
                        } else {
                            Toast.makeText(SignUpActivity.this, "something else went wrong", Toast.LENGTH_LONG).show();
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

}