package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Home {
    private List<Person>people;
    private static ArrayList<Room>rooms;
    private String email;
    public Home(){

    }
    public Home(String email){
    this.email=email;
    }
    public void AddPerson(Person person) {
        List<Items> items=person.getItems();
        for (Items item : items) {
            item.setEmail(email);
        }
        person.setItems(items);
        people.add(person);
    }
    public void AddPeople(List<Person>people){
        for(Person person: people){
            List<Items>items=person.getItems();
            for(Items item:items){
                item.setEmail(email);
            }
            person.setItems(items);
        }
        this.people.addAll(people);
        }
    }

