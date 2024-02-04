package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Home {
    private ArrayList<Person>people;
    private ArrayList<Room>rooms;
    private String email;
    public Home(){

    }
    public Home(String email){
    this.email=email;
    this.rooms = new ArrayList<Room>();
    this.people = new ArrayList<Person>();
    }

    public void AddPerson(Person person) {
        this.people.add(person);
    }
    public void AddPeople(List<Person>people){
        for(Person person: people){
            ArrayList<Items>items=person.getItems();
            person.setItems(items);
        }
        this.people.addAll(people);
        }
        public void setPeople(ArrayList<Person>people){
        this.people=people;
        }
        public List<Person>people(){
        return this.people;
        }
        public void setRooms(ArrayList<Room>rooms){
        this.rooms=rooms;
        }
        public List<Room> getRooms(){
        return this.rooms;
        }
        public void setEmail(String email){
        this.email=email;
        }
        public String getEmail(){
        return this.email;
        }
    }

