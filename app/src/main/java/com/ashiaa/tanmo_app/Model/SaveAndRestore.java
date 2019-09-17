/**
 * @file SaveAndRestore.java
 *
 * @brief file for class to save general app data.
 */

//folder package contain SaveAndRestore.
package com.ashiaa.tanmo_app.Model;

// An activity is a single, focused thing that the user can do.
// An Activity represents a single screen in an app.
import android.app.Activity;


// Interface to global information about an application environment. This is an abstract class
// whose implementation is provided by the Android system. It allows access to
// application-specific resources and classes, as well as up-calls for application-level
// operations such as launching activities, broadcasting and receiving intents, etc.
import android.content.Context;

// Interface for accessing and modifying preference data returned by Context#getSharedPreferences.
import android.content.SharedPreferences;

// local app resources folder.
import com.ashiaa.tanmo_app.R;


import com.ashiaa.tanmo_app.Services.BootCompletedIntentReceiver;

// Java TreeMap class is a red-black tree based implementation.
//It provides an efficient means of storing key-value pairs in sorted order.
import java.util.TreeMap;



/**
 * @class SaveAndRestore
 *
 * @brief responsible for all the save and restore from disk.
 */
public class SaveAndRestore {   //class SaveAndRestore start.

    private Context activity;   //define attribute "activity" of type Context.

    private  SharedPreferences sharedPref;  //define attribute "sharedPref" of type SharedPreferences.


    /**
     * @function public SaveAndRestore(Context activity)
     *
     * @brief class constructor that receive activity parameter of type Context.
     *
     * @param activity : parameter of type Context.
     */
    public SaveAndRestore(Context activity)
    {
        //assign class attribute "activity" to activity parameter of constructor function.
        this.activity = activity;

        // - Add a Variable declaration and Create Preference File "hello".
        // - Context.MODE_PRIVATE is an int constant with value zero.
        // - prototype:getSharedPreferences(String name, int mode);
        // - prtotype:activity.getSharedPreferences(PREFERENCES_FILE_NAME,0);
        sharedPref = activity.getSharedPreferences("hello3",Context.MODE_PRIVATE);
    }



    /**
     * @function public boolean saveCredentials(String ip,String password,String deviceId)
     *
     * @brief save all the credentials.
     *
     * @details to store settings info from user input.
     *
     * @param ip //ip of the device
     * @param password password of the device
     * @param deviceId  device id
     *
     * @return true if successfull get data from input and false if fail.
     */
    public boolean saveCredentials(String ip,String password,String deviceId)
    {                                                       // saveCredentials function start.
        //use try in case of problems with input.
        try {

            // - Open Editor.
            // - int x = y;
            // - prototype : data_type new_object_name = old_object_name;
            SharedPreferences.Editor editor = sharedPref.edit();

            // Add key-value pairs for settings.
            editor.putString(activity.getString(R.string.ip), ip);
            editor.putString(activity.getString(R.string.password), password);
            editor.putString(activity.getString(R.string.device_id), deviceId);

            // All changes you make in an editor are batched, and not copied back to the original
            // SharedPreferences until you call commit() or apply().
            editor.commit();
        }
        catch (Exception e) {
            return false;
        }
        return true;    // if success in getting data.
    }                                                   // saveCredentials function end.




    /**
     * @function public boolean saveDailyClock(int hour,int min)
     *
     * @brief use it to save the clock where every selected days you chose will start on.
     *
     * @param hour
     * @param min
     * @return
     */
    public boolean saveDailyClock(int hour,int min)
    {
        try {

            // - Open Editor.
            // - int x = y;
            // - prototype : data_type new_object_name = old_object_name;
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(activity.getString(R.string.start_daily_hour), hour);
            editor.putInt(activity.getString(R.string.start_daily_min), min);
            editor.commit();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }




    /**
     * to save the period of the time to set it off after every selected day
     * @param hour
     * @param min
     * @return
     */
    public boolean saveDailyPeriod(int hour,int min)
    {
        try {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(activity.getString(R.string.period_daily_hour), hour);
            editor.putInt(activity.getString(R.string.period_daily_min), min);
            editor.commit();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }




    /**
     * what are the days the device will work in it
     * @param day
     * @param state
     * @return
     */
    public boolean saveDayState(String day,boolean state)
    {
        try {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(day, state);
            editor.commit();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }


    public int getDailyClockHour()
    {
        return sharedPref.getInt(activity.getString(R.string.start_daily_hour), 0);
    }


    public int getDailyClockMin()
    {
        return sharedPref.getInt(activity.getString(R.string.start_daily_min), 0);
    }


    public int getDailyPeriodHour()
    {
        return sharedPref.getInt(activity.getString(R.string.period_daily_hour), 0);
    }


    public int getDailyPeriodMin()
    {
        return sharedPref.getInt(activity.getString(R.string.period_daily_min), 0);
    }


    public boolean getDayState(String day)
    {
        return sharedPref.getBoolean(day, false);
    }




    /**
     * @function public String getUserName()
     *
     * @brief get user name value from user interface and put it in SharedPreferences file.
     *
     * @return the username value.
     */
    public String getUserName()
    {
        String userName = "";   //variable to store user name value.

        //use try in case of problems with reaching value.
        try {

            //put user name value into SharedPreferences file.
            userName = sharedPref.getString(activity.getString(R.string.user_name), "");

        }
        catch (Exception e) {
        }
        return userName;
    }



    /**
     * @function public String getPassWord()
     *
     * @brief get password value from user interface and put it in SharedPreferences file.
     *
     * @return the password value.
     */
    public String getPassWord()
    {
        String password = "";   //variable to store password value.
        try {

            //put password value into SharedPreferences file.
            password = sharedPref.getString(activity.getString(R.string.password), "");

        }
        catch (Exception e) {
        }
        return password;
    }




    /**
     * @function public String getDeviceId()
     *
     * @brief get device_id value from user interface and put it in SharedPreferences file.
     *
     * @return the device id value.
     */
    public String getDeviceId()
    {
        String deviceId = "";   //variable to store device id value.

        //use try in case of problems with reaching value.
        try {

            //put device id value into SharedPreferences file.
            deviceId = sharedPref.getString(activity.getString(R.string.device_id), "");

        }
        catch (Exception e) {
        }
        return deviceId;
    }





    /**
     * @function public String getIp()
     *
     * @brief get ip value from user interface.
     *
     * @return the ip value.
     */
    public String getIp()
    {
        //get device ip value from user interface.
        String ip =sharedPref.getString(activity.getString(R.string.ip), activity.getResources().getString(R.string.ip));
        return ip;
    }




    public long getPeriodEndTime()
    {
        return sharedPref.getLong(activity.getString(R.string.end_period), -1);
    }




    public boolean setEndTime(long time)
    {
        try {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putLong(activity.getString(R.string.end_period), time);
            editor.commit();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }







}   // class SaveAndRestore end.
