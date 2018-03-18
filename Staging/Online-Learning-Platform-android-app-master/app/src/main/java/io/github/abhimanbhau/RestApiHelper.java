package io.github.abhimanbhau;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by akolte on 3/17/18.
 */

public class RestApiHelper {
    private static RequestQueue instance;

    public static RequestQueue getInstance(Context c) {
        if (instance == null) {
            Log.d(Constants.LOG_TAG_VISDUM, "First Instance of Volley");
            instance = Volley.newRequestQueue(c);
        }
        return instance;
    }
}
