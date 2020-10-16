package com.example.nearbyplaces.nearbyplaces;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.nearbyplaces.BaseFragment;
import com.example.nearbyplaces.R;
import com.example.nearbyplaces.database.DataBaseHelper;
import com.example.nearbyplaces.database.DataBaseModel;

import java.util.List;

public class DetailFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        assert getArguments() != null;
        String positionStr = getArguments().getString("position");
        assert positionStr != null;
        int position = Integer.parseInt(positionStr);

        List<DataBaseModel> data = DataBaseHelper.getInstance(requireActivity()).getAllPosts();

        TextView place_name = view.findViewById(R.id.placeName);
        TextView place_address = view.findViewById(R.id.placeAddress);
        TextView place_fullAddress = view.findViewById(R.id.placeFullAddress);
        TextView place_city = view.findViewById(R.id.placeCity);
        TextView place_distance = view.findViewById(R.id.placeDistance);
        TextView place_cross = view.findViewById(R.id.placeCross);

        place_name.setText(data.get(position).getPlaceName());
        place_address.setText(data.get(position).getVenueName());
        place_fullAddress.setText(data.get(position).getCompleteAddress());
        place_city.setText(data.get(position).getCity());
        place_distance.setText(String.valueOf(data.get(position).getDistance()));
        place_cross.setText(data.get(position).getCrossStreet());


        return view;
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }


}
