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
    private static final String TAG ="DeletePage De";
    private List<DataModelBrands> dataModelBrandsList= new ArrayList<>();
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

                      dataModelBrandsList.clear();


                        for (QueryDocumentSnapshot ds : value) {

                            dataModelBrandsList.add(ds.toObject(DataModelBrands.class));
                            Log.d(TAG, "onSuccess: " + dataModelBrandsList.get(Integer.parseInt(ds.getId())).getBrandName());

                        }
                    }

                    Toast.makeText(this, "Fetched", Toast.LENGTH_SHORT).show();
                    adapter.getData(dataModelBrandsList);

//                        get.getPath(listNum);



                });
    }
}