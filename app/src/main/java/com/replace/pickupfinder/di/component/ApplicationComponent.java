package com.replace.pickupfinder.di.component;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

import com.replace.pickupfinder.Bootstrapper;
import com.replace.pickupfinder.data.DataManager;
import com.replace.pickupfinder.data.network.AppApiHelper;
import com.replace.pickupfinder.di.ApplicationContext;
import com.replace.pickupfinder.di.module.ApplicationModule;
import com.replace.pickupfinder.di.module.WebApiModule;

@Singleton
@Component(modules = {ApplicationModule.class, WebApiModule.class})
public interface ApplicationComponent {

    void inject(Bootstrapper app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}
