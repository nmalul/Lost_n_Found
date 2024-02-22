package com.example.myapplication;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private ArrayList<Items> items;
    private String email;
    private String pId;
   private String name;

    public Person() {
    }

    public Person(String name) {
        this.name=name;

    }
    public Person(String email,String pId,String name){
        this.email=email;
        this.items= new ArrayList<Items>();
        this.pId=pId;
        this.name=name;
    }


    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpId() {
        return pId;
    }

    public void setItems(ArrayList<Items> items) {
        this.items = items;
    }

    public ArrayList<Items> getItems() {
        return items;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
public void AddNewItem(Items item){
        getItems().add(item);
}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

