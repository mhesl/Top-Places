package com.example.nearbyplaces.nearbyplaces;


import com.example.nearbyplaces.webservice.NearbyPlacesApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NearbyPlacesModule {

    @Provides
    public NearbyPlacesActivityMVP.Presenter provideTopMoviesActivityPresenter(NearbyPlacesActivityMVP.Model topMoviesModel) {
        return new NearbyPlacesPresenter(topMoviesModel);
    }

    @Provides
    public NearbyPlacesActivityMVP.Model provideTopMoviesActivityModel(Repository repository) {
        return new NearbyPlacesModel(repository);
    }

    @Singleton
    @Provides
    public Repository provideRepo(NearbyPlacesApiService nearbyPlacesApiService) {
        return new NearbyPlacesRepository(nearbyPlacesApiService);
    }

}
