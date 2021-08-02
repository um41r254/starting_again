package com.mid_banchers.starting_again;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mid_banchers.starting_again.databinding.ActivityAddDataBinding;
import com.mid_banchers.starting_again.databinding.AddBinding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddData extends AppCompatActivity {
    private ActivityAddDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddDataBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        binding.addDoc.setOnClickListener(v -> {

            Map<String, Object> data = new HashMap<>();
            data.put("brandName", binding.brName.getText().toString());
            data.put("id", Integer.parseInt(binding.brId.getText().toString()));
            data.put("image", binding.brImage.getText().toString());
            data.put("addedOn", Timestamp.now());

            db.collection("Brands")
                    .document()
                    .set(data)
                    .addOnSuccessListener(aVoid ->
                            Toast.makeText(AddData.this, "Document Added", Toast.LENGTH_SHORT).show());
        });

        binding.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddData.this, DeletePage.class);
                startActivity(intent);
            }
        });

//        Map<String, Object> data = new HashMap<>();
//            data.put("name", text);
//            data.put("secondName", "Umair");
//            data.put("scores", scores);
//            data.put("class", list);
//            data.put("isNull", true);
//            data.put("addedOn", Timestamp.now());


//            db.collection("Brands")
//                    .document("ABC")
//                    .update(data)
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//
//                            Toast.makeText(MainActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
//                        }
//                    });
    }


}