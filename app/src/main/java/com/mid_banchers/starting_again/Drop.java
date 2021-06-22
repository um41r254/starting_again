package com.mid_banchers.starting_again;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Drop extends AppCompatActivity {
    Spinner drop;
    ImageView imageView,imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop);
        drop=findViewById(R.id.Drop);
        List<String>data = new ArrayList<>();
        data.add("");
        data.add("");
        data.add("");
        ArrayAdapter<String>adapter= new DropAdapter(this,R.layout.add,data);
        drop.setAdapter(adapter);
        imageView=findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Drop.this,Tab.class);
                startActivity(intent);
            }
        });
        Glide.with(this).load("https://numeralpaint.com/wp-content/uploads/2020/08/japan-autumn-season-paint-by-number.jpg")
                .into(imageView);
        imageView2=findViewById(R.id.imageView2);

        Glide.with(this).load("https://numeralpaint.com/wp-content/uploads/2020/08/japan-autumn-season-paint-by-number.jpg")
                .into(imageView2);

    }




}