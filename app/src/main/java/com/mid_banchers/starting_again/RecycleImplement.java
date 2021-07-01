package com.mid_banchers.starting_again;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class RecycleImplement extends AppCompatActivity {
    RecyclerView rv1;
    List<String> listImage = new ArrayList<>();
    List<String> listName = new ArrayList<>();
    List<String> listID = new ArrayList<>();
    RecycleDirectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_implement);
        rv1 = findViewById(R.id.rvcheck);
        adapter = new RecycleDirectAdapter(this);
        rv1.setAdapter(adapter);
        rv1.setLayoutManager(new GridLayoutManager(this, 2));
        getDatafromDb();


    }

    private void getDatafromDb() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

//        db.collection("Brands")
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        for (DocumentSnapshot ds : queryDocumentSnapshots.getDocuments()){
//                            dpname.add(ds.getString("brandName"));
//                            dbimage.add(ds.getString("image"));
//                            dbid.add(ds.getId());
//                        }
//                        Toast.makeText(RecycleImplement.this, "Fetched", Toast.LENGTH_SHORT).show();
//                        adapter.getdata(dbid,dbimage,dpname);
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//            }
//        });


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