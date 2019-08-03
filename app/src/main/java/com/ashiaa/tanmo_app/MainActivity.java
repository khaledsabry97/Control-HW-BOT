package com.ashiaa.tanmo_app;


import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {        //onCreate start
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //on button find
        final Button onButton = findViewById(R.id.button_on);
        final MediaPlayer OnSound = MediaPlayer.create(this,R.raw.on_sound );
        //off button find
        final Button offButton = findViewById(R.id.button_off);
        final MediaPlayer OffSound = MediaPlayer.create(this,R.raw.off_sound );

        //on button listener
        onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View argOn) {
                onButton.setEnabled(false);
                offButton.setEnabled(true);
                //do something

                OnSound.start();

                //Toast message
                Toast.makeText(MainActivity.this, "الرجاء الإنتظار",
                        Toast.LENGTH_LONG).show();

                requestObject.onButtonFunction(argOn);
                //debugger message
                Log.d("tagv_click Button 1","On Button clicked");
            }
        });


        //off button listener

        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View argOff) {
                offButton.setEnabled(false);
                onButton.setEnabled(true);
                //do something

                OffSound.start();

                //Toast message
                Toast.makeText(MainActivity.this, "الرجاء الإنتظار",
                        Toast.LENGTH_LONG).show();

                requestObject.offButtonFunction(argOff);

                //debugger message
                Log.d("tagv_click Button 2","Off Button clicked");
            }
        });

    }                                                           //onCreate end



    //create object of http request class
    requestFunctions requestObject=new requestFunctions(this);





}       //MainActivity end
