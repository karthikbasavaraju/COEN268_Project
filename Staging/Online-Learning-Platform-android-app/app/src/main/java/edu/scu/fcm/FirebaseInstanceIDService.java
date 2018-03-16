package edu.scu.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by akolte on 3/16/18.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onTokenRefresh() {

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }
}
