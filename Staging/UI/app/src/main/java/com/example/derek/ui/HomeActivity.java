package com.example.derek.ui;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.example.derek.ui.students.HomeFragment;
import com.example.derek.ui.students.ScheduleFragment;
import com.example.derek.ui.teachers.T_HomeFragment;
import com.example.derek.ui.teachers.T_ScheduleFragment;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class HomeActivity extends AppCompatActivity {

    private FragmentTransaction transaction;
    private MaterialSearchView materialSearchView;

    String role = "teacher";
//    String role = "student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Search Bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        materialSearchView = (MaterialSearchView)findViewById(R.id.search_view);

        // Set the default Fragment to HomeFragment
        if(savedInstanceState == null) {
            transaction = getSupportFragmentManager().beginTransaction();
            if(role.equals("student")) {
                transaction.add(R.id.Fragment,  new HomeFragment());
            }else if(role.equals("teacher")) {
                transaction.add(R.id.Fragment,  new T_HomeFragment());
            }
            transaction.commit();
        }

        // Bottom Nav-Bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        if(role.equals("student")) {
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    // Change the Fragment based on which button is clicked.
                    switch (item.getItemId()) {
                        case R.id.action_home:
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.Fragment, new HomeFragment());
                            transaction.addToBackStack(null);
                            transaction.commit();
                            break;
                        case R.id.action_schedule:
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.Fragment, new ScheduleFragment());
                            transaction.addToBackStack(null);
                            transaction.commit();
                            break;
                        case R.id.action_profile:
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.Fragment, new ProfileFragment());
                            transaction.addToBackStack(null);
                            transaction.commit();
                            break;
                    }
                    return true;
                }
            });
        }else if(role.equals("teacher")) {
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    // Change the Fragment based on which button is clicked.
                    switch (item.getItemId()) {
                        case R.id.action_home:
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.Fragment, new T_HomeFragment());
                            transaction.addToBackStack(null);
                            transaction.commit();
                            break;
                        case R.id.action_schedule:
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.Fragment, new T_ScheduleFragment());
                            transaction.addToBackStack(null);
                            transaction.commit();
                            break;
                        case R.id.action_profile:
                            transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.Fragment, new ProfileFragment());
                            transaction.addToBackStack(null);
                            transaction.commit();
                            break;
                    }
                    return true;
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        materialSearchView.setMenuItem(item);
        return true;
    }
}
