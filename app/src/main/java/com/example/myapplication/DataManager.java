package com.example.myapplication;

import android.content.ClipData;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DataManager {
    private static ArrayList<Home> homes;
    private static final String dbMainList = "homes";

    public static ArrayList<Home> GetHomes() {
        if (homes == null) {
            homes = new ArrayList<Home>();
        }
        return homes;
    }

    public static void pullHomes() {
        DBManager.getDatabase().getReference(dbMainList).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GenericTypeIndicator<ArrayList<Home>> t = new GenericTypeIndicator<ArrayList<Home>>() {
                };
                homes = snapshot.getValue(t);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void AddNewHome(Home home) {
        GetHomes().add(home);
        DBManager.getDatabase().getReference(dbMainList).setValue(homes);
    }
    public static void AddNewItem(Items item){

    }
}