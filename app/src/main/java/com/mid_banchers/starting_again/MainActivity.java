package com.mid_banchers.starting_again;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.mid_banchers.starting_again.databinding.ActivityMainBinding;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ActivityMainBinding binding;
    private static final String TAG ="MainActivity De";
    String path;
    Object pat;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


//        db.collection("Brands")
//                .whereEqualTo("id", 3)
//                .get()
//                .addOnSuccessListener(queryDocumentSnapshots -> {
//
//                    for (DocumentSnapshot ds : queryDocumentSnapshots.getDocuments()) {
//
//                        setData(ds.getId());
//                    }
//                });


        binding.btnAddData.setOnClickListener(view1 -> {
            Intent intent = new Intent(this, AddData.class);
            startActivity(intent);


//
        });
        binding.timeUpd.setOnClickListener(v -> {
            Map<String, Object> data = new HashMap<>();
            data.put("addedOn", Timestamp.now());
            db.collection("Brands")
                    .addSnapshotListener((value, error) -> {
                        if (error != null) {
                            Log.w("Fail", error);
                        } else {
                            Log.d(TAG, " Data Exists");

                            for (QueryDocumentSnapshot ds : value) {

                                db.collection("Brands")
                                        .document(ds.getId())
                                        .update(data);

                            }
                            Log.d(TAG, "Updated");
                        }
                    });
//            db.collection("Brands").get().
//                    .document(path)
//                        .update(data)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Map<String,Timestamp> time = new HashMap<>();
//                    time.put("addedOn",null);
//                    db.collection("Brands")
//                            .document()
//                            .set(time);
//
//
//                    Toast.makeText(MainActivity.this, "Fialed", Toast.LENGTH_SHORT).show();
//                }
//            });


        });


        binding.btnDialog.setOnClickListener(v -> {
            String names[] = {"Usama", "Zain", "Umair"};
            MaterialAlertDialogBuilder popUp = new MaterialAlertDialogBuilder(MainActivity.this);
            popUp.setTitle("Names");
            popUp.setItems(names, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 1) {
                        Toast.makeText(MainActivity.this, "Option" + which, Toast.LENGTH_SHORT).show();
                    }
                    if (which == 2) {
                        Toast.makeText(MainActivity.this, "Option" + which, Toast.LENGTH_SHORT).show();
                    }
                    if (which == 3) {
                        Toast.makeText(MainActivity.this, "Option" + which, Toast.LENGTH_SHORT).show();
                    }


                }
            });
            popUp.show();
        });

        binding.next.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DropTest.class);
            startActivity(intent);
        });


        binding.nPassBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        });
        binding.rPassBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this,PasswordReset.class);
            startActivity(intent);
        });

    }
//    public void getPath(List<String>path1){
//        path.addAll(path1);
//
//    }


//    private void setData(String id) {
//
//        Map<String, Object> data = new HashMap<>();
//        data.put("id", 500);
//
//        db.collection("Brands")
//                .document(id)
//                .update(data);
//    }


}