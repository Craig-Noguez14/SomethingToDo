package com.replace.pickupfinder.data.network;

import android.content.Context;

import com.google.android.gms.location.places.Place;
import com.patloew.rxlocation.RxLocation;
import com.replace.pickupfinder.data.network.model.LoginRequest;
import com.replace.pickupfinder.data.network.model.LogoutResponse;
import com.replace.pickupfinder.data.network.model.OpenSourceResponse;
import com.replace.pickupfinder.data.network.model.LoginResponse;

import java.util.List;

import Models.Event;
import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;

public interface ApiHelper {

    ApiHeader getApiHeader();

    Observable<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest request);

    Observable<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest request);

    Observable<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    Observable<LogoutResponse> doLogoutApiCall();

    Observable<OpenSourceResponse> getOpenSourceApiCall();

    Observable<List<android.location.Address>> getLocationInfo(Place place, RxLocation rxLocation);

    Observable<ResponseBody> createEvent(Event event);
}
