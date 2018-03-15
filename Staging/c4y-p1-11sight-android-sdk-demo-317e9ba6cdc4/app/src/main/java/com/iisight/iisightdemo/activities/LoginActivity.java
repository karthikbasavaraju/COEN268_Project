package com.iisight.iisightdemo.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.elevensight.sdk.sdk.IISightSDKManager;
import com.iisight.iisightdemo.R;

/**
 * Created by nursultanyerezhepov on 2/23/18.
 */

public class LoginActivity extends AppCompatActivity {
    private static final String mTag = "LoginActivity";

    private ProgressDialog dialog;
    private RelativeLayout mRelLoading;

    private EditText mEmailTxt;
    private EditText mPasswordTxt;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailTxt = findViewById(R.id.input_email);
        mPasswordTxt = findViewById(R.id.input_password);
        mRelLoading = findViewById(R.id.relLoading);

        IISightSDKManager.getInstance().setLoginSuccessCallback(new IISightSDKManager.ICallback() {
            @Override
            public void process(Object o) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRelLoading.setVisibility(View.GONE);
                    }
                }, 1000);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (IISightSDKManager.getInstance().isLoggedIn(this)) {
            mRelLoading.setVisibility(View.VISIBLE);
        }
    }

    public void onSignInClicked(View view) {
        disableForm();

        dialog = ProgressDialog.show(this, getString(R.string.authenticating),
                getString(R.string.please_wait), true, false);
        String email = mEmailTxt.getText().toString();
        String password = mPasswordTxt.getText().toString();
        IISightSDKManager.getInstance().loginUser(email, password, LoginActivity.this, new IISightSDKManager.ICallback() {
            @Override
            public void process(Object o) {
                dialog.dismiss();
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, new IISightSDKManager.ICallback() {
            @Override
            public void process(Object o) {
                dialog.dismiss();
                enableForm();
            }
        });

    }

    private void disableForm() {
        mEmailTxt.setEnabled(false);
        mPasswordTxt.setEnabled(false);
    }

    private void enableForm() {
        mEmailTxt.setEnabled(true);
        mPasswordTxt.setEnabled(true);
    }
}
