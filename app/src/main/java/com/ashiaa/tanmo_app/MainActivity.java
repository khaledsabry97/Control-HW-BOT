package com.ashiaa.tanmo_app;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ashiaa.tanmo_app.Controllers.SendController;
import com.ashiaa.tanmo_app.Views.AboutFragment;
import com.ashiaa.tanmo_app.Views.ConfigurationFragment;
import com.ashiaa.tanmo_app.Views.Homefragment;
import com.ashiaa.tanmo_app.Views.ScheduleFragment;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    TextView header;
    View head;
    BottomNavigationView bottomNavigationView;
    Homefragment homeFragment;
    ScheduleFragment scheduleFragment;
    ConfigurationFragment configurationFragment;
    AboutFragment aboutFragment;
    SpinKitView loadingBar;
    BroadcastReceiver loadingBarReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {        //onCreate start
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        frameLayout = findViewById(R.id.main_frame_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        header = findViewById(R.id.header_text_id);
        head = findViewById(R.id.header_id);
        loadingBar = findViewById(R.id.spin_kit);
        homeFragment = new Homefragment();
        scheduleFragment = new ScheduleFragment();
        configurationFragment = new ConfigurationFragment();
        aboutFragment = new AboutFragment();


        navigate();
        setupLoadingBar();
    }

    private void setupLoadingBar() {
    loadingBarReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean visible= intent.getBooleanExtra("loading_bar",false);
            if (visible)
            {
                loadingBar.setVisibility(View.VISIBLE);

            }
            else
            {
                loadingBar.setVisibility(View.GONE);
            }
        }
    };
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(loadingBarReceiver,new IntentFilter("loading_bar_receiver"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(loadingBarReceiver);
    }

    /**
     * the navigation to all the fragments in the app
     */
    private void navigate() {

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                Fragment fragment = null;
                head.setVisibility(View.VISIBLE);
                switch (id) {
                    case R.id.menu_main:
                        if (homeFragment == null)
                            homeFragment = new Homefragment();
                        fragment = homeFragment;
                        header.setText("القائمة الرئيسية");
                        break;
                    case R.id.menu_schedule:
                        if (scheduleFragment == null)
                            scheduleFragment = new ScheduleFragment();
                        fragment = scheduleFragment;
                        header.setText("ضبط بمواعيد يومية");
                        break;
                    case R.id.menu_settings:
                        if (configurationFragment == null)
                            configurationFragment = new ConfigurationFragment();
                        fragment = configurationFragment;
                        header.setText("الإعدادات");
                        break;
                    case R.id.menu_about:
                        if (aboutFragment == null)
                            aboutFragment = new AboutFragment();
                        fragment = aboutFragment;
                        header.setText("من نحن");
                        break;


                    default:
                        return false;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                return true;
            }
        });


        bottomNavigationView.setSelectedItemId(R.id.menu_main);
        SendController sendController= new SendController(getApplicationContext());
        sendController.sendCheckStatus();

    }

    //onCreate end


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (bottomNavigationView.getSelectedItemId() == R.id.menu_main){
            finish();
            System.exit(0);
        }
        else
        {
            bottomNavigationView.setSelectedItemId(R.id.menu_main);
        }

    }
}       //MainActivity end
