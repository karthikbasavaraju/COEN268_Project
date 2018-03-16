package edu.scu;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.elevensight.sdk.ui.MainActivity;

/**
 * Created by akolte on 3/15/18.
 */

public class RestApi {
    private static RequestQueue instance;
    public static RequestQueue getInstance(Context c)
    {
        if(instance == null)
            instance = Volley.newRequestQueue(c);
        return instance;
    }
}
