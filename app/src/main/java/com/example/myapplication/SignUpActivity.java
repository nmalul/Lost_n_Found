package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth auth;
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
    }

    @Override
    public void onClick(View v) {
        if(v==btnEnter){
            DBManager.getAuth().signInWithEmailAndPassword(etEmail.getText().toString(),etPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Log.d("user Auth","user signed in successfully");
                        Toast.makeText(SignUpActivity.this,"susccessful sign-in",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(SignUpActivity.this,task.getException().getClass().getName(),Toast.LENGTH_SHORT).show();
                        if(task.getException() instanceof FirebaseAuthUserCollisionException){
                            tvError.setText("already exists" );
                        }
                        Log.d("User Auth","User creation failed");
                    }
                }
            });
            finish();
        }
    }
}