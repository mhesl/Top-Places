package com.example.nearbyplaces.webservice;

import com.example.nearbyplaces.webservice.apimodel.TopPlaces;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface NearbyPlacesApiService {

    @GET("search")
    Observable<TopPlaces> getNearByPlaces();

}
