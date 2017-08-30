package com.replace.pickupfinder.ui.event;

import android.location.Address;

import com.replace.pickupfinder.ui.base.MvpView;

import java.util.List;

import okhttp3.ResponseBody;

public interface EventMvpView extends MvpView {
    void onCreatedEvent(ResponseBody responseBody);
}
