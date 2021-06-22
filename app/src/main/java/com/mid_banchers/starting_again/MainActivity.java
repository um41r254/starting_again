package com.mid_banchers.starting_again;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.dialog.MaterialDialogs;

public class MainActivity extends AppCompatActivity {
Button dia , page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dia = findViewById(R.id.dialog);
        dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String names[] ={"Usama","Zain","Umair"};
                MaterialAlertDialogBuilder popUp = new MaterialAlertDialogBuilder(MainActivity.this);
                popUp.setTitle("Names");
                popUp.setItems(names, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==1){
                            Toast.makeText(MainActivity.this, "Option"+which, Toast.LENGTH_SHORT).show();
                        }
                        if (which==2){
                            Toast.makeText(MainActivity.this, "Option"+which, Toast.LENGTH_SHORT).show();
                        }
                        if (which==3){
                            Toast.makeText(MainActivity.this, "Option"+which, Toast.LENGTH_SHORT).show();
                        }


                    }
                });
            }
        });

    }
}