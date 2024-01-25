package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;

public class Items {



    private String id,location, item;
    Button btnBuzz;




    public Items(String item,String location,String id){
        this.item=item;
        this.location=location;
        this.id=id;
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

}
