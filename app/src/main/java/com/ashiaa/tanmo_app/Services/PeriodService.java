package com.ashiaa.tanmo_app.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.ashiaa.tanmo_app.Controllers.SendController;
import com.ashiaa.tanmo_app.MainActivity;
import com.ashiaa.tanmo_app.Model.SaveAndRestore;
import com.ashiaa.tanmo_app.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * this service working for a period and then cancel itself and sendOff to the device
 */
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
        String channelId = "0";
        String title = getApplicationContext().getString(R.string.app_name);
        final NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this);

        if (manager == null) {
            manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = manager.getNotificationChannel(channelId);
            if (mChannel == null) {
                mChannel = new NotificationChannel(channelId, title, importance);
                manager.createNotificationChannel(mChannel);
            }
        }

        builder.setSmallIcon(R.drawable.ic_info_outline_black_24dp)
                .setContentTitle(title)
                .setContentText("This is a test notification");
        builder.setOngoing(true);
        builder.setOnlyAlertOnce(true);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        manager.notify(0, builder.build());

        addJob(builder);


    }


    void addJob(final NotificationCompat.Builder builder) {
        final Context context = getApplicationContext();
        final SaveAndRestore saveAndRestore = new SaveAndRestore(context);
        final Timer timer = new Timer();
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                long endTime = saveAndRestore.getPeriodEndTime();
                long currentTime = System.currentTimeMillis();
                if (endTime == -1) {
                    stopSelf();

                } else if (currentTime >= endTime) {

                    timer.cancel();
                    timer.purge();
                    sendController.sendOff();
                    saveAndRestore.setEndTime(-1);
                    stopSelf();

                } else {
                    Integer sec = Long.valueOf((endTime - currentTime) / (1000)).intValue();
                    Integer min = sec / 60;
                    Integer hour = min / 60;
                    String timeRemaining = "سيغلق الجهاز بعد ";
                    if (hour > 0) {
                        timeRemaining += hour.toString() + "ساعة و ";
                    }
                    if (min - hour * 60 > 0) {
                        timeRemaining += Integer.valueOf(min - hour * 60).toString() + "دقيقة و ";
                    }
                    if (sec - 60 * min >= 0) {
                        timeRemaining += Integer.valueOf(sec - 60 * min).toString() + "ثانية ";
                    }

                    builder.setContentText(timeRemaining);
                    Intent intent = new Intent("PeriodService"); //FILTER is a string to identify this intent
                    intent.putExtra("time", timeRemaining);
                    sendBroadcast(intent);
                    manager.notify(0, builder.build());

                }

            }
        };

        timer.schedule(task, 0, 1000);
    }
}
