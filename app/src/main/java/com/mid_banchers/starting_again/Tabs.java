package com.mid_banchers.starting_again;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Tabs extends AppCompatActivity {
    Spinner drop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        drop=findViewById(R.id.Drop);
        List<String>data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        ArrayAdapter<String>adapter= new DropAdapter(this,R.layout.add,data);
        drop.setAdapter(adapter);
    }


}