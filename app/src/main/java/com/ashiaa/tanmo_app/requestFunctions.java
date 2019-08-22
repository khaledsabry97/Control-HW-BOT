//package com.ashiaa.tanmo_app;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.View;
//import android.widget.Toast;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//public class requestFunctions {
//
//
//    private final Context context;
//
//    public requestFunctions(Context context) {
//        this.context = context;
//    }
//
//
//    public void onButtonFunction(View v) {
//
//        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(context);
//
//        //String url = "https://httpbin.org/get";
//        String url = "https://api.thingspeak.com/channels/338402/fields/3.json?results=3";
//
//        // Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //Toast message
//                        Toast.makeText(context, "تم فتح الجهاز",
//                                Toast.LENGTH_LONG).show();
//                        // Display the response in debugger
//                        Log.d("tagv_response", "Response is: "+ response);
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //Toast message
//                Toast.makeText(context, "خطأ في الإتصال بالجهاز",
//                        Toast.LENGTH_LONG).show();
//                //debugger message
//                Log.d("tagv_onErrorResponse", "That didn't work!");
//            }
//        });
//
//        // Add the request to the RequestQueue.
//        queue.add(stringRequest);
//    }
//
//
//    public void offButtonFunction(View v) {
//
//        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(context);
//
//        //String url = "https://httpbin.org/get";
//        String url = "https://api.thingspeak.com/channels/338402/fields/4.json?results=3";
//
//        // Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //Toast message
//                        Toast.makeText(context, "تم غلق الجهاز",
//                                Toast.LENGTH_LONG).show();
//                        // Display the response in debugger
//                        Log.d("tagv_response", "Response is: "+ response);
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //Toast message
//                Toast.makeText(context, "خطأ في الإتصال بالجهاز",
//                        Toast.LENGTH_LONG).show();
//                //debugger message
//                Log.d("tagv_onErrorResponse", "That didn't work!");
//            }
//        });
//
//        // Add the request to the RequestQueue.
//        queue.add(stringRequest);
//    }
//
//}
