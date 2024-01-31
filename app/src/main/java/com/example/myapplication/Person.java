package com.example.myapplication;

import android.widget.ListView;

import java.util.List;

public class Person {
    String boy="boy";
    List<Items> items;
    String email;
    public Person(String email){
        this.email=email;
    }

    public void setBoy(String boy) {
        this.boy = boy;
    }

    public String getBoy() {
        return boy;
    }
    public void setItems(List<Items> items) {
        this.items = items;
    }

    public List<Items> getItems() {
        return items;
    }



}
