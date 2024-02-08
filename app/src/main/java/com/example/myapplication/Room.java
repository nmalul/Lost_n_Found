package com.example.myapplication;

import android.content.ClipData;

import java.util.ArrayList;

public class Room {
    private  ArrayList<Items>items;

    public Room(ArrayList<Items> items) {
        this.items = new ArrayList<>();
    }

    public Room() {
    }

    public ArrayList<Items> getItems() {
        return items;
    }

    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }
}
