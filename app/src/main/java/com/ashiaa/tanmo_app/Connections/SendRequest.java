/**
 * @file SendRequest.java
 *
 * @brief basic http request file to handle http connection.
 */


//folder package contain SendRequest.
package com.ashiaa.tanmo_app.Connections;


// Interface to global information about an application environment. This is an abstract class
// whose implementation is provided by the Android system. It allows access to
// application-specific resources and classes, as well as up-calls for application-level
// operations such as launching activities, broadcasting and receiving intents, etc.
import android.content.Context;


//  An Intent is a messaging object you can use to request an action from another app component.
//  An intent is an abstract description of an operation to be performed. An Intent object carries
//  information that the Android system uses to determine which componentto start (such as the exact
//  component name or component category that should receive the intent),plus information that the
//  recipient component uses in order to properly perform the action (such as the action to take and
//  the data to act upon).

import android.content.Intent;


// API for sending log output.
import android.util.Log;

// This class represents the basic building block for user interface components.
import android.view.View;

// A toast is a view containing a quick little message for the user.
import android.widget.Toast;



// Volley is an HTTP library that makes networking for Android apps easier and most importantly,faster.
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


// static values file.
import com.ashiaa.tanmo_app.Model.Constants;


import com.ashiaa.tanmo_app.R;

import org.json.JSONException;

// A modifiable set of name/value mappings.
import org.json.JSONObject;



/**
 * @class SendRequest
 *
 * @brief class to handle http request and response.
 *
 * @details this is the interface of the app with the iot device you can send and receive from the response
 * from it.
 */
public class SendRequest {

    private final Context context;  //define variable "context" of type Context.

    Intent loadingIntent;


    /**
     * @function public SendRequest(Context context)
     *
     * @brief class constructor.
     *
     * @param context : pass context object to SendRequest class .
     */
    public SendRequest(Context context) {
        this.context = context;
    }


    /**
     * @function selectState(JSONObject sending, JSONObject response)
     *
     * @brief here you can control what the response do when it gets back.
     *
     * @param sending  : JSONObject to send.
     * @param response : JSONObject to receive.
     */
    private void selectState(JSONObject sending, JSONObject response)
    {                                                               //selectState function start.
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
    }                                                           //selectState function end.


    /**
     * @brief function to handle http request by sending JSON object.
     *
     * @param jsonObject
     * @param url
     */
    public void send(final JSONObject jsonObject, String url) {

        //set up a RequestQueue using default values, and starts the queue.

        RequestQueue queue = Volley.newRequestQueue(context); //receive class context attribute.

        sendLoadingVisible(true);

        //create request object called "jsonObjReq" to request a JSON object response from the provided URL.
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(   // JsonObjectRequest object head start.
                Request.Method.POST,                            // 1st parameter:method to apply on http request.
                url,                                            // 2nd parameter:requested URL.
                new Response.Listener<JSONObject>()            // 4th parameter:Response.Listener object.

                {   // Response.Listener object body start.

                    // Volley delivers parsed responses on the main thread.
                    @Override
                    public void onResponse(JSONObject response)   // define response prameter of type JSONObject.
                    {   // onResponse body start.

                        selectState(jsonObject, response);

                        // Display the response in debugger.
                        Log.d("[Received Successfully]", response.toString());
                        //Toast message
                        // Display the response in debugger
                        sendLoadingVisible(false);
                    }   // onResponse body end.

                },  // Response.Listener object body start.

                new Response.ErrorListener()    // 5th parameter:Response.ErrorListener object.

                {
                    // Response.ErrorListener object body start.


                    @Override
                    public void onErrorResponse(VolleyError error)  // define error prameter of type VolleyError.
                    {   // onErrorResponse function  body start.

                        VolleyLog.d("[Not Received]", "Error: " + error.getMessage());
                        // Toast message.
                        Toast.makeText(context, "خطأ في الإتصال بالجهاز",
                                Toast.LENGTH_LONG).show();
                        // debugger message.
                        Log.d("tagv_onErrorResponse", "That didn't work!");
                        sendLoadingVisible(false);
                    }   // onErrorResponse function  body end.

                }   // Response.ErrorListener object body end.

        );  // JsonObjectRequest object head end.

        // Add the request object "jsonObjReq" to the RequestQueue.
        queue.add(jsonObjReq);

    }


    /**
     *
     * @param state
     */
    private void onButton(boolean state) {
        Constants.onButtonState = state;
        Intent intent = new Intent(context.getString(R.string.ButtonStateBrodReceiver));
        intent.putExtra("onButtonState", Constants.onButtonState);
        context.sendBroadcast(intent);
    }


    /**
     *
     * @param visible
     */
    private void sendLoadingVisible(boolean visible) {
        loadingIntent = new Intent("loading_bar_receiver");
        loadingIntent.putExtra("loading_bar", visible);
        context.sendBroadcast(loadingIntent);
    }


}

