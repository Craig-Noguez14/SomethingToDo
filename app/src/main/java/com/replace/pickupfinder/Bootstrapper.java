package com.replace.pickupfinder;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.replace.pickupfinder.data.DataManager;
import com.replace.pickupfinder.di.component.ApplicationComponent;
import com.replace.pickupfinder.di.component.DaggerApplicationComponent;
import com.replace.pickupfinder.di.component.WebApiComponent;
import com.replace.pickupfinder.di.module.ApplicationModule;
import com.replace.pickupfinder.di.module.WebApiModule;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Bootstrapper extends Application {
    @Inject
    DataManager mDataManager;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .webApiModule(new WebApiModule("http://192.168.0.12:8080/api/"))
                .build();


        //AppLogger.init();

        AndroidNetworking.initialize(getApplicationContext());
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }

    public ApplicationComponent getComponent() {

        return mApplicationComponent;
    }

    public ApplicationComponent getWebApiComponent() {
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
