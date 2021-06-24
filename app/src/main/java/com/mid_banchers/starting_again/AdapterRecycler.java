package com.mid_banchers.starting_again;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder>{

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView brand;
        TextView bName;
        ImageView bImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            brand.findViewById(R.id.brand);
            bImage.findViewById(R.id.bLogo);
            bName.findViewById(R.id.bname);

        }
    }
}
