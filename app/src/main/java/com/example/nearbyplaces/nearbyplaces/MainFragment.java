package com.example.nearbyplaces.nearbyplaces;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearbyplaces.BaseFragment;
import com.example.nearbyplaces.R;
import com.example.nearbyplaces.database.DataBaseHelper;
import com.example.nearbyplaces.database.DataBaseModel;
import com.example.nearbyplaces.nearbyplaces.interfaces.RecyclerViewCLickListener;
import com.example.nearbyplaces.root.App;
import com.example.nearbyplaces.webservice.GPSTracker;
import com.example.nearbyplaces.webservice.apimodel.Venue;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainFragment extends BaseFragment implements NearbyPlacesActivityMVP.View {

    @Inject
    NearbyPlacesActivityMVP.Presenter presenter;


    private List<Venue> dataSet;
    private RecyclerAdapter adapter;
    private RecyclerViewCLickListener cLickListener;
    private boolean isDataBaseCleaned = false;
    private RecyclerView recyclerView;
    private boolean isInit = false;
    private boolean isConnected;


    public MainFragment(RecyclerViewCLickListener cLickListener) {
        this.cLickListener = cLickListener;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.main_fragment, container, false);
        ((App) requireActivity().getApplication()).getComponent().inj(this);
        ConnectivityManager cm =
                (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        recyclerView = view.findViewById(R.id.main_recycler_view);
        dataSet = new ArrayList<>();
        if (!isConnected) {
            adapter = new RecyclerAdapter(dataSet, cLickListener, getContext(), isConnected);
            recyclerView.setAdapter(adapter);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //check if GPS enabled
        GPSTracker gpsTracker = new GPSTracker(getActivity());
        if (gpsTracker.getIsGPSTrackingEnabled() && gpsTracker.getLatitude() != 0) {
            NearByPlacesActivity.stringLatitude = String.valueOf(gpsTracker.getLatitude());
            NearByPlacesActivity.stringLongitude = String.valueOf(gpsTracker.getLongitude());
        }
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.loadData();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.rxUnsubscribe();
    }


    @Override
    public void updateData(Venue viewModel) {
        if (!isDataBaseCleaned) {
            DataBaseHelper.getInstance(getActivity()).deleteAllPosts();
            isDataBaseCleaned = true;
        }
        DataBaseHelper.getInstance(getActivity()).addPlace(
                new DataBaseModel(
                        viewModel.getLocation().getAddress(),
                        viewModel.getName(),
                        viewModel.getLocation().getFormattedAddress().get(0),
                        viewModel.getLocation().getCrossStreet(),
                        viewModel.getLocation().getCity(),
                        viewModel.getLocation().getDistance()));
        dataSet.add(viewModel);
        if (!isInit) {
            adapter = new RecyclerAdapter(dataSet, cLickListener, getContext(), isConnected);
            recyclerView.setAdapter(adapter);
            isInit = true;
        }
        adapter.notifyItemInserted(dataSet.size() - 1);

    }
}
