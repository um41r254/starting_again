package com.mid_banchers.starting_again;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mid_banchers.starting_again.databinding.ActivityNewPasswordBinding;

import java.util.HashMap;
import java.util.Map;

public class NewPassword extends AppCompatActivity {
    ActivityNewPasswordBinding binding;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewPasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
//        String pass = binding.nPass.getText().toString();
//        String cPass =binding.cPass.getText().toString();

        binding.addPass.setOnClickListener(v -> {
            Map<String,Object> data = new HashMap<>();
            data.put("password",binding.nPass.getText().toString());
            data.put("addedon", Timestamp.now());
            db.collection("Password")
                    .document()
                    .set(data).addOnSuccessListener(aVoid -> {
                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
                    });
            

//       if (pass == cPass) {
//
//           Toast.makeText(this, "Matched", Toast.LENGTH_SHORT).show();
//           
//       }
//       else
//           Toast.makeText(this, "Pa"+pass, Toast.LENGTH_SHORT).show();


        });
        
    }


}