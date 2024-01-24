package com.example.myapplication;

import android.widget.ListView;

public class Person {
    String boy="boy";
    ItemList items;
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
    public void setItems(ItemList items) {
        this.items = items;
    }

    public ItemList getItems() {
        return items;
    }



}
