package com.mid_banchers.starting_again;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RecycleImplement extends AppCompatActivity {
    RecyclerView rv1;
    List<String> dbimage = new ArrayList<>();
    List<String> dpname = new ArrayList<>();
    List<String> dbid = new ArrayList<>();
    RecycleDirectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_implement);
        rv1 = findViewById(R.id.rvcheck);
        adapter = new RecycleDirectAdapter(this);
        rv1.setAdapter(adapter);
        rv1.setLayoutManager(new GridLayoutManager(this,2));
        getDatafromDb();


    }
    private void getDatafromDb(){
        FirebaseFirestore db =  FirebaseFirestore.getInstance();

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
        final CollectionReference docRef  = db.collection("Brands");
        docRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.w("Fail", error);
                }
                for (QueryDocumentSnapshot ds : value) {
                    dpname.add(ds.getString("brandName"));
                    dbimage.add(ds.getString("image"));
                    dbid.add(ds.getId());
                }
                Toast.makeText(RecycleImplement.this, "Fetched", Toast.LENGTH_SHORT).show();
                adapter.getdata(dbid, dbimage, dpname);

            }
        });

    }
}