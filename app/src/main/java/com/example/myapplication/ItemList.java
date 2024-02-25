package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemList extends AppCompatActivity implements View.OnClickListener {
    static ArrayList<Items> itemList;
    ListView lv;
    ItemAdapter itemAdapter;
    Button btnAdd,btnDone,btnDaily;
    Items lastSelected;
    String name,id;
    ListView lvItems;

    Boolean pressedDaily =false;
    Home home;
    Person person;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        lv = findViewById(R.id.lvItems);
        if(home!=null){
        home=DataManager.GetHome(getIntent().getStringExtra("EMAIL"));
        person=DataManager.GetPerson(home,getIntent().getStringExtra("PERSON"));}
        else{
            home= new Home("email");
            person=new Person("email");
        }
        Items i1 = new Items("keys","id","email");
        if (itemList == null) {
            itemList = new ArrayList<Items>();
            itemList.add(i1);
        }
        itemAdapter = new ItemAdapter(this, 0, 0, itemList);
        lv.setAdapter(itemAdapter);
        lvItems=findViewById(R.id.lvItems);
        btnDone = findViewById(R.id.btnDone);
        btnAdd = findViewById(R.id.btnAdd);
        btnDaily = findViewById(R.id.btnDaily);
        btnDaily.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnDone.setOnClickListener(this);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    lastSelected = itemAdapter.getItem(position);
                    if(pressedDaily !=true){
                    Intent intent = new Intent(ItemList.this, EditActivity.class);
                    getIntent().putExtra("NAME", lastSelected.getItem());
                    startActivityForResult(intent, 0);}
                             else {
                        TextView daily = view.findViewById(R.id.tvDaily);
                        if(daily.getText()=="daily"){
                            daily.setText("");
                        }
                        else {
                            daily.setText("daily");
                        }
                    }
                }
            });
            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    lastSelected = itemAdapter.getItem(position);
                    itemAdapter.remove(lastSelected);
                    itemAdapter.notifyDataSetChanged();
                    return false;
                }
            });
        }


    @Override
    public void onClick(View v) {

        if (v == btnAdd&pressedDaily!=true) {
            Intent intent = new Intent(this, EditActivity.class);
            intent.putExtra("EMAIL",getIntent().getStringExtra("EMAIL"));
            startActivityForResult(intent, 1);
        }
        if(v==btnDone&pressedDaily!=true){
            finish();
        }
        if(v==btnDaily){
            if(pressedDaily ==true){
                pressedDaily =false;
                btnDaily.setBackgroundResource(android.R.drawable.btn_default);
                btnAdd.setBackgroundResource(android.R.drawable.btn_default);
                btnDone.setBackgroundResource(android.R.drawable.btn_default);
            }
            else{
                pressedDaily =true;
                btnDaily.setBackgroundColor(Color.TRANSPARENT);
                btnAdd.setBackgroundColor(Color.TRANSPARENT);
                btnDone.setBackgroundColor(Color.TRANSPARENT);
            }

        }

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        {
            if (requestCode == 0) {
                if (resultCode == RESULT_OK) {
                    name = data.getExtras().getString("NAME");
                    lastSelected.setItem(name);
                    itemAdapter.notifyDataSetChanged();
                }
                else
                    Toast.makeText(this,"data canceld",Toast.LENGTH_LONG).show();
            } else if (requestCode == 1) {
                if (resultCode == RESULT_OK) {
                    name = data.getExtras().getString("NAME");
                    id=data.getExtras().getString("ID");
                    Items item = new Items(name,id,"email");
                    DataManager.AddNewItem(item,person);
                    itemAdapter.add(item);
                    itemAdapter.notifyDataSetChanged();
                }
                else
                    Toast.makeText(this,"data canceld",Toast.LENGTH_LONG).show();
            }
            Toast.makeText(this, "data saved", Toast.LENGTH_LONG).show();
        }
    }
}
