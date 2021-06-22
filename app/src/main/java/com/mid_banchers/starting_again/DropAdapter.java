package com.mid_banchers.starting_again;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DropAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<String> list;


    public DropAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.list=objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return CreateDropDownView(position,convertView,parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return CreateDropDownView(position,convertView,parent);
    }
    public View CreateDropDownView(int position,  View convertView,  ViewGroup parent){
        if (position==0){
            Toast.makeText(context, "pos"+position, Toast.LENGTH_SHORT).show();
        }if (position==1){
            Toast.makeText(context, "pos"+position, Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(context,Tab.class);

        }if (position==2){
            Toast.makeText(context, "pos"+position, Toast.LENGTH_SHORT).show();
        }
        return LayoutInflater .from(context).inflate(R.layout.add,parent,false);
    }
}
