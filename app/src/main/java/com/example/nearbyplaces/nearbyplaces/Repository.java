package com.example.nearbyplaces.nearbyplaces;

import com.example.nearbyplaces.webservice.apimodel.Result;

import io.reactivex.Observable;

public interface Repository {

    Observable<Result> getResultsFromMemory();

    Observable<Result> getResultsFromNetwork();

    Observable<Result> getResultData();


}
