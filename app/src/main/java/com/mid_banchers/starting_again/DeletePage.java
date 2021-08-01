package com.mid_banchers.starting_again;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.mid_banchers.starting_again.databinding.ActivityDeletePageBinding;

import java.util.ArrayList;
import java.util.List;

public class DeletePage extends AppCompatActivity {
    List<String> listImage = new ArrayList<>();
    List<String> listName = new ArrayList<>();
    List<String> listId = new ArrayList<>();
    List<String> listNum = new ArrayList<>();
    DeleteRecycle adapter;
    MainActivity get = new MainActivity();

    ActivityDeletePageBinding deletePageBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        deletePageBinding = ActivityDeletePageBinding.inflate(getLayoutInflater());
        View view = deletePageBinding.getRoot();
        setContentView(view);
        adapter = new DeleteRecycle(this);
        deletePageBinding.dRv.setAdapter(adapter);
        deletePageBinding.dRv.setLayoutManager(new LinearLayoutManager(this));
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
                        listId.clear();
                        listNum.clear();

                        for (QueryDocumentSnapshot ds : value) {

                            listName.add(ds.getString("brandName"));
                            listImage.add(ds.getString("image"));
                            listId.add(String.valueOf(ds.getLong("id")));
                            listNum.add(ds.getId());
                        }
                    }

                    Toast.makeText(this, "Fetched", Toast.LENGTH_SHORT).show();
                    adapter.getdata(listId,listImage,listName,listNum);

                        get.getPath(listNum);



                });
    }
}