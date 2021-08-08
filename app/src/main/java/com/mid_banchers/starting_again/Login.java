package com.mid_banchers.starting_again;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.mid_banchers.starting_again.databinding.LoginBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login extends AppCompatActivity {
    FirebaseFirestore fb = FirebaseFirestore.getInstance();
    LoginBinding binding ;
    List<DataModelPassword> data = new ArrayList() ;
//    Map<Class<DataModelPassword>, DataModelPassword> well = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.signUpBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this,SignUp.class);
            startActivity(intent);
        });
        binding.forgotPassword.setOnClickListener(v -> {
            Intent intent = new Intent(this,PasswordReset.class);
            startActivity(intent);
        });
        binding.progressBar2.setVisibility(View.GONE);

        binding.loginBtn.setOnClickListener(v -> {
            binding.progressBar2.setVisibility(View.VISIBLE);
            fb.collection("Password")
                    .whereEqualTo("email",binding.userSignUp.getText().toString())
                    .whereEqualTo("password",binding.signUpPassword.getText().toString())


                    .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (DocumentSnapshot df : queryDocumentSnapshots.getDocuments()){
                        data.clear();
//                        well.put(DataModelPassword.class,df.toObject(DataModelPassword.class));

//                        Toast.makeText(Login.this, "aaa"+well.get(""), Toast.LENGTH_SHORT).show();

                        data.add(df.toObject(DataModelPassword.class));

                    }
                    if(data.get(0).getEmail().equals(binding.userSignUp.getText().toString())&&
                        data.get(0).getPassword().equals(binding.signUpPassword.getText().toString())){
                        binding.progressBar2.setVisibility(View.GONE);
                        Intent intent = new Intent(Login.this,MainActivity.class);
                        startActivity(intent);


                    }else{

                        binding.progressBar2.setVisibility(View.GONE);
                        Toast.makeText(Login.this, "Account Does Not Exits", Toast.LENGTH_SHORT).show();


                    }






//                    Toast.makeText(Login.this, "Welcome "+data.get(0).getUser(), Toast.LENGTH_SHORT).show();
                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    binding.progressBar2.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();

                }});

        });

    }
}