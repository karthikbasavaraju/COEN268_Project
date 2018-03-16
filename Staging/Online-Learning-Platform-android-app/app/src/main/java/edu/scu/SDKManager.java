package edu.scu;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.elevensight.sdk.sdk.IISightSDKManager;
import com.elevensight.sdk.ui.MainActivity;

/**
 * Created by akolte on 3/15/18.
 */

public class SDKManager extends Application {

    @Override
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
            Log.e(Constants.TAG, "Error occured in sdk maneger buils");
            e.printStackTrace();
        }
    }
}
