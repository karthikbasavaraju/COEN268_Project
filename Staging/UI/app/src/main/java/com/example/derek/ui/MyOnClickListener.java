package com.example.derek.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by derek on 2018/3/7.
 */

public class MyOnClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        // go to the course description page
        Intent intent = new Intent(v.getContext(), CourseIntroductionActivity.class);
        v.getContext().startActivity(intent);
    }
}
