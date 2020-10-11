package com.example.nearbyplaces.nearbyplaces;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nearbyplaces.R;
import com.example.nearbyplaces.root.ApplicationModule;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ApplicationModule applicationModule;
    private List<ViewModel> dataSet;


    public RecyclerAdapter(List<ViewModel> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_model, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.place_name.setText(dataSet.get(position).getName());
        holder.place_address.setText(dataSet.get(position).getStreetName());
        Glide.with(applicationModule.provideContext()).load(holder.place_image).centerCrop().into(holder.place_image);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView place_image;
        private TextView place_name;
        private TextView place_address;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            place_address = itemView.findViewById(R.id.place_address);
            place_image = itemView.findViewById(R.id.place_image);
            place_name = itemView.findViewById(R.id.place_name);
        }
    }
}
