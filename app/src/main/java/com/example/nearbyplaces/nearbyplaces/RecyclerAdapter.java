package com.example.nearbyplaces.nearbyplaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearbyplaces.R;
import com.example.nearbyplaces.database.DataBaseHelper;
import com.example.nearbyplaces.database.DataBaseModel;
import com.example.nearbyplaces.nearbyplaces.interfaces.RecyclerViewCLickListener;
import com.example.nearbyplaces.webservice.apimodel.Venue;

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
        if (dataSet.size() != 0) {
            holder.place_name.setText(dataSet.get(position).getName());
            holder.place_address.setText(dataSet.get(position).getLocation().getAddress());
        } else {
            List<DataBaseModel> models = DataBaseHelper.getInstance(context).getAllPosts();
            holder.place_name.setText(models.get(position).getPlaceName());
            holder.place_address.setText(models.get(position).getVenueName());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecyclerAdapter.itemListener.onClickListener(position);
            }
        });
//        System.out.println(dataSet.get(position)
//                .getCategories().get(0)
//                .getIcon().getPrefix());
//        GlideUrl glideUrl = new GlideUrl(dataSet.get(position)
//                .getCategories().get(0)
//                .getIcon().getPrefix(), new LazyHeaders.Builder()
//                .addHeader("CLIENT_ID", "PV5SZYDISY1OXOT4RG2RMVRMK2O2KSKCS1XA040HNT0C4GSE")
//                .addHeader("CLIENT_SECRET" , "2QYALZR2AGFD0CKBO150MAYHDJ0Y22YLN0RCK2APX0BNSYCP").build());
//        Glide.with(context)
//                .load(glideUrl)
//                .centerCrop()
//                .into(holder.place_image);
    }

    @Override
    public int getItemCount() {
        if (dataSet.size() == 0)
            return DataBaseHelper.getInstance(context).getAllPosts().size();
        else
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
