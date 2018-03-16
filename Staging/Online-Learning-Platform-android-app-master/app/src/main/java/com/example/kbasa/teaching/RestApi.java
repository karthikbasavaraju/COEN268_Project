package com.example.kbasa.teaching;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by akolte on 3/16/18.
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
