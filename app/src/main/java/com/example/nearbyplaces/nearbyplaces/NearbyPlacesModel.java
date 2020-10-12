package com.example.nearbyplaces.nearbyplaces;

import com.example.nearbyplaces.webservice.apimodel.Venue;

import io.reactivex.Observable;

public class NearbyPlacesModel implements NearbyPlacesActivityMVP.Model {
    private Repository repository;

    public NearbyPlacesModel(Repository repository) {
        this.repository = repository;
    }


    @Override
    public Observable<Venue> result() {
        return repository.getResultData();
    }
}
