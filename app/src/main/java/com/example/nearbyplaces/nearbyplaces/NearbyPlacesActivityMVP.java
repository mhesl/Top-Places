package com.example.nearbyplaces.nearbyplaces;


import io.reactivex.Observable;

public interface NearbyPlacesActivityMVP {

    interface View {
        void updateData(ViewModel viewModel);

    }

    interface Presenter {


        void loadData();

        void rxUnsubscribe();

        void setView(NearbyPlacesActivityMVP.View view);


    }


    interface Model {

        Observable<ViewModel> result();

    }
}
