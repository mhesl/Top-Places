package com.example.nearbyplaces.nearbyplaces;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearbyplaces.R;
import com.example.nearbyplaces.nearbyplaces.interfaces.RecyclerViewCLickListener;
import com.example.nearbyplaces.webservice.apimodel.Venue;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements RecyclerViewCLickListener {

    private RecyclerView recyclerView;
    private List<Venue> dataSet;
    private RecyclerAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.main_fragment, container, false);
        recyclerView = view.findViewById(R.id.main_recycler_view);
        dataSet = new ArrayList<>();
        adapter = new RecyclerAdapter(dataSet, this, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onClickListener(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("position", String.valueOf(position));
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_mainFragment_to_detailFragment, bundle);
    }
}
