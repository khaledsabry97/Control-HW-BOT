/**
 * @file BootCompletedIntentReceiver.java
 *
 * @brief file to prepare app to receive broadcast from Android system.
 *
 * @details A broadcast is a message that any app can receive. This file prepare app to
 * receive broadcast from Android system when system boot event occur.it call dailyService
 * when system boot up.
 */


//folder package contain BootCompletedIntentReceiver.
package com.ashiaa.tanmo_app.Services;


// Base class for code that receives and handles broadcast intents sent by
// Context.sendBroadcast(Intent).
import android.content.BroadcastReceiver;


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



/**
 * @class BootCompletedIntentReceiver
 *
 * @brief receive broadcast intent messgae when system boot up.
 */
public class BootCompletedIntentReceiver extends BroadcastReceiver
{                                               //class BootCompletedIntentReceiver start.


    /**
     * @function public void onReceive(Context context, Intent intent)
     *
     * @brief act when event boot up happen.
     *
     * @param context : parameter of type Context to receive global information about an application
     * environment.
     * @param intent : parameter of type Intent messaging object to request an action from another
     * app component.
     */
    @Override
    public void onReceive(Context context, Intent intent) { //function onReceive start.

        // The java string equals() method compares the two given strings based on the
        // content of the string.if {intent.getAction() match boot action
        // "android.intent.action.BOOT_COMPLETED"} then....
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) { //if start.

            // constructor "Intent(Context packageContext, Class<?> cls)" Create an intent
            // for a specific component. call DailyService class using context parameter
            // in onReceive function.
            Intent pushIntent = new Intent(context, DailyService.class);


            // "startService(Intent service)" is a method on the Context class, available
            // to all subclasses of Context. Request that a given application service be
            // started.It call an Intent that call a service class "DailyService.class"
            // here which pushIntent is an intent object that call class
            // "DailyService.class".
            context.startService(pushIntent);

            Intent periodIntent = new Intent(context, PeriodService.class);
            context.startService(periodIntent);

        } //if end.
    }                                                      //function onReceive end.

}                                              //class BootCompletedIntentReceiver end.
