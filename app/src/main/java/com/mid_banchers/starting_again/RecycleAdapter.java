package com.mid_banchers.starting_again;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
private Context context;
private List <String>  id= new ArrayList<>();
private List <String>  name= new ArrayList<>();
private List <String>  image = new ArrayList<>();

    public RecycleAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.brand,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (id.size()>0){
            holder.textView.setText(name.get(position));
            Glide.with(context).load(image.get(position))
                    .into(holder.imageView);


        }

    }

    @Override
    public int getItemCount() {
        if (id.size()==0){
        return 4;
    }else {
            return id.size();
        }
    }
    public void getdata(List<String> data1 ,List<String> data2 ,List<String> data3){
        id.addAll(data1);
        image.addAll(data2);
        name.addAll(data3);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView  textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView =  itemView.findViewById(R.id.brand);
            textView = itemView.findViewById(R.id.bname);
            imageView = itemView.findViewById(R.id.bLogo);

        }
    }
}