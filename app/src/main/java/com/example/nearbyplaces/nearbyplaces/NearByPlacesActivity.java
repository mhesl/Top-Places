package com.example.nearbyplaces.nearbyplaces;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.nearbyplaces.BaseFragment;
import com.example.nearbyplaces.R;
import com.example.nearbyplaces.nearbyplaces.interfaces.RecyclerViewCLickListener;
import com.example.nearbyplaces.root.App;

import java.util.List;

import javax.inject.Inject;

public class NearByPlacesActivity extends AppCompatActivity implements RecyclerViewCLickListener {


    @Inject
    NearbyPlacesActivityMVP.Presenter presenter;

    private FragmentTransaction ft;
    private MainFragment mainFragment;
    private SharedPreferences sharedPreferences;
    public static String stringLatitude;
    public static String stringLongitude;
    private String lastLatitude;
    private String lastLongitude;
    private DetailFragment detailFragment;
    private boolean isFar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_places);
        sharedPreferences = getSharedPreferences("POSITION", MODE_PRIVATE);

        lastLatitude = sharedPreferences.getString("latitude", "");
        lastLongitude = sharedPreferences.getString("longitude", "");

        // getting latitude and longitude of location from intent
        stringLatitude = getIntent().getStringExtra("latitude");
        stringLongitude = getIntent().getStringExtra("longitude");

        if (lastLongitude.equals("") || lastLatitude.equals("")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("latitude", stringLatitude);
            editor.putString("longitude", stringLongitude);
            editor.apply();
            isFar = true;
        } else {
            isFar = distance(
                    Double.parseDouble(lastLatitude),
                    Double.parseDouble(lastLongitude),
                    Double.parseDouble(stringLatitude),
                    Double.parseDouble(stringLongitude));
            if (isFar) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("latitude", stringLatitude);
                editor.putString("longitude", stringLongitude);
                editor.apply();
            }
        }


        ((App) getApplication()).getComponent().inject(this);
        Bundle bundle = new Bundle();
        bundle.putBoolean("distance", isFar);
        mainFragment = new MainFragment(this);
        mainFragment.setArguments(bundle);
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.main_fragment_container_view, mainFragment);
        ft.commit();
    }


    // handling beck press button in fragments
    @Override
    public void onBackPressed() {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        boolean handled = false;
        for (Fragment f : fragmentList) {
            if (f instanceof BaseFragment) {
                handled = ((BaseFragment) f).onBackPressed();
                if (handled) {
                    getSupportFragmentManager().beginTransaction().show(mainFragment).remove(detailFragment).commit();
                }
            }
        }

        if (!handled) {
            super.onBackPressed();
        }

    }


    // getting distance between two different  latitude and longitude
    private boolean distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return !(dist < 0.1);

    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }


    // click listener for moving to detail fragment
    @Override
    public void onClickListener(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("position", String.valueOf(position));
        detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);
        ft = getSupportFragmentManager().beginTransaction();
        ft.hide(mainFragment);
        ft.add(R.id.main_fragment_container_view, detailFragment);
        ft.commit();
    }
}