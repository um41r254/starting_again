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

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
private Context context;

    private static final String TAG ="RecycleAdapter De";
    private List<DataModelBrands> dataModelBrandsList= new ArrayList<>();
    public RecycleAdapter(Context context) {
        this.context = context;
    }
BrandBinding binding;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
binding = BrandBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        View view =binding.getRoot();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (dataModelBrandsList.size()>0){
            binding.bName.setText(dataModelBrandsList.get(position).getBrandName());
            Glide.with(context).load(dataModelBrandsList.get(position).getImage())
                    .into(binding.bLogo);


        }

    }

    @Override
    public int getItemCount() {
        if (dataModelBrandsList.size()==0){
        return 10;
    }else {
            return dataModelBrandsList.size();
        }
    }
    public void getData(List<DataModelBrands> data){
        dataModelBrandsList.clear();

        dataModelBrandsList.addAll(data);

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

//        CardView cardView;
//        TextView  textView;
//        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            cardView =  itemView.findViewById(R.id.brand);
//            textView = itemView.findViewById(R.id.bName);
//            imageView = itemView.findViewById(R.id.bLogo);
            binding.brand.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = dataModelBrandsList.get(RecycleAdapter.ViewHolder.this.getAdapterPosition()).getBrandName();
                    String image= dataModelBrandsList.get(RecycleAdapter.ViewHolder.this.getAdapterPosition()).getImage();

                    ShowingStringBinding showingStringBinding;
                    showingStringBinding= ShowingStringBinding.inflate(LayoutInflater.from(context));
                    View view = showingStringBinding.getRoot();

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
