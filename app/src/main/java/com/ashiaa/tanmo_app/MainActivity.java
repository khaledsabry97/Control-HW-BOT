/**
 * @file MainActivity.java
 *
 * @brief app main activity file.
 */


// folder package contain MainActivity.
package com.ashiaa.tanmo_app;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;



// Bundles are generally used for passing data between various Android activities. It depends on
// you what type of values you want to pass, but bundles can hold all types of values and pass them
// to the new activity.
import android.os.Bundle;


import android.view.MenuItem;
import android.view.View;

// FrameLayout is designed to block out an area on the screen to display a single item.
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;


// Base class for activities that use the support library for multiple API versions and action
// bar features.
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.widget.TextViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ashiaa.tanmo_app.Controllers.SendController;
import com.ashiaa.tanmo_app.Views.AboutFragment;
import com.ashiaa.tanmo_app.Views.ConfigurationFragment;

// local home fragment.
import com.ashiaa.tanmo_app.Views.Homefragment;
import com.ashiaa.tanmo_app.Views.ScheduleFragment;
import com.github.ybq.android.spinkit.SpinKitView;



// Represents a standard bottom navigation bar for application.Bottom navigation bars allow
// movement between primary destinations in an app.
import com.google.android.material.bottomnavigation.BottomNavigationView;



/**
 * @class MainActivity
 *
 * @brief class for main activity.
 */
public class MainActivity extends AppCompatActivity {                        //MainActivity{} start.

    // define general class attributes.

    // define frameLayout attribute which draw element block.
    FrameLayout frameLayout;
    TextView header;
    View head;

    // define BottomNavigationView attribute for bottom menu.
    BottomNavigationView bottomNavigationView;

    Homefragment homeFragment;
    ScheduleFragment scheduleFragment;
    ConfigurationFragment configurationFragment;
    AboutFragment aboutFragment;
    SpinKitView loadingBar;
    BroadcastReceiver loadingBarReceiver;


    /**
     * @function protected void onCreate(Bundle savedInstanceState)
     *
     *
     * @brief Called when the activity is starting.
     *
     * @details This is where most initialization should go: calling setContentView(int) to inflate
     * the activity's UI, using findViewById(int) to programmatically interact with widgets in the
     * UI, calling managedQuery(android.net.Uri, java.lang.String[],java.lang.String, java.lang.
     * String[], java.lang.String) to retrieve cursors for data being displayed, etc.
     *
     * @param savedInstanceState : Bundle If the activity is being re-initialized after previously
     * being shut down then this Bundle contains the data it most recently supplied in
     * onSaveInstanceState(Bundle). Note: Otherwise it is null. This value may be null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {        //onCreate() start

        // call the default code of onCreate() inside new onCreate().
        super.onCreate(savedInstanceState);



        // prototype : public void setContentView (View view)
        // Set the activity content to an explicit view. This view is placed directly into the
        // activity's view hierarchy. It can itself be a complex view hierarchy. When calling
        // this method, the layout parameters of the specified view are ignored.
        // @param view : View The desired content to display.
        // activity_main_layout : basic xml file layout with bottom navigation and a rectangular.
        setContentView(R.layout.activity_main_layout);

        // assign frameLayout to outer rectangular without bottom menu.
        frameLayout = findViewById(R.id.main_frame_layout);

        // assign bottomNavigationView to bottom menu
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




                // getSupportFragmentManager():
                // prototype : FragmentManager getSupportFragmentManager ().
                // Return the FragmentManager for interacting with fragments associated with this activity.
                // @return : FragmentManager object.
                //
                // beginTransaction():
                // prototype : public abstract FragmentTransaction beginTransaction ().
                // Start a series of edit operations on the Fragments associated with this FragmentManager.
                // @return : FragmentTransaction object.
                //
                // replace():
                // prototype : public abstract FragmentTransaction replace (int containerViewId,Fragment fragment).
                // Replace whatever is in the fragment_container view with this fragment.
                // @return : FragmentTransaction object.
                //
                // Home_fragment.newInstance() : create new instance of Home_fragment.
                //
                // addToBackStack():
                // prototype : public abstract FragmentTransaction addToBackStack (String name).
                // Add this transaction to the back stack. This means that the transaction will be
                // remembered after it is committed, and will reverse its operation when later popped off
                // the stack.
                // @param name : String: An optional name for this back stack state, or null. This value
                // may be null.
                // @return : FragmentTransaction object.
                //
                // commit():
                // prototype : public abstract int commit ().
                // Schedules a commit of this transaction. The commit does not happen immediately; it will
                // be scheduled as work on the main thread to be done the next time that thread is ready.
                // @return : int Returns the identifier of this transaction's back stack entry, if
                // addToBackStack(java.lang.String) had been called. Otherwise, returns a negative number.
                //
                // we used here chaining style.
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();
                return true;
            }
        });


        bottomNavigationView.setSelectedItemId(R.id.menu_main);
        SendController sendController= new SendController(getApplicationContext());
        sendController.sendCheckStatus();

    }

    //onCreate() end


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
}       //MainActivity{} end.
