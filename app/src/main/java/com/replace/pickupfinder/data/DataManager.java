package com.replace.pickupfinder.data;

import com.replace.pickupfinder.data.network.ApiHelper;
import com.replace.pickupfinder.data.prefs.PreferencesHelper;

/**
 * Created by Craig on 8/23/2017.
 */

public interface DataManager extends PreferencesHelper, ApiHelper {
    void updateApiHeader(Long userId, String accessToken);

    void setUserAsLoggedOut();

    void updateUserInfo(
            String accessToken,
            Long userId,
            LoggedInMode loggedInMode,
            String userName,
            String email,
            String profilePicPath);

    enum LoggedInMode {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
