package com.mid_banchers.starting_again;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    DataModelPassword obj;
    private static final String TAG = "Login De ";
//    List<DataModelPassword> data = new ArrayList() ;
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
                    .document(binding.userSignUp.getText().toString())

                    .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                   obj =  documentSnapshot.toObject(DataModelPassword.class);


                    if(obj.getPassword().equals(binding.signUpPassword.getText().toString())){
                        binding.progressBar2.setVisibility(View.GONE);
                        Intent intent = new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
                        Log.d(TAG, "Password Matched");



                    }else{

                        binding.progressBar2.setVisibility(View.GONE);
                        Toast.makeText(Login.this, "Account Does Not Exits", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Account Does Not Exits " );


                    }

//                   Toast.makeText(Login.this, "Welcome "+data.get(0).getUser(), Toast.LENGTH_SHORT).show();

            }

        }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    binding.progressBar2.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();

                }
        });

    });

}
}