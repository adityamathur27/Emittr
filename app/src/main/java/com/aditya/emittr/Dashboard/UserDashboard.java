package com.aditya.emittr.Dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.aditya.emittr.Fragment.LeaderboardFragment;
import com.aditya.emittr.Fragment.QuestionFragment;
import com.aditya.emittr.Fragment.UserprogressFragment;
import com.aditya.emittr.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                selectedFragment = new QuestionFragment();
            } else if (itemId == R.id.navigation_dashboard) {
                selectedFragment = new LeaderboardFragment();
            } else if (itemId == R.id.user) {
                selectedFragment = new UserprogressFragment();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, selectedFragment).commit();
            return true;
        });

        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }
}