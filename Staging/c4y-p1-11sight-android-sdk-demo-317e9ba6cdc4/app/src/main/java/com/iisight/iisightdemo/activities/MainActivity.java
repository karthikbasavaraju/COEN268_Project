package com.iisight.iisightdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.elevensight.sdk.sdk.IISightSDKManager;
import com.iisight.iisightdemo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    RequestQueue MyRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyRequestQueue = Volley.newRequestQueue(this);

        email = findViewById(R.id.email);
        password = findViewById(R.id.txtPassword);

    }

    public void onSignOutClicked(View view) {
        IISightSDKManager.getInstance().logoutUser(new IISightSDKManager.ICallback() {
            @Override
            public void process(Object o) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }, new IISightSDKManager.ICallback() {
            @Override
            public void process(Object o) {
            }
        });
    }

    public void onMakeCallClicked(View view) {
        IISightSDKManager.getInstance()
                .makeCall("xldgqYMqWkMg5YMEicLG-uv4Mr5wuK5FZe79b5Uz9zE6zZETgn", getApplicationContext());
    }

    public void onCreateUserClicked(View view) throws JSONException {
        String url = "https://sdktest.11sight.com/api/v2/users.json";
        String u = email.getText().toString();
        String pwd = password.getText().toString();
        String jsonString = "{" +
                "\"tos\": \"accepted\"," +
                "\"user\" :{" +
                "\"email\":\"" + u + "\"," +
                "\"password\":\"" + pwd + "\"," +

                "\"profile_attributes\": {" +
                "\"first_name\": \"Abe\"," +
                "\"last_name\": \"Kolte\"," +
                "\"business_name\": \"SCU\"" +
                "}" +

                "}" +
                "}";
        JSONObject json = new JSONObject(jsonString);


        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(MainActivity.this,
                        "SUCESS", Toast.LENGTH_SHORT).show();
                Log.d("11SIGHTLOLBOI", response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,
                        "ERROR", Toast.LENGTH_SHORT).show();
                Log.d("11SIGHTLOLBOI", new String(error.networkResponse.data));
            }
        }) {

            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json");
                headers.put("S-Auth-Token", "rse75cohhbajx3x4tc3brhca");
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        MyRequestQueue.add(req);
    }
}