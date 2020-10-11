package com.example.nearbyplaces.nearbyplaces;

import com.example.nearbyplaces.webservice.apimodel.TopPlaces;

import io.reactivex.Observable;

public interface Repository {

    Observable<TopPlaces> getResultsFromMemory();

    Observable<TopPlaces> getResultsFromNetwork();

    Observable<TopPlaces> getResultData();


}
