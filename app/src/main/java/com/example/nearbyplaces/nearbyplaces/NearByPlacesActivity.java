package com.example.nearbyplaces.nearbyplaces;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nearbyplaces.R;
import com.example.nearbyplaces.root.App;

import javax.inject.Inject;

public class NearByPlacesActivity extends AppCompatActivity implements NearbyPlacesActivityMVP.View {


    @Inject
    NearbyPlacesActivityMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_places);

        ((App) getApplication()).getComponent().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.rxUnsubscribe();
    }

    @Override
    public void UpdateData(ViewModel viewModel) {

    }
}