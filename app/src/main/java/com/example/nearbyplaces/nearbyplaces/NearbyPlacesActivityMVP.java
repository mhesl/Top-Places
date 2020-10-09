package com.example.nearbyplaces.nearbyplaces;


import io.reactivex.Observable;

public interface NearbyPlacesActivityMVP {

    interface View {
        void UpdateData(ViewModel viewModel);

    }

    interface Presenter {

        void setView(NearbyPlacesActivityMVP.View view);

        void loadData();

        void rxUnsubscribe();

    }


    interface Model {

        Observable<ViewModel> result();

    }
}
