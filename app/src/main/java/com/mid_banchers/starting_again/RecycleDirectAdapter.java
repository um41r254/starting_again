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

import java.util.ArrayList;
import java.util.List;

public class RecycleDirectAdapter extends RecyclerView.Adapter<RecycleDirectAdapter.ViewHolderDirect> {
    private Context context;
    private static final String TAG ="RecycleDirectAdapter De";
    private List<DataModelBrands> dataModelBrandsList= new ArrayList<>();

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
        if (dataModelBrandsList.size() > 0) {
            holder.textView.setText(dataModelBrandsList.get(position).getBrandName());
            Glide.with(context).load(dataModelBrandsList.get(position).getImage())
                    .into(holder.imageView);


        }

    }

    @Override
    public int getItemCount() {
        return dataModelBrandsList.size();
    }

    public void getData(List<DataModelBrands> data) {

       dataModelBrandsList.clear();

       dataModelBrandsList.addAll(data);


        notifyDataSetChanged();
    }

    public class ViewHolderDirect extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textView ,text1,text2;
        ImageView imageView,imageV;

        public ViewHolderDirect(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.brand);
            textView = itemView.findViewById(R.id.bName);
            imageView = itemView.findViewById(R.id.bLogo);
//          String name[]= listName.toArray(new String[0]);

            cardView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {



                  String name = dataModelBrandsList.get(ViewHolderDirect.this.getAdapterPosition()).getBrandName();
                  String image= dataModelBrandsList.get(ViewHolderDirect.this.getAdapterPosition()).getImage();
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
//                               }
//                           });popUp.show();


                    View view = LayoutInflater.from(context).inflate(R.layout.showing_string,null);
                    text1 = view.findViewById(R.id.string1);
                    text2 = view.findViewById(R.id.string2);
//                    imageV = view.findViewById(R.id.string1);


                    text1.setText(name);
                    text2.setText(image);
//                    Glide.with(context).load(image).into(imageV);
                          MaterialAlertDialogBuilder popUP = new MaterialAlertDialogBuilder(context);
                          popUP.setView(view);
                          popUP.show();

                }
            });

        }

    }
}
