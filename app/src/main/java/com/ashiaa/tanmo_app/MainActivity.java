package com.ashiaa.tanmo_app;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.FrameMetrics;
import android.widget.FrameLayout;

import com.ashiaa.tanmo_app.Views.Home_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
FrameLayout frameLayout;
BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {        //onCreate start
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        frameLayout = findViewById(R.id.main_frame_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, Home_fragment.newInstance()).addToBackStack(null).commit();

    }                                                           //onCreate end








}       //MainActivity end
