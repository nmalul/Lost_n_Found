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
                if(homes==null){
                    homes=new ArrayList<Home>();
                }
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
    public static void AddNewPerson(Person person, Home home){
        home.getPeople().add(person);
        DBManager.getDatabase().getReference(dbMainList).setValue(home);
    }
    public static void AddNewItem(Items item,Person person){
    person.getItems().add(item);
        DBManager.getDatabase().getReference(dbMainList).setValue(homes);
    }
    public static Home GetHome(String email){
        for(int i=0;i<homes.size();i++){
            if(homes.get(i).getEmail().equals(email)){
                return homes.get(i);
            }
        }
        return null;
    }
    public static Person GetPerson(Home home,String name){
        for(int i=0;i<home.getPeople().size();i++){
            if(home.getPeople().get(i).getName().equals(name)){
                return home.getPeople().get(i);
            }
        }
        return null;
    }
    public static String CheckLocationChange(Person person, Items item){
        for(int i=0; i<DataManager.GetHomes().size();i++){
            if(DataManager.GetHomes().get(i).getEmail().equals(person.getEmail())){
                for(int j=0;j<DataManager.GetHomes().get(i).getPeople().size();j++){
                    if(homes.get(i).getPeople().get(j).getName().equals(person.getName())){
                        for(int k=0;k<DataManager.GetHomes().get(i).getPeople().get(j).getItems().size();k++){
                            if(!DataManager.GetHomes().get(i).getPeople().get(j).getItems().get(k).getLocation().equals(person.getItems().get(k))){
                                return ("item: "+ person.getItems().get(k).getItem()+" moved to" + DataManager.GetHomes().get(i).getPeople().get(j).getItems().get(k).getLocation());
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}