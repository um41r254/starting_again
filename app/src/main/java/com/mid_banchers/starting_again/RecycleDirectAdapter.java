package com.mid_banchers.starting_again;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.mid_banchers.starting_again.databinding.BrandBinding;
import com.mid_banchers.starting_again.databinding.ShowingStringBinding;

import java.math.BigInteger;
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
        BrandBinding view = BrandBinding.inflate(LayoutInflater.from(parent.getContext()) , parent , false);
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand, parent, false);
        return new ViewHolderDirect(view); // holder name
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDirect holder, int position) {
        if (listID.size() > 0) {
            holder.binding.bName.setText(listName.get(position));
            Glide.with(context).load(listImage.get(position))
                    .into(holder.binding.bLogo);


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

        BrandBinding binding;

        public ViewHolderDirect(@NonNull BrandBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;


//          String name[]= listName.toArray(new String[0]);

            binding.brand.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    String name = listName.get(ViewHolderDirect.this.getAdapterPosition());
                    String image = listImage.get(ViewHolderDirect.this.getAdapterPosition());
//                  new Dialog(context,name,image)
//                          .show();


//                  MaterialAlertDialogBuilder popUp =  new MaterialAlertDialogBuilder(context);
//                            popUp.setTitle("Quick View");
//                           popUp.setItems(new String[]{name,image}, new DialogInterface.OnClickListener() {
//                               @Override
//                               public void onClick(DialogInterface dialog, int which) {
//                                   if (which==1){
//                                       Toast.makeText(context, "dsd", Toast.LENGTH_SHORT).show();
//                                   }
//                                }
//                           });popUp.show();
                    ShowingStringBinding showingStringBinding;
                    showingStringBinding= ShowingStringBinding.inflate(LayoutInflater.from(context));
                    View view = showingStringBinding.getRoot();


//                    View view = LayoutInflater.from(context).inflate(R.layout.showing_string, null);
//                    text1 = view.findViewById(R.id.string1);
//                    text2 = view.findViewById(R.id.string2);
//                    imageV = view.findViewById(R.id.string1);


                    showingStringBinding.string1.setText(name);
                    showingStringBinding.string2.setText(image);
//                    Glide.with(context).load(image).into(imageV);
                    MaterialAlertDialogBuilder popUP = new MaterialAlertDialogBuilder(context);
                    popUP.setView(view);
                    popUP.show();

                }
            });

        }

    }
}
