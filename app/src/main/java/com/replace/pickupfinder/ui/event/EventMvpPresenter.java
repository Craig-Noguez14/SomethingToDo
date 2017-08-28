package com.replace.pickupfinder.ui.event;

import android.content.Context;

import com.google.android.gms.location.places.Place;
import com.patloew.rxlocation.RxLocation;
import com.replace.pickupfinder.ui.base.MvpPresenter;
import com.replace.pickupfinder.ui.base.MvpView;

import java.util.Date;

import Models.Address;
import Models.Event;
import Models.SubCategory;

public interface EventMvpPresenter <V extends MvpView> extends MvpPresenter<V> {

    void onCreateEventClick(Event event);

    void onLookupAddress(Place place, RxLocation rxLocation);
}
