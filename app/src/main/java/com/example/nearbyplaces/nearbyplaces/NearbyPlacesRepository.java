package com.example.nearbyplaces.nearbyplaces;

import com.example.nearbyplaces.webservice.apimodel.Result;

import io.reactivex.Observable;

public class NearbyPlacesRepository implements Repository {
    @Override
    public Observable<Result> getResultsFromMemory() {
        return null;
    }

    @Override
    public Observable<Result> getResultsFromNetwork() {
        return null;
    }

    @Override
    public Observable<Result> getResultData() {
        return null;
    }
}
