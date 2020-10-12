package com.example.nearbyplaces.nearbyplaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nearbyplaces.R;
import com.example.nearbyplaces.nearbyplaces.interfaces.RecyclerViewCLickListener;
import com.example.nearbyplaces.webservice.apimodel.Venue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    public static RecyclerViewCLickListener itemListener;
    private List<Venue> dataSet;
    private Context context;


    public RecyclerAdapter(List<Venue> dataSet, RecyclerViewCLickListener itemListener, Context context) {
        this.dataSet = dataSet;
        RecyclerAdapter.itemListener = itemListener;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_model, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.place_name.setText(dataSet.get(position).getName());
        holder.place_address.setText(dataSet.get(position).getLocation().getAddress());
        try {
            Glide.with(context)
                    .load(new URL(dataSet.get(position)
                            .getCategories().get(0)
                            .getIcon().getPrefix()))
                    .centerCrop()
                    .into(holder.place_image);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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
            itemView.setOnClickListener(view -> RecyclerAdapter.itemListener.onClickListener(getLayoutPosition()));
        }
    }
}
