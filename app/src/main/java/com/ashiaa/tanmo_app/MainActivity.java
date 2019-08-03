package com.ashiaa.tanmo_app;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button onButton = findViewById(R.id.button_on);
        Button offButton = findViewById(R.id.button_off);

        onButton.setOnClickListener(this);
        offButton.setOnClickListener(this);
    }



    @Override
    public void onClick(View v)
    {



        switch (v.getId()) {

            //ON block
            //-------------------------------------------------------------------------------------
            case R.id.button_on:
                Toast.makeText(this, "Button 1 clicked", Toast.LENGTH_SHORT).show();

                //-------------------------------------------------------------------------------------


                break;

            //OFF block
            //-------------------------------------------------------------------------------------
            case R.id.button_off:
                Toast.makeText(this, "Button 2 clicked", Toast.LENGTH_SHORT).show();

                //-------------------------------------------------------------------------------------

                break;
        }
    }






}