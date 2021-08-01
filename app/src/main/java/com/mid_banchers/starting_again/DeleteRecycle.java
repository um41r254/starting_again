package com.mid_banchers.starting_again;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mid_banchers.starting_again.databinding.DeleteLayoutBinding;

import java.util.ArrayList;
import java.util.List;


public class DeleteRecycle extends RecyclerView.Adapter<DeleteRecycle.DeleteViewHolder> {
    private Context context;
    List<String>  listNum = new ArrayList<>();
    List<String>  listImage = new ArrayList<>();
    List<String>  listId = new ArrayList<>();
    List<String>  listName = new ArrayList<>();

    public DeleteRecycle(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DeleteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DeleteLayoutBinding view = DeleteLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);


        return new DeleteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeleteViewHolder holder, int position) {

            holder.binding.idD.setText(listId.get(position));
            holder.binding.imgD.setText(listImage.get(position));
            holder.binding.nameD.setText(listName.get(position));



    }

    @Override
    public int getItemCount() {
//        if (listNum.size()>0)
        return listNum.size();
//        else
//        return 7;
    }

    public void getdata(List<String>data1,List<String> data2, List<String> data3, List<String>data4){
        listImage.clear();
        listName.clear();
        listNum.clear();
        listId.clear();

        listId.addAll(data1);
        listImage.addAll(data2);
        listName.addAll(data3);
        listNum.addAll(data4);
        notifyDataSetChanged();



    }

    public class DeleteViewHolder extends RecyclerView.ViewHolder {
        DeleteLayoutBinding binding;
        public DeleteViewHolder(@NonNull DeleteLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            binding.data.setOnClickListener(new View.OnClickListener() {
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                @Override
                public void onClick(View v) {
                    String obj = listNum.get(DeleteViewHolder.this.getAdapterPosition());
//                    obj.get(db.collection("Brands").document().getId());

                    String yes[] = {"Delete"};


                    MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(context);
                    dialogBuilder.setItems(yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which==0){
                                db.collection("Brands")
                                        .document(obj)
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                Toast.makeText(context, "dfghj", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).show();
                }
            });











        }
    }
}

