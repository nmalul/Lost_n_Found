package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Boolean clicked = false;
    Ringtone ringtone;
    String email;
    Home home;
    Person person;
    @SuppressLint("MissingInflatedId")
    Button btnRooms, btnItemList, btnAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataManager.pullHomes();
        btnRooms = findViewById(R.id.btnRooms);
        btnRooms.setOnClickListener(this);
        btnItemList = findViewById(R.id.btnItemList);
        btnItemList.setOnClickListener(this);
        btnAlert = findViewById(R.id.btnAlert);
        btnAlert.setOnClickListener(this);
        email=getIntent().getStringExtra("EMAIL");
        Uri ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), ringtoneUri);
        for(int i=0; i<DataManager.GetHomes().size();i++){
            if(DataManager.GetHomes().get(i).getEmail().equals(email)){
                home=DataManager.GetHomes().get(i);
            }
        }
        for(int j=0;j<home.getPeople().size();j++){
            if(home.getPeople().get(j).getItems().equals(getIntent().getStringExtra("PERSON"))){
                person=home.getPeople().get(j);
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if (id == R.id.action_login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, 2);
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == btnRooms) {
            Intent intent = new Intent(this, Rooms.class);
            startActivityForResult(intent, 0);
        }
        if (v == btnItemList) {
            Intent intent = new Intent(this, ItemList.class);
            intent.putExtra("EMAIL", getIntent().getStringExtra("EMAIL"));
            startActivityForResult(intent, 0);
        }
        if (v == btnAlert) {
            if (ringtone != null) {
                if (!clicked) {
                    ringtone.play();
                    clicked = true;
                } else {
                    ringtone.stop();
                    clicked = false;

                }
            }
        }
    }
}