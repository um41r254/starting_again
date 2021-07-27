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
    List<String> name = new ArrayList<>();
    List<String> image = new ArrayList<>();
    List<String> id = new ArrayList<>();


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
        db.collection("Products")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (DocumentSnapshot Ds : queryDocumentSnapshots.getDocuments()) {

                            name.add(Ds.getString("articleName"));
                            image.add(Ds.getString("image"));
                            id.add(Ds.getId());
                        }
                        Adapter.getdata(id, image, name);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }
}