package com.ashiaa.tanmo_app.Model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.ashiaa.tanmo_app.R;
import com.ashiaa.tanmo_app.Services.BootCompletedIntentReceiver;

import java.util.TreeMap;

public class SaveAndRestore {
    private Context activity;
    private  SharedPreferences sharedPref;
    public SaveAndRestore(Context activity)
    {
        this.activity = activity;
        sharedPref = activity.getSharedPreferences("hello",Context.MODE_PRIVATE);
    }


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

    public boolean saveCurrentClock(String cClock)
    {
        try {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(activity.getString(R.string.user_name), cClock);
            editor.commit();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

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
    public String getCclock()
    {
        String cClock = activity.getResources().getString(R.string.current_clock);
        cClock = sharedPref.getString(activity.getString(R.string.current_clock), cClock);

        return cClock;
    }


    public long getPeriodEndTime()
    {
        String endTime = sharedPref.getString(activity.getString(R.string.end_period), "");
        return Long.valueOf(endTime);
    }

    public boolean setEndTime(long time)
    {
        try {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(activity.getString(R.string.end_period), String.valueOf(time));
            editor.commit();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }







}
