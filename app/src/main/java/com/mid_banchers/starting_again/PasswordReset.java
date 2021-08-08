package com.mid_banchers.starting_again;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mid_banchers.starting_again.databinding.ActivityPasswordResetBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PasswordReset extends AppCompatActivity {
FirebaseFirestore db = FirebaseFirestore.getInstance();
ActivityPasswordResetBinding binding;

String checkEmail;

List<DataModelPassword> dataModelPasswordList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPasswordResetBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.confrimEmailGroup.setVisibility(View.VISIBLE);
        binding.newPasswordGroup.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);




        binding.checkEmailBtn.setOnClickListener(v -> {
            binding.progressBar.setVisibility(View.VISIBLE);
            db.collection("Password")
                    .whereEqualTo("email",binding.checkEmail.getText().toString())
                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    for (DocumentSnapshot ds : queryDocumentSnapshots.getDocuments()) {

                        dataModelPasswordList.add(ds.toObject(DataModelPassword.class));
//                    path = ds.getId();
                        Toast.makeText(PasswordReset.this, "Matched", Toast.LENGTH_SHORT).show();

//                        dataModelPasswordList.add(DataModelPassword.class);

                    }
                    if (dataModelPasswordList.get(0).getEmail().equals(binding.checkEmail.getText().toString())){

                    binding.progressBar.setVisibility(View.GONE);
                    binding.confrimEmailGroup.setVisibility(View.GONE);
                    binding.newPasswordGroup.setVisibility(View.VISIBLE);}
                    else{
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(PasswordReset.this, "Email Doesn't Exits", Toast.LENGTH_SHORT).show();
                    }


                }
            }).addOnFailureListener(e -> {
                Toast.makeText(this, "Email Does not Matched", Toast.LENGTH_SHORT).show();

            });


//            getData();


        });

        binding.resetBtn.setOnClickListener(v -> {
            binding.progressBar.setVisibility(View.VISIBLE);
            Map<String,Object> data = new HashMap<>();
            data.put("password",binding.newPass.getText().toString());
            data.put("changedOn", Timestamp.now());

          if (dataModelPasswordList.get(0).getPassword().equals(binding.oldPass.getText().toString())){

              db.collection("Password")
                      .whereEqualTo("password",binding.oldPass.getText().toString())
                      .get()
                      .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                          @Override
                          public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                              for (DocumentSnapshot ds : queryDocumentSnapshots.getDocuments()) {
                                  updateData(data, ds.getId());
                              }

                          }


                      });

          }else {
              binding.progressBar.setVisibility(View.GONE);
              Toast.makeText(PasswordReset.this, "Password not Exits", Toast.LENGTH_SHORT).show();
          }
//            updateData(data);


        });

    }
    public void updateData(Map<String,Object> data, String path){
        db.collection("Password")
                .document(path)
                .update(data)
                .addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(PasswordReset.this, "Password Reset", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PasswordReset.this, "error", Toast.LENGTH_SHORT).show();
            }
        });


    }
//    public void getData(){
//
//        db.collection("password")
//                .whereEqualTo("email",binding.checkEmail.getText().toString())
//                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//                for (DocumentSnapshot ds : queryDocumentSnapshots.getDocuments()) {
//
//                    dataModelPasswordList.add(ds.toObject(DataModelPassword.class));
////                    path = ds.getId();
//
////                        dataModelPasswordList.add(DataModelPassword.class);
//
//                }
//
//                binding.progressBar.setVisibility(View.GONE);
//                binding.confrimEmailGroup.setVisibility(View.GONE);
//                binding.newPasswordGroup.setVisibility(View.VISIBLE);
//
//
//            }
//        }).addOnFailureListener(e -> {
//            Toast.makeText(this, "Email Does not Matched", Toast.LENGTH_SHORT).show();
//
//        });
//
//    }
}