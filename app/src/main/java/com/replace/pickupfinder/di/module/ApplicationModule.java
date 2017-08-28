package com.replace.pickupfinder.di.module;

import android.app.Application;
import android.content.Context;

import com.patloew.rxlocation.RxLocation;
import com.replace.pickupfinder.BuildConfig;
import com.replace.pickupfinder.R;
import com.replace.pickupfinder.data.AppDataManager;
import com.replace.pickupfinder.data.DataManager;
import com.replace.pickupfinder.data.network.ApiHeader;
import com.replace.pickupfinder.data.network.ApiHelper;
import com.replace.pickupfinder.data.network.AppApiHelper;
import com.replace.pickupfinder.data.prefs.AppPreferencesHelper;
import com.replace.pickupfinder.data.prefs.PreferencesHelper;
import com.replace.pickupfinder.di.ApiInfo;
import com.replace.pickupfinder.di.ApplicationContext;
import com.replace.pickupfinder.di.PreferenceInfo;
import com.replace.pickupfinder.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Craig on 8/23/2017.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }


    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }


    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,
                preferencesHelper.getCurrentUserId(),
                preferencesHelper.getAccessToken());
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
