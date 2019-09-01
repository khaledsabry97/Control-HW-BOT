package com.ashiaa.tanmo_app.Connections;

import android.content.Context;
import android.content.Intent;
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
import com.ashiaa.tanmo_app.Controllers.SendController;
import com.ashiaa.tanmo_app.Model.Constants;
import com.ashiaa.tanmo_app.R;
import com.ashiaa.tanmo_app.Views.Homefragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * this is the interface of the app with the iot device you can send and receive from the response from it
 */
public class SendRequest {

    private final Context context;

    public SendRequest(Context context) {
        this.context = context;
    }


    public void send(final JSONObject jsonObject) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(context);
final Constants constants = new Constants(context);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                constants.getUrl(), jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (jsonObject.getString(SendController.switchs) == "on")
                            {
                                Constants.onButtonState = false;
                                Intent intent = new Intent(context.getString(R.string.ButtonStateBrodReceiver));
                                intent.putExtra("onButtonState",Constants.onButtonState);
                            context.sendBroadcast(intent);}
                            else
                            {
                                Constants.onButtonState = true;
                                Intent intent = new Intent(context.getString(R.string.ButtonStateBrodReceiver));
                                intent.putExtra("onButtonState",Constants.onButtonState);
                                context.sendBroadcast(intent);
                            }

                            Toast.makeText(context, response.getString("msg"),
                                    Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("[Received Successfully]", response.toString());
                        //Toast message
                        // Display the response in debugger

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


