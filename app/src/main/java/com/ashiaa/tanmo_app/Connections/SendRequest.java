package com.ashiaa.tanmo_app.Connections;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ashiaa.tanmo_app.Model.Constants;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SendRequest {

    private final Context context;

    public SendRequest(Context context) {
        this.context = context;
    }


    public void onButton(JSONObject jsonObject) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Constants.URL, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("[Received Successfully]", response.toString());
                        //Toast message
                        Toast.makeText(context, "تم فتح الجهاز",
                                Toast.LENGTH_LONG).show();
                        // Display the response in debugger
                        Log.d("tagv_response", "Response is: " + response);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("[Not Received]", "Error: " + error.getMessage());
                //Toast message
                Toast.makeText(context, "خطأ في الإتصال بالجهاز",
                        Toast.LENGTH_LONG).show();
                //debugger message
                Log.d("tagv_onErrorResponse", "That didn't work!");
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }


        };

        // Add the request to the RequestQueue.
        queue.add(jsonObjReq);

    }

    @Deprecated
    public void onButtonFunction() {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        //String url = "https://httpbin.org/get";
        String url = "https://api.thingspeak.com/channels/338402/fields/3.json?results=3";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast message
                        Toast.makeText(context, "تم فتح الجهاز",
                                Toast.LENGTH_LONG).show();
                        // Display the response in debugger
                        Log.d("tagv_response", "Response is: " + response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast message
                Toast.makeText(context, "خطأ في الإتصال بالجهاز",
                        Toast.LENGTH_LONG).show();
                //debugger message
                Log.d("tagv_onErrorResponse", "That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Deprecated
    public void offButtonFunction(View v) {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

        //String url = "https://httpbin.org/get";
        String url = "https://api.thingspeak.com/channels/338402/fields/4.json?results=3";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast message
                        Toast.makeText(context, "تم غلق الجهاز",
                                Toast.LENGTH_LONG).show();
                        // Display the response in debugger
                        Log.d("tagv_response", "Response is: " + response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast message
                Toast.makeText(context, "خطأ في الإتصال بالجهاز",
                        Toast.LENGTH_LONG).show();
                //debugger message
                Log.d("tagv_onErrorResponse", "That didn't work!");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}


