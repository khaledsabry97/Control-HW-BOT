package com.ashiaa.tanmo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void onButton(View v ) {

        TextView textview = (TextView) findViewById(R.id.textView1);
        textview.setVisibility(View.VISIBLE);

    }


    public void offButton(View v ) {

        TextView textview = (TextView) findViewById(R.id.textView1);
        textview.setVisibility(View.INVISIBLE);

    }


}
