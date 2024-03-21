package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {
    Context context;
    List<Person> objects;
    TextView tvPerson;


    public PersonAdapter(Context context,int resource,int texViewResourceId,List<Person>objects){
        super(context,resource);


        this.context=context;
        this.objects=objects;
    }
    public View getView(int position, View converView, ViewGroup parent){
        LayoutInflater layoutInflater=((Activity)context).getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.person_tab,parent,false);
        tvPerson=(TextView) view.findViewById(R.id.tvPerson);

        Person temp=objects.get(position);
        tvPerson.setText(temp.getName());
        return view;
    }


}


