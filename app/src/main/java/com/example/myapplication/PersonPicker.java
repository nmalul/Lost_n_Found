package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PersonPicker extends AppCompatActivity implements View.OnClickListener {
    static ArrayList<Person> personList;
    ListView lv;
    PersonAdapter personAdapter;
    Button btnAdd,btnEdit;
    Person lastSelected;
    String name,id;
    ListView lvPeople;
    Home home;
    Person person;
    Boolean edit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_picker);
        lv = findViewById(R.id.lvPeople);
        if(home==null){
            home=DataManager.GetHome(getIntent().getStringExtra("EMAIL"));
            person=DataManager.GetPerson(home,name);
            }
        else{
            home= new Home("email");
            person=new Person("email");
        }
        if (personList == null) {
            personList=new ArrayList<>();
            personList.addAll(home.getPeople());
        }
        personAdapter = new PersonAdapter(this, 0, 0, personList);
        lv.setAdapter(personAdapter);
        lvPeople=findViewById(R.id.lvPeople);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
           lastSelected = personAdapter.getItem(position);
           if (edit != true) {
            Intent intent = new Intent(PersonPicker.this, MainActivity.class);
            getIntent().putExtra("NAME", lastSelected.getName());
            startActivityForResult(intent, 0);
         } else {
         Intent intent = new Intent(PersonPicker.this, EditPerson.class);
         getIntent().putExtra("NAME", lastSelected.getName());
         startActivityForResult(intent, 0);
         }
    }
 });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                lastSelected = personAdapter.getItem(position);
                personAdapter.remove(lastSelected);
                personAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        {
            if (requestCode == 0) {
                if (resultCode == RESULT_OK) {
                    name = data.getExtras().getString("NAME");
                    lastSelected.setName(name);
                    personAdapter.notifyDataSetChanged();
                }
                else
                    Toast.makeText(this,"data canceld",Toast.LENGTH_LONG).show();
            } else if (requestCode == 1) {
                if (resultCode == RESULT_OK) {
                    name = data.getExtras().getString("NAME");
                    id=data.getExtras().getString("ID");
                    Person Person = new Person(name);
                    DataManager.AddNewPerson(person,home);
                    personAdapter.add(person);
                    personAdapter.notifyDataSetChanged();
                }
                else
                    Toast.makeText(this,"data canceld",Toast.LENGTH_LONG).show();
            }
            Toast.makeText(this, "data saved", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if(btnAdd==v){
            Intent intent=new Intent(PersonPicker.this,EditPerson.class);
            intent.putExtra("NAME",lastSelected.getName());
            startActivityForResult(intent,0);
        }
    }
}

