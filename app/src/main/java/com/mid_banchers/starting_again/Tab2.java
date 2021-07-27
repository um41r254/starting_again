package com.mid_banchers.starting_again;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.firestore.QuerySnapshot;
import com.mid_banchers.starting_again.databinding.FragmentTab2Binding;

import java.util.ArrayList;
import java.util.List;


public class Tab2 extends Fragment {
//    RecyclerView reshow;
    RecycleDirectAdapter AdapterDirect;
    List<String> name = new ArrayList<>();
    List<String> image = new ArrayList<>();
    List<String> id = new ArrayList<>();




    public Tab2() {
        // Required empty public constructor
    }



private FragmentTab2Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         binding=FragmentTab2Binding.inflate(inflater, container, false);
         View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AdapterDirect = new RecycleDirectAdapter(getContext());
        binding.rvShow.setAdapter(AdapterDirect);
        binding.rvShow.setLayoutManager(new GridLayoutManager(getContext(),2));

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Brands")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(DocumentSnapshot Ds : queryDocumentSnapshots.getDocuments())
                        {

                            name.add(Ds.getString("brandName"));
                            image.add(Ds.getString("image"));
                            id.add(Ds.getId());
                        }
                        AdapterDirect.getData(id,image,name);

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}