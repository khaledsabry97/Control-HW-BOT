package com.ashiaa.tanmo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {        //onCreate start
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //on button find
        final Button onButton = findViewById(R.id.button_on);
        //off button find
        final Button offButton = findViewById(R.id.button_off);

        //on button listener
        onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                onButton.setEnabled(false);
                offButton.setEnabled(true);
                //do something

                onButtonFunction(arg0);
                //debugger message
                Log.d("tagv_click Button 1","Button 1 clicked");
            }
        });


        //off button listener

        offButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                offButton.setEnabled(false);
                onButton.setEnabled(true);
                //do something
                //debugger message
                Log.d("tagv_click Button 2","Button 2 clicked");
            }
        });

    }                                                           //onCreate end




    public void onButtonFunction(View v) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://httpbin.org/json";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response in debugger
                        Log.d("tagv_response", "Response is: "+ response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //debugger message
                Log.d("tagv_onErrorResponse", "That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}       //MainActivity end
