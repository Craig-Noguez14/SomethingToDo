package com.replace.pickupfinder.ui.login;

import com.replace.pickupfinder.data.DataManager;
import com.replace.pickupfinder.ui.base.BasePresenter;
import com.replace.pickupfinder.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V>
        implements LoginMvpPresenter<V> {


    @Inject
    public LoginPresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onServerLoginClick(String email, String password) {

    }

    @Override
    public void onGoogleLoginClick() {

    }

    @Override
    public void onFacebookLoginClick() {

    }
}
