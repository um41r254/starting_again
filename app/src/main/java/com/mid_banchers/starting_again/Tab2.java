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

import java.util.ArrayList;
import java.util.List;


public class Tab2 extends Fragment {
    RecyclerView reshow;
    RecycleDirectAdapter AdapterDirect;
    List<String> name = new ArrayList<>();
    List<String> image = new ArrayList<>();
    List<String> id = new ArrayList<>();




    public Tab2() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reshow = view.findViewById(R.id.rv_show);
        AdapterDirect = new RecycleDirectAdapter(getContext());
        reshow.setAdapter(AdapterDirect);
        reshow.setLayoutManager(new GridLayoutManager(getContext(),2));

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