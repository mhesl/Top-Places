package com.example.nearbyplaces.nearbyplaces;

import android.os.Bundle;
import android.util.Log;

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

    public static String stringLatitude;
    public static String stringLongitude;
    DetailFragment detailFragment;
    private FragmentTransaction ft;
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_places);
        // getting latitude and longitude of location from intent
        stringLatitude = getIntent().getStringExtra("latitude");
        stringLongitude = getIntent().getStringExtra("longitude");
        Log.d("yeap", stringLatitude + stringLongitude);
        ((App) getApplication()).getComponent().inject(this);
        mainFragment = new MainFragment(this);
        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.main_fragment_container_view, mainFragment);
        ft.commit();
    }

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