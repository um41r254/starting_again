package com.mid_banchers.starting_again;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mid_banchers.starting_again.databinding.ActivityTabBinding;

public class Tab extends AppCompatActivity {
 private ActivityTabBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTabBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

//        tabs=findViewById(R.id.tabs);
//        ViewPager2 pager = findViewById(R.id.pager);

        TabAdapter tabAdapter = new TabAdapter(this);

        binding.pager.setAdapter(tabAdapter);

        new TabLayoutMediator(binding.tabs,binding.pager,(TabLayout.Tab tab, int position) -> {
            if (position==0){

                tab.setText("Data Delay");
            }if (position==1){

                tab.setText("View With Data");
            }

        }).attach();
    }

}