package com.example.nearbyplaces.nearbyplaces;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NearbyPlacesPresenter implements NearbyPlacesActivityMVP.Presenter {
    private NearbyPlacesActivityMVP.View view;
    private Disposable subscription = null;
    private NearbyPlacesActivityMVP.Model model;


    public NearbyPlacesPresenter(NearbyPlacesActivityMVP.Model model) {
        this.model = model;
    }


    @Override
    public void loadData() {
        subscription = model
                .result()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ViewModel>() {
                    @Override
                    public void onComplete() {
                    }


                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(ViewModel viewModel) {

                    }

                });
    }

    @Override
    public void rxUnsubscribe() {
        if (subscription != null) {
            if (!subscription.isDisposed()) {
                subscription.dispose();
            }
        }
    }


    @Override
    public void setView(NearbyPlacesActivityMVP.View view) {
        this.view = view;
    }

}
