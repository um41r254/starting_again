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

import java.util.ArrayList;
import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder> {

    private Context context;
    private List<String> myData = new ArrayList<>();

    public AdapterRecycler(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (myData.size() > 0) {
            holder.bName.setText(myData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (myData.size() == 0) {
            return 5;
        } else {
            return myData.size();
        }

    }


    public void getData(List<String> data) {

        myData.addAll(data);
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView brand;
        TextView bName;
        ImageView bImage;

        public ViewHolder(@NonNull View layout) {
            super(layout);

            brand = layout.findViewById(R.id.brand);
            bImage = layout.findViewById(R.id.bLogo);
            bName = layout.findViewById(R.id.bname);

        }
    }
}
