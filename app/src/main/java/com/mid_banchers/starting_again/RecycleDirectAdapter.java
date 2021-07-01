package com.mid_banchers.starting_again;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class RecycleDirectAdapter extends RecyclerView.Adapter<RecycleDirectAdapter.ViewHolderDirect> {
    private Context context;
    private List<String> listID = new ArrayList<>();
    private List<String> listName = new ArrayList<>();
    private List<String> listImage = new ArrayList<>();

    public RecycleDirectAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolderDirect onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand, parent, false);
        return new ViewHolderDirect(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDirect holder, int position) {
        if (listID.size() > 0) {
            holder.textView.setText(listName.get(position));
            Glide.with(context).load(listImage.get(position))
                    .into(holder.imageView);


        }

    }

    @Override
    public int getItemCount() {
        return listID.size();
    }

    public void getData(List<String> data1, List<String> data2, List<String> data3) {

        listID.clear();
        listImage.clear();
        listName.clear();

        listID.addAll(data1);
        listImage.addAll(data2);
        listName.addAll(data3);
        notifyDataSetChanged();
    }

    public class ViewHolderDirect extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textView;
        ImageView imageView;

        public ViewHolderDirect(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.brand);
            textView = itemView.findViewById(R.id.bname);
            imageView = itemView.findViewById(R.id.bLogo);
//          String name[]= listName.toArray(new String[0]);

            cardView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                  String qqq = listName.get(ViewHolderDirect.this.getAdapterPosition());
                  String qqw = listID.get(ViewHolderDirect.this.getAdapterPosition());

                  MaterialAlertDialogBuilder popUp =  new MaterialAlertDialogBuilder(context);
                            popUp.setTitle("Quick View");
                           popUp.setItems(new String[]{qqq,qqw}, new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   if (which==1){
                                       Toast.makeText(context, "dsd", Toast.LENGTH_SHORT).show();
                                   }
                               }
                           });popUp.show();

                }
            });

        }

    }
}
