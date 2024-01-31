package com.example.myapplication;

import java.util.ArrayList;

public class DataManager {
    private static ArrayList<Home> homes;
    private static final String  dbMainList="homes";

public static ArrayList<Home> GetHomes(){
if(homes==null){
    homes=new ArrayList<Home>();
}
return homes;
}
public static void AddNewHome(Home home){
    GetHomes().add(home);
    DBManager.getDatabase().getReference(dbMainList).setValue(homes);
}
}