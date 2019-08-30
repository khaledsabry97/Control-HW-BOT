package com.ashiaa.tanmo_app.Model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.ashiaa.tanmo_app.R;
import com.ashiaa.tanmo_app.Services.BootCompletedIntentReceiver;

import java.util.TreeMap;

/**
 * responsible for all the save and restore from disk
 */
public class SaveAndRestore {
    private Context activity;
    private  SharedPreferences sharedPref;
    public SaveAndRestore(Context activity)
    {
        this.activity = activity;
        sharedPref = activity.getSharedPreferences("hello3",Context.MODE_PRIVATE);
    }


    /**
     * save all the credentials
     * @param ip //ip of the device
     * @param password //password of the device
     * @param deviceId // device id
     * @return
     */
    public boolean saveCredentials(String ip,String password,String deviceId)
    {
        try {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(activity.getString(R.string.ip), ip);
            editor.putString(activity.getString(R.string.password), password);
            editor.putString(activity.getString(R.string.device_id), deviceId);
            editor.commit();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * use it to save the clock where every selected days you chose will start on
     * @param hour
     * @param min
     * @return
     */
    public boolean saveDailyClock(int hour,int min)
    {
        try {
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





    public String getUserName()
    {
        String userName = "";
        try {
            userName = sharedPref.getString(activity.getString(R.string.user_name), "");

        }
        catch (Exception e) {
        }
        return userName;
    }
    public String getPassWord()
    {
        String password = "";
        try {
            password = sharedPref.getString(activity.getString(R.string.password), "");

        }
        catch (Exception e) {
        }
        return password;
    }
    public String getDeviceId()
    {
        String deviceId = "";
        try {
            deviceId = sharedPref.getString(activity.getString(R.string.device_id), "");

        }
        catch (Exception e) {
        }
        return deviceId;
    }

    public String getIp()
    {
        String ip = activity.getResources().getString(R.string.ip);
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







}
