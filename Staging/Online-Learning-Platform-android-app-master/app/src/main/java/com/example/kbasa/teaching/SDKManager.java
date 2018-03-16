package com.example.kbasa.teaching;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.elevensight.sdk.Constants;
import com.elevensight.sdk.sdk.IISightSDKManager;

/**
 * Created by akolte on 3/16/18.
 */

public class SDKManager extends Application {
    public void onCreate() {
        super.onCreate();
        try {
            IISightSDKManager.build("rse75cohhbajx3x4tc3brhca",
                    getApplicationContext(), new IISightSDKManager.ICallback() {
                        @Override
                        public void process(Object o) {
                            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(i);
                        }
                    });
        } catch (Exception e) {
            Log.e(Constants.LOG_TAG, "Error occured in sdk maneger buils");
            e.printStackTrace();
        }
    }
}
