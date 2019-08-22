package com.ashiaa.tanmo_app;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.FrameMetrics;
import android.widget.FrameLayout;
import android.widget.Toast;

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

        bottomNavigationView.getSelectedItemId()


        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, Home_fragment.newInstance()).addToBackStack(null).commit();
        navigate();
    }

    private void navigate() {
        int id = bottomNavigationView.getSelectedItemId();

        switch (id)
        {
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;


            default:

        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState); // the UI component values are saved here.
        outState.putDouble("VALUE", liter);
        Toast.makeText(this, "Activity state saved", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    //onCreate end








}       //MainActivity end
