package com.replace.pickupfinder.di.component;

import com.replace.pickupfinder.ui.event.EventActivity;
import com.replace.pickupfinder.ui.event.NewEventActivity;
import com.replace.pickupfinder.ui.login.LoginActivity;
import com.replace.pickupfinder.di.module.ActivityModule;

import dagger.Component;
import com.replace.pickupfinder.di.PerActivity;
import com.replace.pickupfinder.ui.map.MapsActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MapsActivity activity);

    void inject(NewEventActivity activity);

    void inject(LoginActivity activity);
}
