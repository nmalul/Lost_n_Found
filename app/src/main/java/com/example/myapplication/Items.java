package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Items {



    private String id;
    private String location;
    private String item;

    public Items(){

    }

    public Items(String item,String id,String email){
        this.item=item;
        this.id=email+"."+id;
        this.location=" ";
    }
    public String getItem(){
        return this.item;
    }
    public void setItem(String item){
        this.item=item;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
