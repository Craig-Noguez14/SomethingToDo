package com.replace.pickupfinder.di.component;

import com.replace.pickupfinder.di.module.ApplicationModule;
import com.replace.pickupfinder.di.module.WebApiModule;
import com.replace.pickupfinder.ui.event.EventActivity;
import com.replace.pickupfinder.ui.login.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, WebApiModule.class})
public interface WebApiComponent {

    void inject(LoginActivity activity);

    void inject(EventActivity activity);
}
