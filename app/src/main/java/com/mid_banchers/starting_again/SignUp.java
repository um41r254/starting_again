package com.mid_banchers.starting_again;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;

import com.mid_banchers.starting_again.databinding.ActivitySignUpBinding;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    ActivitySignUpBinding binding;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG  = "SignUp De";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.progressBarS.setVisibility(View.GONE);

        binding.newSignUpBtn.setOnClickListener(v -> {

            Map<String,Object> data = new HashMap<>();
            data.put("password",binding.newPassword.getText().toString());
            data.put("addedOn",Timestamp.now());
            data.put("email",binding.newEmail.getText().toString());
            data.put("user",binding.newUserName.getText().toString());

            binding.progressBarS.setVisibility(View.VISIBLE);

            String newPassword = binding.newPassword.getText().toString();
            String confirmPassword =binding.confirmPassword.getText().toString();


            if (TextUtils.equals(newPassword,confirmPassword))  {
                Log.d(TAG, "password: equals");

            db.collection("Password")
                    .document()
                    .set(data).addOnSuccessListener(aVoid -> {
                binding.progressBarS.setVisibility(View.GONE);
                Log.d(TAG, "Data Added");
                Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show();

                Intent intent =new Intent(this,Login.class);
                startActivity(intent);

                    });






       }
       else {
                binding.progressBarS.setVisibility(View.GONE);
                Toast.makeText(this, "Password Not Matched", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Password Not Matched" );
            }

        });
        
    }


}