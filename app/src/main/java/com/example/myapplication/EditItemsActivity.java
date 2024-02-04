package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EditItemsActivity extends AppCompatActivity implements View.OnClickListener {
    static ArrayList<Items> itemList;
    ListView lv;
    EditText etId;
    ItemAdapter itemAdapter;
    Button btnAdd,btnDone;
    Items lastSelected;
    String name;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        etId=findViewById(R.id.etId);
        lv = findViewById(R.id.lvItems);
        Items i1 = new Items("keys","id","email");
        if( itemList==null )
        {
            itemList = new ArrayList<Items>();
            itemList.add(i1);
        }
        itemAdapter = new ItemAdapter(this, 0, 0, itemList);
        lv.setAdapter(itemAdapter);
        btnDone=findViewById(R.id.btnDone);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnDone.setOnClickListener(this);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lastSelected = itemAdapter.getItem(position);
                Intent intent = new Intent(EditItemsActivity.this, EditActivity.class);
                getIntent().putExtra("NAME", lastSelected.getItem());
                getIntent().putExtra("ID",lastSelected.getId());
                startActivityForResult(intent, 0);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                lastSelected=itemAdapter.getItem(position);
                itemAdapter.remove(lastSelected);
                itemAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v == btnAdd) {
            Intent intent = new Intent(this, EditActivity.class);
            startActivityForResult(intent, 1);
        }
        if(v==btnDone){
            finish();
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
                    Items item = new Items(name,etId.getText().toString(),"email");
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
