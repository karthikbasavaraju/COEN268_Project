package com.example.derek.ui.students;

import android.content.Intent;
import android.view.View;

import com.example.derek.ui.students.CourseIntroductionActivity;

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
