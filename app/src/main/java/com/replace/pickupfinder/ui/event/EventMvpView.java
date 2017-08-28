package com.replace.pickupfinder.ui.event;

import android.location.Address;

import com.replace.pickupfinder.ui.base.MvpView;

import java.util.List;

public interface EventMvpView extends MvpView {
    void updateAddress(List<android.location.Address> addresses);
}
