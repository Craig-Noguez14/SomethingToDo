package com.replace.pickupfinder.ui.event;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.androidnetworking.error.ANError;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.places.Place;
import com.patloew.rxlocation.RxLocation;
import com.replace.pickupfinder.R;
import com.replace.pickupfinder.data.DataManager;
import com.replace.pickupfinder.di.ApplicationContext;
import com.replace.pickupfinder.ui.base.BasePresenter;
import com.replace.pickupfinder.ui.base.MvpView;
import com.replace.pickupfinder.ui.map.MapsActivity;
import com.replace.pickupfinder.utils.SignalRHubConnection;
import com.replace.pickupfinder.utils.rx.SchedulerProvider;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import Models.Address;
import Models.Event;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class EventPresenter<V extends EventMvpView> extends BasePresenter<V> implements
        EventMvpPresenter<V> {

    private static final String TAG = "EventPresenter";

    @Inject
    public EventPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);

    }

    @Override
    public void onCreateEventClick(Event event) {
        /*if (TextUtils.isEmpty(event.Description)) {
            getMvpView().onError(R.string.event_description_required);
            return;
        }
        if (event.StartOn == null) {
            getMvpView().onError(R.string.event_start_required);
            return;
        }
        if (event.ExpiresOn == null) {
            getMvpView().onError(R.string.event_end_required);
            return;
        }
        if (event.Location == null) {
            getMvpView().onError(R.string.event_location_required);
            return;
        }*/

        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
                .createEvent(event)
                .subscribeOn(Schedulers.io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody response) throws Exception {
                        getMvpView().onCreatedEvent(response);

                        getMvpView().hideLoading();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        if (!isViewAttached()) {
                            return;
                        }

                        getMvpView().hideLoading();

                        // handle the login error here
                        if (throwable instanceof ANError) {
                            ANError anError = (ANError) throwable;
                            handleApiError(anError);
                        }
                    }
                }));
    }

    @Override
    public void onLookupAddress(Place place, RxLocation rxLocation) {
        getMvpView().showLoading();

    }
}
