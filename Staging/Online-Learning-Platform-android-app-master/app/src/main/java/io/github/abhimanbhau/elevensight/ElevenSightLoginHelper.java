package io.github.abhimanbhau.elevensight;

import android.content.Context;
import android.widget.Toast;

import com.elevensight.sdk.sdk.IISightSDKManager;

import io.github.abhimanbhau.utils.Constants;

/**
 * Created by akolte on 3/18/18.
 */

public class ElevenSightLoginHelper {
    public static void loginUser(String email, String password, final Context ctx) {
        IISightSDKManager.getInstance().loginUser(
                email, password, ctx,
                new IISightSDKManager.ICallback() {
                    @Override
                    public void process(Object o) {
                        Toast.makeText(ctx,
                                Constants.LOG_TAG_ELEVEN_SIGHT + " LOGIN_SUCCESS", Toast.LENGTH_SHORT).show();
                    }
                }, new IISightSDKManager.ICallback() {
                    @Override
                    public void process(Object o) {
                        Toast.makeText(ctx,
                                Constants.LOG_TAG_ELEVEN_SIGHT + "LOGIN_FAILED", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
