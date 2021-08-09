package com.mid_banchers.starting_again;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mid_banchers.starting_again.databinding.FragmentTab1Binding;

import java.util.ArrayList;
import java.util.List;


public class Tab1 extends Fragment {
//    RecyclerView reload;
    RecycleAdapter Adapter;
    List<DataModelBrands> dataModelBrandsList = new ArrayList<>();
    private static final String  TAG = "Tab1 De";

    public Tab1() {


    }
private FragmentTab1Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentTab1Binding.inflate(inflater,container,false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Adapter = new RecycleAdapter(getContext());
        binding.rvLoad.setAdapter(Adapter);
        binding.rvLoad.setLayoutManager(new GridLayoutManager(getContext(), 2));

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Brands")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            for (DocumentSnapshot ds : queryDocumentSnapshots.getDocuments()) {

                                dataModelBrandsList.add(ds.toObject(DataModelBrands.class));
                                Log.d(TAG, "onSuccess: " + dataModelBrandsList.get(getId()).getBrandName());
                            }
                        }
                            Adapter.getData(dataModelBrandsList);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "Error Data not found" );

            }
        });


    }
}