package com.mid_banchers.starting_again;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.mid_banchers.starting_again.databinding.ActivityPasswordResetBinding;

import java.util.HashMap;
import java.util.Map;

import javax.xml.xpath.XPath;

public class PasswordReset extends AppCompatActivity {
FirebaseFirestore db = FirebaseFirestore.getInstance();
ActivityPasswordResetBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPasswordResetBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.resetBtn.setOnClickListener(v -> {

            Map<String,Object> data = new HashMap<>();
            data.put("password",binding.newPass.getText().toString());
            data.put("changedOn", Timestamp.now());
            db.collection("Password")
                    .whereEqualTo("password",binding.oldPass.getText().toString())
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for (DocumentSnapshot ds : queryDocumentSnapshots.getDocuments())
                                updateData(data,ds.getId());

                        }
                    });

        });

    }
    public void updateData(Map<String,Object> data , String path){
        db.collection("Password")
                .document(path)
                .update(data)
                .addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(PasswordReset.this, "Password Rseted", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PasswordReset.this, "Password not Exits", Toast.LENGTH_SHORT).show();
            }
        });


    }
}