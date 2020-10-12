package com.example.nearbyplaces.nearbyplaces;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearbyplaces.R;
import com.example.nearbyplaces.nearbyplaces.interfaces.RecyclerViewCLickListener;
import com.example.nearbyplaces.root.App;
import com.example.nearbyplaces.webservice.apimodel.Venue;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainFragment extends Fragment implements RecyclerViewCLickListener, NearbyPlacesActivityMVP.View {

    @Inject
    NearbyPlacesActivityMVP.Presenter presenter;

    private RecyclerView recyclerView;
    private List<Venue> dataSet;
    private RecyclerAdapter adapter;


    public MainFragment(RecyclerViewCLickListener cLickListener) {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.main_fragment, container, false);
        ((App) getActivity().getApplication()).getComponent().inj(this);
        recyclerView = view.findViewById(R.id.main_recycler_view);
        dataSet = new ArrayList<>();
        adapter = new RecyclerAdapter(dataSet, this, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onClickListener(int position) {


    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    public void updateData(Venue viewModel) {
        dataSet.add(viewModel);
        adapter.notifyItemInserted(dataSet.size() - 1);
    }
}
