package com.example.myapplication;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Person {

    ArrayList<Items> items;
    String email;
    public Person(String email){
        this.email=email;
        this.items= new ArrayList<Items>();
    }

    public Person() {
    }

    public void setBoy(String boy) {

    }


    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }

    public ArrayList<Items> getItems() {
        return items;
    }



}
