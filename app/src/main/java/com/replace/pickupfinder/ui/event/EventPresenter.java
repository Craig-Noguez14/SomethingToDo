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

public class EventPresenter<V extends EventMvpView> extends BasePresenter<V> implements
        EventMvpPresenter<V> {

    private static final String TAG = "FeedPresenter";

    @Inject
    public EventPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onCreateEventClick(Event event) {
        if (TextUtils.isEmpty(event.Description)) {
            getMvpView().onError(R.string.error_field_required);
            return;
        }
        if (event.StartOn == null) {
            getMvpView().onError(R.string.error_field_required);
            return;
        }
        if (event.ExpiresOn == null) {
            getMvpView().onError(R.string.error_field_required);
            return;
        }

        getMvpView().showLoading();


    }

    @Override
    public void onLookupAddress(Place place, RxLocation rxLocation) {
        getMvpView().showLoading();

    }
}
