package com.replace.pickupfinder.data.network;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.location.places.Place;
import com.patloew.rxlocation.Geocoding;
import com.patloew.rxlocation.RxLocation;
import com.replace.pickupfinder.Bootstrapper;
import com.replace.pickupfinder.di.ApplicationContext;
import com.replace.pickupfinder.ui.event.NewEventActivity;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.replace.pickupfinder.data.network.model.LoginRequest;
import com.replace.pickupfinder.data.network.model.LoginResponse;
import com.replace.pickupfinder.data.network.model.LogoutResponse;
import com.replace.pickupfinder.data.network.model.OpenSourceResponse;

import java.util.List;
import java.util.Locale;

import Interfaces.IEventService;
import Models.Event;
import Repositories.EventRepository;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    Retrofit retrofit;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public io.reactivex.Observable<LoginResponse> doGoogleLoginApiCall(LoginRequest.GoogleLoginRequest
                                                                  request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GOOGLE_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    @Override
    public io.reactivex.Observable<LoginResponse> doFacebookLoginApiCall(LoginRequest.FacebookLoginRequest
                                                                    request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_FACEBOOK_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    @Override
    public io.reactivex.Observable<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest
                                                                  request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SERVER_LOGIN)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .addBodyParameter(request)
                .build()
                .getObjectObservable(LoginResponse.class);
    }

    @Override
    public io.reactivex.Observable<LogoutResponse> doLogoutApiCall() {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGOUT)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectObservable(LogoutResponse.class);
    }

    @Override
    public io.reactivex.Observable<OpenSourceResponse> getOpenSourceApiCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_OPEN_SOURCE)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getObjectObservable(OpenSourceResponse.class);
    }

    @Override
    public Observable<List<Address>> getLocationInfo(Place place, RxLocation rxLocation) {
        Geocoding geocoding = rxLocation.geocoding();
        return geocoding.fromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1).toObservable();
    }

    @Override
    public Observable<ResponseBody> createEvent(Event event) {
        return retrofit.create(IEventService.class).CreateEvent(event);
    }
}
