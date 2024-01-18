package com.example.myapplication;

import java.util.ArrayList;

public class DataManager {
    private static ArrayList<Person> people;
    private static final String  dbMainList="People";

public static ArrayList<Person> GetPeople(){
if(people==null){
    people=new ArrayList<Person>();
}
return people;
}
public static void AddNewPerson(Person person){
    GetPeople().add(person);
    DBManager.getDatabase().getReference(dbMainList).setValue(people);
}
}