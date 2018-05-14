package com.example.ssuns.hw8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class recordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        Intent intent = getIntent();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        adapter.addAll(intent.getStringArrayListExtra("itemList"));
        ((ListView) findViewById(R.id.recordView)).setAdapter(adapter);
    }
}
