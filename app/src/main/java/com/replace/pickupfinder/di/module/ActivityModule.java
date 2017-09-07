package com.replace.pickupfinder.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

import com.patloew.rxlocation.RxLocation;
import com.replace.pickupfinder.di.ActivityContext;
import com.replace.pickupfinder.ui.event.EventMvpPresenter;
import com.replace.pickupfinder.ui.event.EventMvpView;
import com.replace.pickupfinder.ui.event.EventPresenter;
import com.replace.pickupfinder.ui.map.IMapsPresenter;
import com.replace.pickupfinder.ui.map.IMapsView;
import com.replace.pickupfinder.ui.map.MapsPresenter;
import com.replace.pickupfinder.utils.rx.AppSchedulerProvider;
import com.replace.pickupfinder.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    RxLocation provideRxLocation() {
        return new RxLocation(mActivity);
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    EventMvpPresenter<EventMvpView> provideEventPresenter(
            EventPresenter<EventMvpView> presenter) {
        return presenter;
    }

    @Provides
    IMapsPresenter<IMapsView> provideMapsPresenter(
            MapsPresenter<IMapsView> presenter) {
        return presenter;
    }
}
