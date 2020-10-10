package com.example.nearbyplaces.nearbyplaces;

import io.reactivex.Observable;

public class NearbyPlacesModel implements NearbyPlacesActivityMVP.Model {
    private Repository repository;

    public NearbyPlacesModel(Repository repository) {
        this.repository = repository;
    }


    @Override
    public Observable<ViewModel> result() {
        return null;
    }
}
