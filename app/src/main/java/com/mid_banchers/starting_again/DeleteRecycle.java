package com.mid_banchers.starting_again;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
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
    private static final String TAG ="DeleteRecycle de";
    private List<DataModelBrands> dataModelBrandsList= new ArrayList<>();

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

            holder.binding.idD.setText(String.valueOf(dataModelBrandsList.get(position).getId()));
            holder.binding.imgD.setText(dataModelBrandsList.get(position).getImage());
            holder.binding.nameD.setText(dataModelBrandsList.get(position).getBrandName());



    }

    @Override
    public int getItemCount() {
//        if (listNum.size()>0)
        return dataModelBrandsList.size();
//        else
//        return 7;
    }

    public void getData(List<DataModelBrands>data){
        dataModelBrandsList.clear();

      dataModelBrandsList.addAll(data);

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
                    String obj = dataModelBrandsList.get(DeleteViewHolder.this.getAdapterPosition()).getBrandName();
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
                                Log.d(TAG, "Dialog"+which);
                            }

                        }
                    }).show();
                }
            });











        }
    }
}

