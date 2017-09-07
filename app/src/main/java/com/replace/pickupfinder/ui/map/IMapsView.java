package com.replace.pickupfinder.ui.map;

import com.replace.pickupfinder.ui.base.MvpView;

public interface IMapsView extends MvpView {
    void setMarkers(Models.Event[] events);
}
