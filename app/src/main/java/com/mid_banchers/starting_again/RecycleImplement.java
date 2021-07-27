package com.mid_banchers.starting_again;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.mid_banchers.starting_again.databinding.ActivityRecycleImplementBinding;

import java.util.ArrayList;
import java.util.List;

public class RecycleImplement extends AppCompatActivity {
    List<String> listImage = new ArrayList<>();
    List<String> listName = new ArrayList<>();
    List<String> listID = new ArrayList<>();
    RecycleDirectAdapter adapter;

    private ActivityRecycleImplementBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityRecycleImplementBinding .inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        adapter = new RecycleDirectAdapter(this);
       binding.rvcheck.setAdapter(adapter);
        binding.rvcheck.setLayoutManager(new GridLayoutManager(this, 2));
        getDataFromDb();


    }

    private void getDataFromDb() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection("Brands")
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        Log.w("Fail", error);
                    } else {

                        listName.clear();
                        listImage.clear();
                        listID.clear();

                        for (QueryDocumentSnapshot ds : value) {
                            listName.add(ds.getString("brandName"));
                            listImage.add(ds.getString("image"));
                            listID.add(ds.getId());
                        }
                    }

                    Toast.makeText(RecycleImplement.this, "Fetched", Toast.LENGTH_SHORT).show();
                    adapter.getData(listID, listImage, listName);

                });

    }
}