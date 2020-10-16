package com.example.nearbyplaces.webservice;

import com.example.nearbyplaces.nearbyplaces.NearByPlacesActivity;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModuleForPlaces {

    public final String BASE_URL = "https://api.foursquare.com/v2/venues/";
    public final String CLIENT_ID = "PV5SZYDISY1OXOT4RG2RMVRMK2O2KSKCS1XA040HNT0C4GSE";
    public final String CLIENT_SECRET = "2QYALZR2AGFD0CKBO150MAYHDJ0Y22YLN0RCK2APX0BNSYCP";


    @Provides
    public OkHttpClient provideClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(chain -> {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder().addQueryParameter(
                    "client_id",
                    CLIENT_ID
            ).addQueryParameter(
                    "client_secret",
                    CLIENT_SECRET
            ).addQueryParameter(
                    "v",
                    "20200101"
            ).addQueryParameter(
                    "ll",
                    NearByPlacesActivity.stringLatitude + "," + NearByPlacesActivity.stringLongitude
            ).addQueryParameter(
                    "radius",
                    "150"
            ).build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        }).build();
    }

    @Provides
    public Retrofit provideRetrofit(String baseURL, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public NearbyPlacesApiService provideApiService() {
        return provideRetrofit(BASE_URL, provideClient()).create(NearbyPlacesApiService.class);
    }
}
