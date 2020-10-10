package com.example.nearbyplaces.webservice;

import com.example.nearbyplaces.webservice.apimodel.NearbyPlaces;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NearbyPlacesApiService {

    @GET("")
    Observable<NearbyPlaces> getNearByPlaces();

}
