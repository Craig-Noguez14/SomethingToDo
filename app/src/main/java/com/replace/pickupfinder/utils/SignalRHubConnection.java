package com.replace.pickupfinder.utils;
import android.content.Context;

import com.replace.pickupfinder.R;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;
import microsoft.aspnet.signalr.client.Credentials;
import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.http.Request;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;
import microsoft.aspnet.signalr.client.hubs.SubscriptionHandler1;
import microsoft.aspnet.signalr.client.hubs.SubscriptionHandler2;
import microsoft.aspnet.signalr.client.transport.ClientTransport;
import microsoft.aspnet.signalr.client.transport.ServerSentEventsTransport;

public class SignalRHubConnection {
    // HubConnection
    public static HubConnection mHubConnection;
    public static HubProxy mHubProxy;
    private static Context context;
    public static String mConnectionID;

    public SignalRHubConnection(Context context) {
        this.context = context;
    }
    //
     /*
    This function try to connect with chat hub and return connection ID.
     */
    public static void startSignalR(String hubProxy) {

        if (NetworkUtils.isNetworkConnected(context) == false) {
            //TODO: SHOW ALERT ABOUT NETWORK NOT CONNECTED
        } else {
            try {
                Platform.loadPlatformComponent(new AndroidPlatformComponent());
                /*Credentials credentials = new Credentials() {
                    @Override
                    public void prepareRequest(Request request) {
                        AppPreferences appPrefs = AppPreferences.getInstance(context);
                        request.addHeader("UserName",  appPrefs.getString(PrefConstants.USER_NAME));
                    }
                };
                mHubConnection.setCredentials(credentials);*/ //IF CONNECTION HAS CREDENTIALS
                mHubConnection = new HubConnection(context.getResources().getString(R.string.api_url));
                mHubProxy = mHubConnection.createHubProxy(hubProxy);
                ClientTransport clientTransport = new   ServerSentEventsTransport(mHubConnection.getLogger());
                SignalRFuture<Void> signalRFuture = mHubConnection.start(clientTransport);
                signalRFuture.get();
                //set connection id
                mConnectionID = mHubConnection.getConnectionId();

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
