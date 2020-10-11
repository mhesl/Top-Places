package com.example.nearbyplaces.nearbyplaces;

import com.example.nearbyplaces.webservice.apimodel.Venue;

import io.reactivex.Observable;

public interface Repository {

    Observable<Venue> getResultsFromMemory();

    Observable<Venue> getResultsFromNetwork();

    Observable<Venue> getResultData();


}
