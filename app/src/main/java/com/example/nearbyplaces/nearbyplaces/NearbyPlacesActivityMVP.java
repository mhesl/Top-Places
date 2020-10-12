package com.example.nearbyplaces.nearbyplaces;


import com.example.nearbyplaces.webservice.apimodel.Venue;

import io.reactivex.Observable;

public interface NearbyPlacesActivityMVP {

    interface View {
        void updateData(Venue viewModel);

    }

    interface Presenter {


        void loadData();

        void rxUnsubscribe();

        void setView(NearbyPlacesActivityMVP.View view);


    }


    interface Model {

        Observable<Venue> result();

    }
}
