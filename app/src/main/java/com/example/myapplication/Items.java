package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;

public class Items {
    private String item;
    Button btnBuzz;
    public Items(String item){
        this.item=item;
    }
    public String getItem(){
        return this.item;
    }
    public void setItem(String item){
        this.item=item;
    }

}
