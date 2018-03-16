package com.iisight.iisightdemo;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.elevensight.sdk.sdk.IISightSDKManager;
import com.iisight.iisightdemo.activities.MainActivity;

/**
 * Created by nursultanyerezhepov on 2/23/18.
 */

public class App extends Application {
    private static final String mTag = "IISightDemoApp";

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            IISightSDKManager.build("rse75cohhbajx3x4tc3brhca",
                    getApplicationContext(), new IISightSDKManager.ICallback() {
                        @Override
                        public void process(Object o) {
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(i);
                        }
                    });
        } catch (Exception e) {
            Log.e(mTag, "Error occured in sdk maneger buils");
            e.printStackTrace();
        }
    }

}
