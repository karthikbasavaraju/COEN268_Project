package com.iisight.iisightdemo.fcm;

import android.util.Log;

import com.elevensight.sdk.sdk.IISightSDKManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by nursultanyerezhepov on 2/27/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {


        Log.e(TAG, "Response: " + remoteMessage.getData().get("message") + remoteMessage.toString());
        boolean check = IISightSDKManager.getInstance().isIISightNotification(remoteMessage, MyFirebaseMessagingService.this);

        if (check) {

            Log.e(TAG, "Check is: " + Boolean.toString(check));
            IISightSDKManager.getInstance().handleIISightNotification(remoteMessage);
        }
    }
}
