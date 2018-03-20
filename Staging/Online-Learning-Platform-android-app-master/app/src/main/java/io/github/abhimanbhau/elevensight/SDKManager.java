package io.github.abhimanbhau.elevensight;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.elevensight.sdk.sdk.IISightSDKManager;
import com.example.kbasa.teaching.LoginActivity;

import io.github.abhimanbhau.utils.Constants;

/**
 * Created by akolte on 3/17/18.
 */

public class SDKManager extends Application {
    public void onCreate() {
        super.onCreate();
        try {
            Log.d(Constants.LOG_TAG_ELEVEN_SIGHT,
                    "INIT_SDK");
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
            Log.e(Constants.LOG_TAG_ELEVEN_SIGHT, "FAILED_BUILD_SDK");
            e.printStackTrace();
        }
    }
}
