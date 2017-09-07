package com.replace.pickupfinder.ui.map;

import android.os.Handler;
import android.os.Looper;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.replace.pickupfinder.data.DataManager;
import com.replace.pickupfinder.ui.base.BasePresenter;
import com.replace.pickupfinder.utils.SignalRHubConnection;
import com.replace.pickupfinder.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import Models.Event;
import io.reactivex.disposables.CompositeDisposable;
import microsoft.aspnet.signalr.client.hubs.SubscriptionHandler1;

public class MapsPresenter<V extends IMapsView> extends BasePresenter<V> implements
        IMapsPresenter<V> {

    private static final String TAG = "MapsPresenter";
    private SignalRHubConnection mSignalRHubConnection;

    @Inject
    public MapsPresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        mSignalRHubConnection = new SignalRHubConnection((MapsActivity)mvpView);

        mSignalRHubConnection.startSignalR("EventHub");
        signalRChatMessageReceived();
    }

    private void signalRChatMessageReceived() {
        mSignalRHubConnection.mHubProxy.on( "Send", new SubscriptionHandler1<Models.Event[]>()
        {
            @Override
            public void run(final Models.Event[] events) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable(){
                    @Override
                    public void run() {
                        getMvpView().setMarkers(events);
                    }
                });
            }
        }, Models.Event[].class);
    }
}
