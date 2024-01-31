package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Items {



    private String id;
    private String location;
    private String item;
    private String email;
    public Items(){

    }

    public Items(String item,String id){
        this.item=item;

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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
