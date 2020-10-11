package com.example.nearbyplaces.nearbyplaces;

import com.example.nearbyplaces.webservice.NearbyPlacesApiService;
import com.example.nearbyplaces.webservice.apimodel.TopPlaces;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class NearbyPlacesRepository implements Repository {

    private static final long STALE_MS = 20 * 1000; // Data is stale after 20 seconds
    private NearbyPlacesApiService nearbyPlacesApiService;
    private List<TopPlaces> results;
    private long timestamp;

    public NearbyPlacesRepository(NearbyPlacesApiService nearbyPlacesApiService) {
        this.nearbyPlacesApiService = nearbyPlacesApiService;
        this.timestamp = System.currentTimeMillis();
        results = new ArrayList<>();
    }

    @Override
    public Observable<TopPlaces> getResultsFromMemory() {
        return null;
    }

    @Override
    public Observable<TopPlaces> getResultsFromNetwork() {
        return null;
    }

    @Override
    public Observable<TopPlaces> getResultData() {
        return null;
    }
}
