package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Items> {
    Context context;
    List<Items>objects;
    TextView tvItem;


    public ItemAdapter(Context context,int resource,int texViewResourceId,List<Items>objects){
        super(context,resource,texViewResourceId,objects);


        this.context=context;
        this.objects=objects;
    }
    public View getView(int position, View converView, ViewGroup parent){
        LayoutInflater layoutInflater=((Activity)context).getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.item,parent,false);
        tvItem=(TextView) view.findViewById(R.id.tvItem);

        Items temp=objects.get(position);
        tvItem.setText(temp.getItem());
        return view;
    }


}
