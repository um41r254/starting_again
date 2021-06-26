package com.mid_banchers.starting_again;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Tab extends AppCompatActivity {
TabLayout tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        tabs=findViewById(R.id.tabs);
        ViewPager2 pager = findViewById(R.id.pager);

        TabAdapter tabAdapter = new TabAdapter(this);

        pager.setAdapter(tabAdapter);

        new TabLayoutMediator(tabs,pager,(TabLayout.Tab tab, int position) -> {
            if (position==0){

                tab.setText("Data Delay");
            }if (position==1){

                tab.setText("View With Data");
            }

        }).attach();
    }

}