package com.example.derek.ui.students;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.derek.ui.R;

public class CourseIntroductionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_introduction);

        Button btn_enroll = (Button) findViewById(R.id.btn_enroll);
        btn_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseIntroductionActivity.this, DateSelectionActivity.class);
                startActivity(intent);
            }
        });

    }

}
