package com.mid_banchers.starting_again;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Dialog  extends MaterialAlertDialogBuilder {
    private String brandName ;
    private String brandImage;
    private Context context;

    public Dialog(@NonNull Context context, String brandName, String brandImage) {
        super(context);
        this.brandName = brandName;
        this.brandImage = brandImage;
        this.context = context;
    }


    @NonNull
    @Override
    public MaterialAlertDialogBuilder setTitle(int titleId) {
        return super.setTitle("Quick View");
    }

    @NonNull
    @Override
    public MaterialAlertDialogBuilder setView(View layout) {
  View view = LayoutInflater.from(context).inflate(R.layout.brand,null);
        TextView textView =view.findViewById(R.id.bName);
        ImageView imageView = view.findViewById(R.id.bLogo);


        textView.setText(brandName);
        Glide.with(context).load(brandImage)
                .into(imageView);




return setView(view);
    }


}
