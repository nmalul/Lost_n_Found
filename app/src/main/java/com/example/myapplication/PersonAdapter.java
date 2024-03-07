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
    TextView tvPeople;


    public PersonAdapter(Context context,int resource,int texViewResourceId,List<Items>objects){
        super(context,resource);


        this.context=context;
        this.objects=objects;
    }
    public View getView(int position, View converView, ViewGroup parent){
        LayoutInflater layoutInflater=((Activity)context).getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.item,parent,false);
        tvPeople=(TextView) view.findViewById(R.id.tvItem);

        Items temp=objects.get(position);
        tvPeople.setText(temp.getItem());
        return view;
    }


}


