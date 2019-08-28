package com.ashiaa.tanmo_app.Services;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.ashiaa.tanmo_app.MainActivity;
import com.ashiaa.tanmo_app.Model.SaveAndRestore;
import com.ashiaa.tanmo_app.R;

import java.util.Calendar;

import static android.app.PendingIntent.FLAG_ONE_SHOT;

/**
 * Created by azem on 10/29/17.
 */

public class AlarmNotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        SaveAndRestore saveAndRestore = new SaveAndRestore(context);





        Calendar calendar = Calendar.getInstance();

        String[] days = new String[] { context.getString(R.string.sun), context.getString(R.string.mon), context.getString(R.string.tue), context.getString(R.string.wed), context.getString(R.string.thr), context.getString(R.string.fri), context.getString(R.string.sat) };

        String day = days[calendar.get(Calendar.DAY_OF_WEEK)];
        if(saveAndRestore.getDayState(day) == false)
            return;

     Intent intent1 = new Intent(context,PeriodService.class);
     context.startService(intent1);
    }
}