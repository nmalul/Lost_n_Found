package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
EditText etName,etId;
Button btnSave,btnCancel;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        etId=findViewById(R.id.etId);
        etName=(EditText) findViewById(R.id.etName);
        btnSave=(Button) findViewById(R.id.btnSave);
        btnCancel=(Button) findViewById(R.id.btnCancel);
        Intent intent=getIntent();
        if(intent.getExtras()!=null){
            String name=intent.getExtras().getString("NAME");
            etName.setHint(name);
        }
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(btnSave==v){
            if(etName.getText().toString().length()>0){
                Items item= new Items(etName.getText().toString(),etId.getText().toString(),getIntent().getStringExtra("EMAIL"));
                Intent intent=new Intent();
                intent.putExtra("NAME",etName.getText().toString());
                intent.putExtra("ID",etId.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
            else
                Toast.makeText(this,"please fill all fields",Toast.LENGTH_LONG).show();

            }
        else if (btnCancel==v){
            setResult(RESULT_CANCELED,null);
            finish();
        }


    }

}