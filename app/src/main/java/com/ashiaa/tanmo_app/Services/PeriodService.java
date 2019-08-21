package com.ashiaa.tanmo_app.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.ashiaa.tanmo_app.Controllers.SendController;
import com.ashiaa.tanmo_app.MainActivity;
import com.ashiaa.tanmo_app.Model.SaveAndRestore;
import com.ashiaa.tanmo_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Timer;
import java.util.TimerTask;

public class PeriodService extends Service {
    NotificationManager manager;
    SendController sendController;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Period Service has CREATED", Toast.LENGTH_SHORT).show();

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        sendController = new SendController(getApplicationContext());
        addNotification();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Period Service has Started", Toast.LENGTH_SHORT).show();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {

        manager.cancel(0);
        Intent intent = new Intent("PeriodService"); //FILTER is a string to identify this intent
        intent.putExtra("time", "");
        sendBroadcast(intent);
        super.onDestroy();
        Toast.makeText(this, "Period Service has ended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }




    private void addNotification() {
        final NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_info_outline_black_24dp)
                        .setContentTitle(getApplicationContext().getString(R.string.app_name))
                        .setContentText("This is a test notification");
        builder.setOngoing(true);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        manager.notify(0, builder.build());
        final Context context = getApplicationContext();
        final SaveAndRestore saveAndRestore = new SaveAndRestore(context);
        final Timer timer = new Timer();

        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                long endTime = saveAndRestore.getPeriodEndTime();
                long currentTime = System.currentTimeMillis();
                if (endTime == -1)
                {
                    stopSelf();

                }
                else if(currentTime >= endTime) {

                    timer.cancel();
                    timer.purge();
                    sendController.sendOff();
                    stopSelf();

                }
                else
                {
                   Integer sec =  Long.valueOf((endTime - currentTime)/(1000)).intValue();
                    Integer min = sec/60;
                    Integer hour = min/60;
                   String timeRemaining ="سيغلق الجهاز بعد ";
                   if (hour > 0)
                   {
                       timeRemaining += hour.toString() + "ساعة و ";
                   }
                    if (min - hour*60> 0)
                    {
                        timeRemaining += Integer.valueOf(min - hour*60).toString() + "دقيقة و ";
                    }
                    if (sec - 60*min >= 0 )
                    {
                        timeRemaining += Integer.valueOf(sec - 60*min).toString() + "ثانية ";
                    }

                    //String st =   "سيغلق الجهاز بعد " + timeRemaining + " دقيقة";
                    builder.setContentText(timeRemaining);
                    Intent intent = new Intent("PeriodService"); //FILTER is a string to identify this intent
                    intent.putExtra("time", timeRemaining);
                    sendBroadcast(intent);
                    manager.notify(0, builder.build());

                }

            }
        };

        timer.schedule(task,0, 10);





    }
}
