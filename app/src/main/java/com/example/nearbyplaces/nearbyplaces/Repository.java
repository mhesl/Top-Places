package com.example.nearbyplaces.nearbyplaces;

import com.example.nearbyplaces.webservice.apimodel.Response;

import io.reactivex.Observable;

public interface Repository {

    Observable<Response> getResultsFromMemory();

    Observable<Response> getResultsFromNetwork();

    Observable<Response> getResultData();


}
