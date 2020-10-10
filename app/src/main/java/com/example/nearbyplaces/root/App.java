package com.example.nearbyplaces.root;

import android.app.Application;

import com.example.nearbyplaces.nearbyplaces.NearbyPlacesModule;
import com.example.nearbyplaces.webservice.ApiModuleForPlaces;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .nearbyPlacesModule(new NearbyPlacesModule())
                .apiModuleForPlaces(new ApiModuleForPlaces())
                .build();
    }


    public ApplicationComponent getComponent() {
        return component;
    }
}
