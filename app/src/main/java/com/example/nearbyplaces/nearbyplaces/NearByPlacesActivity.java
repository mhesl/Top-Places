package com.example.nearbyplaces.nearbyplaces;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.nearbyplaces.R;
import com.example.nearbyplaces.nearbyplaces.interfaces.RecyclerViewCLickListener;
import com.example.nearbyplaces.root.App;
import com.example.nearbyplaces.webservice.apimodel.Venue;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class NearByPlacesActivity extends AppCompatActivity implements RecyclerViewCLickListener {


    @Inject
    NearbyPlacesActivityMVP.Presenter presenter;

    FragmentTransaction ft;
    MainFragment mainFragment;
    List<Venue> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_places);
        ((App) getApplication()).getComponent().inject(this);
        mainFragment = new MainFragment(this);
        data = new ArrayList<>();
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment_container_view, mainFragment);
        ft.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }


    @Override
    public void onClickListener(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("position", String.valueOf(position));
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);
        ft.hide(mainFragment);
        ft.replace(R.id.main_fragment_container_view, detailFragment);
    }
}