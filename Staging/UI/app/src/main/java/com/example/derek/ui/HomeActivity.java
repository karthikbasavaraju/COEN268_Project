package com.example.derek.ui;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private FragmentTransaction transaction;
    private MaterialSearchView materialSearchView;

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
            Fragment profile_fragment = new HomeFragment();

            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.Fragment,  profile_fragment);
            transaction.commit();
        }

        // Bottom Nav-Bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Change the Fragment based on which button is clicked.
                switch (item.getItemId())
                {
                    case R.id.action_home:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.Fragment,  new HomeFragment());
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                    case R.id.action_schedule:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.Fragment,  new ScheduleFragment());
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                    case R.id.action_profile:
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.Fragment,  new ProfileFragment());
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        materialSearchView.setMenuItem(item);
        return true;
    }
}
