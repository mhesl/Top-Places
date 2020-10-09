package com.example.nearbyplaces.root;

import com.example.nearbyplaces.nearbyplaces.NearByPlacesActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(NearByPlacesActivity target);
}
