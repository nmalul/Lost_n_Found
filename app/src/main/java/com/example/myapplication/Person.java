package com.example.myapplication;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private ArrayList<Items> items;
    private String email;
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


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
public void AddNewItem(Items item){
        getItems().add(item);
}

}
