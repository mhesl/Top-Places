package com.example.nearbyplaces.root;

import com.example.nearbyplaces.nearbyplaces.NearByPlacesActivity;
import com.example.nearbyplaces.nearbyplaces.NearbyPlacesModule;
import com.example.nearbyplaces.webservice.ApiModuleForPlaces;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NearbyPlacesModule.class, ApiModuleForPlaces.class})
public interface ApplicationComponent {

    void inject(NearByPlacesActivity target);
}
