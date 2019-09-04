package com.ashiaa.tanmo_app.Connections;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ashiaa.tanmo_app.Model.Constants;
import com.ashiaa.tanmo_app.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * this is the interface of the app with the iot device you can send and receive from the response from it
 */
public class SendRequest {

    private final Context context;
    Intent loadingIntent;

    public SendRequest(Context context) {
        this.context = context;
    }

    /**
     * here you can control what the response do when it gets back
     *
     * @param response the json response from the iot device
     */
    private void selectState(JSONObject sending, JSONObject response) {
        try {
            //type to tell what are the request is sent from the device
            String type = sending.getString("type");
            if (type.equals("send_on") || type.equals("send_off") || type.equals("check_state")) {
                Log.d("type", type);
                if (response.getBoolean("is_open") == true) {
                    Log.d("On Button", "disabled");
                    onButton(false);
                } else {
                    Log.d("On Button", "enabled");
                    onButton(true);
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void send(final JSONObject jsonObject, String url) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);

sendLoadingVisible(true);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        selectState(jsonObject, response);
                        Log.d("[Received Successfully]", response.toString());
                        //Toast message
                        // Display the response in debugger
sendLoadingVisible(false);
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
                sendLoadingVisible(false);
            }
        }) {


        };

        // Add the request to the RequestQueue.
        queue.add(jsonObjReq);

    }


    private void onButton(boolean state) {
        Constants.onButtonState = state;
        Intent intent = new Intent(context.getString(R.string.ButtonStateBrodReceiver));
        intent.putExtra("onButtonState", Constants.onButtonState);
        context.sendBroadcast(intent);
    }

    private void sendLoadingVisible(boolean visible)
    {
        loadingIntent = new Intent("loading_bar_receiver");
        loadingIntent.putExtra("loading_bar",visible);
        context.sendBroadcast(loadingIntent);
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


