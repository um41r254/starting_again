package com.mid_banchers.starting_again;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RecyclerPractice extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<String> fromDB = new ArrayList<>();
    AdapterRecycler adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_practice);

        adapter = new AdapterRecycler(this);

        recyclerView = findViewById(R.id.rv);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        getDataFromDB();
    }

    private void getDataFromDB() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Brands")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    for (DocumentSnapshot ds : queryDocumentSnapshots.getDocuments()) {

                        fromDB.add(ds.getString("brandName"));
                    }
                    Toast.makeText(this, "Data Received", Toast.LENGTH_SHORT).show();
                    adapter.getData(fromDB);

                }).addOnFailureListener(e -> {

        });
    }


}