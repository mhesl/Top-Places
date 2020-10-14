package com.example.nearbyplaces.nearbyplaces;

import com.example.nearbyplaces.webservice.NearbyPlacesApiService;
import com.example.nearbyplaces.webservice.apimodel.TopPlaces;
import com.example.nearbyplaces.webservice.apimodel.Venue;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class NearbyPlacesRepository implements Repository {

    private static final long STALE_MS = 30 * 1000; // Data is stale after 20 seconds
    private NearbyPlacesApiService nearbyPlacesApiService;
    private List<Venue> results;
    private long timestamp;


    public NearbyPlacesRepository(NearbyPlacesApiService nearbyPlacesApiService) {
        this.nearbyPlacesApiService = nearbyPlacesApiService;
        this.timestamp = System.currentTimeMillis();
        results = new ArrayList<>();
    }


    public boolean isUpToDate() {
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }

    @Override
    public Observable<Venue> getResultsFromMemory() {
        if (isUpToDate()) {
            return Observable.fromIterable(results);
        } else {
            timestamp = System.currentTimeMillis();
            results.clear();
            return Observable.empty();
        }
    }

    @Override
    public Observable<Venue> getResultsFromNetwork() {
        Observable<TopPlaces> topRatedObservable = nearbyPlacesApiService.getNearByPlaces();

        return topRatedObservable.concatMap((Function<TopPlaces, Observable<Venue>>) topPlaces ->
                Observable.fromIterable(topPlaces.getResponse().getVenues())
                        .doOnNext(venue ->
                                results.add(venue)));
    }

    @Override
    public Observable<Venue> getResultData() {
        return getResultsFromMemory().switchIfEmpty(getResultsFromNetwork());
    }
}
