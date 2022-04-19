package com.kent.pam_tr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeAdminActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_admin, new HomeAdminFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.page_home: {
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.content_frame_admin, new HomeAdminFragment())
                                    .commit();
                        }break;
                        case R.id.page_point: {
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.content_frame_admin, new PointAdminFragment())
                                    .commit();
                        }break;
                        case R.id.page_profile: {
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.content_frame_admin, new ProfileAdminFragment())
                                    .commit();
                        }break;
                    }

                    return true;
                }
            };

}