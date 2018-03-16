package edu.scu.fcm;

import android.util.Log;

import com.elevensight.sdk.sdk.IISightSDKManager;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by akolte on 3/16/18.
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {


        Log.e(TAG, "Response: " + remoteMessage.getData().get("message") + remoteMessage.toString());
        boolean check = IISightSDKManager.getInstance().isIISightNotification(remoteMessage,
                FirebaseMessagingService.this);

        if (check) {

            Log.e(TAG, "Check is: " + Boolean.toString(check));
            IISightSDKManager.getInstance().handleIISightNotification(remoteMessage);
        }
    }
}
