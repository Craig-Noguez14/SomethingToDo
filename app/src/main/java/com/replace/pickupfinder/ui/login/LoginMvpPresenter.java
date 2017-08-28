package com.replace.pickupfinder.ui.login;

import com.replace.pickupfinder.di.PerActivity;
import com.replace.pickupfinder.ui.base.MvpPresenter;

@PerActivity
public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void onServerLoginClick(String email, String password);

    void onGoogleLoginClick();

    void onFacebookLoginClick();

}
