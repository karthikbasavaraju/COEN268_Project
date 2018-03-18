package com.example.kbasa.teaching;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by akolte on 3/17/18.
 */

public class RestApiHelper {
    private static RequestQueue instance;

    public static RequestQueue getInstance(Context c) {
        if (instance == null)
            instance = Volley.newRequestQueue(c);
        return instance;
    }
}
