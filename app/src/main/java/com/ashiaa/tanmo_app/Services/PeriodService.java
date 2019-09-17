/**
 * @file PeriodService.java
 *
 * @brief file to make device work for a period of time.
 */


// folder package contain PeriodService.
package com.ashiaa.tanmo_app.Services;

import android.app.Notification;
import android.app.NotificationChannel;


// Class to notify the user of events that happen. This is how you tell the user that something has
// happened in the background.
import android.app.NotificationManager;



// A PendingIntent is a token that you give to a foreign application (e.g. NotificationManager,
// AlarmManager, Home Screen AppWidgetManager, or other 3rd party applications), which allows the
// foreign application to use your application's permissions to execute a predefined piece of code.
// If you give the foreign application an Intent, it will execute your Intent with its own
// permissions. But if you give the foreign application a PendingIntent, that application will
// execute your Intent using your application's permission.
import android.app.PendingIntent;



// A Service is an application component representing either an application's desire to
// perform a longer-running operation while not interacting with the user or to supply
// functionality for other applications to use. Each service class must have a
// corresponding <service> declaration in its package's AndroidManifest.xml. Services can
// be started with Context.startService() and Context.bindService().
import android.app.Service;


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


import android.os.Build;


// - A bound service is one that allows application components to bind to it by calling bindService()
// to create a long-standing connection.The service lives only to serve the application component
// that is bound to it, so when there are no components bound to the service, the system destroys
// it. You do not need to stop a bound service in the same way that you must when the service is
// started through onStartCommand().
// - IBinder: To create a bound service, you must define the interface that specifies how a client
// can communicate with the service. This interface between the service and a client must be an
// implementation of IBinder and is what your service must return from the onBind() callback method.
// After the client receives the IBinder, it can begin interacting with the service through
// that interface.
import android.os.IBinder;


// A toast is a view containing a quick little message for the user.
import android.widget.Toast;


// A class that represents how a persistent notification is to be presented to the user using
// the NotificationManager.
import androidx.core.app.NotificationCompat;

import com.ashiaa.tanmo_app.Controllers.SendController;
import com.ashiaa.tanmo_app.MainActivity;   // local MainActivity file.
import com.ashiaa.tanmo_app.Model.SaveAndRestore;
import com.ashiaa.tanmo_app.R;  // local app resources folder.

import java.util.Timer;
import java.util.TimerTask;




/**
 * @class PeriodService
 *
 * @brief this service working for a period and then cancel itself and sendOff to the device.
 */
public class PeriodService extends Service {
    NotificationManager manager;
    SendController sendController;
    int state;




    /**
     * @function public void onCreate()
     *
     * @details onCreate() is called when the Service object is instantiated (ie: when the service
     * is created). You should do things in this method that you need to do only once
     * (ie: initialize some variables, etc.). onCreate() will only ever be called once per
     * instantiated object.You only need to implement onCreate() if you actually want/need to
     * initialize something only once.
     */
    @Override
    public void onCreate() {
        super.onCreate();   //call the default code of onCreate() inside new onCreate().

        Toast.makeText(this, "Period Service has CREATED", Toast.LENGTH_SHORT).show();

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        sendController = new SendController(getApplicationContext());
        state = 0;
        addNotification();
    }






    /**
     * @function public int onStartCommand(Intent intent, int flags, int startId)
     *
     * @details onStartCommand() is called every time a client starts the service using
     * Context.startService(Intent). This means that onStartCommand() can get called multiple times.
     * You should do the things in this method that are needed each time a client requests something
     * from your service.
     *
     * @param intent : An Intent is a messaging object you can use to request an action from another
     * app component.
     *
     * @param flags : a flag is a value that acts as a signal for a function or process. The value
     * of the flag is used to determine the next step of a program.
     *
     * @param startId :  A unique integer representing this specific request to start. Use with
     * stopSelfResult(int).
     *
     * @return Service.START_STICKY : If service is started with START_STICKY return type, it
     * going to work in background even if activity is not foreground. if android forcefully
     * closed service due to memory problem or some other cases, it will restart service without
     * interaction of the user.
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // message for user.
        Toast.makeText(this, "Period Service has Started", Toast.LENGTH_SHORT).show();

        return Service.START_STICKY;
    }





    /**
     * @function public void onDestroy()
     *
     * @details The system invokes this method when the service is no longer used and is being
     * destroyed. Your service should implement this to clean up any resources such as threads,
     * registered listeners, or receivers. This is the last call that the service receives.
     */
    @Override
    public void onDestroy() {

        manager.cancel(0);
        Intent intent = new Intent("PeriodService"); //FILTER is a string to identify this intent
        intent.putExtra("time", "");
        intent.putExtra("state",state);
        sendBroadcast(intent);
        super.onDestroy();  // call the default code of onDestroy() inside new onDestroy().

        // message for user.
        Toast.makeText(this, "Period Service has ended", Toast.LENGTH_SHORT).show();
    }






    /**
     * @function public IBinder onBind(Intent arg0)
     *
     * @details  IBinder: "interface binder" To create a bound service, you must define the
     * interface that specifies how a client can communicate with the service. This interface
     * between the service and a client must be an implementation of IBinder and is what your
     * service must return from 'the onBind() callback method'. After the client receives the
     * IBinder, it can begin interacting with the service through that interface.
     *
     *  @param arg0 : intent parameter to pass to onBind().
     *
     *  @return : IBinder and is what your service must return from 'the onBind() callback method'.
     */
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }




    /**
     * @function private void addNotification()
     *
     * @brief Notifications provide short, timely information about events in your app.
     */
    private void addNotification() {

        String channelId = "0";
        String title = getApplicationContext().getString(R.string.app_name);


        // Builder class for persistent notification is to be presented to the user objects.
        // Allows easier control over all the flags,as well as help constructing the typical
        // notification layouts.prototype : NotificationCompat.Builder(Context context).
        final NotificationCompat.Builder builder =
                //this refer to context and channel_01 refer to channel ID.
                new NotificationCompat.Builder(this,channelId);

        if (manager == null) {

            // - create notification object to add notification.
            // - NotificationManager : Class to notify the user of events that happen.
            // - getSystemService() factory method that return object
            // - getSystemService() is a method on the same class, which returns a reference to a system
            //   service object.
            // - The returned parameter is of type Object, so you have to cast it to the appropriate
            //   class type - in this case ConnectivityManager.So the key thing is that the value you
            //   pass in to this method must match up with the type you are casting to.
            manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = manager.getNotificationChannel(channelId);
            if (mChannel == null) {
                mChannel = new NotificationChannel(channelId, title, importance);
            }
            manager.createNotificationChannel(mChannel);
        }

        builder.setSmallIcon(R.drawable.ic_info_outline_black_24dp) // set notification icon.
                .setContentTitle(title)     // set notification title.
                .setContentText("");        // set notification message.
        builder.setOngoing(true);
        builder.setOnlyAlertOnce(true);



        // Intent prototype : public Intent (Context packageContext,Class<?> cls).
        // Create an intent for a specific component. All other fields (action, data, type, class)
        // are null
        Intent notificationIntent = new Intent(this, MainActivity.class);



        // - prototype : public static PendingIntent getActivity
        // (Context context, int requestCode, Intent intent, int flags).
        // - getActivity() static PendingIntent factory method which used to obtain a PendingIntent
        // object.
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
        PendingIntent.FLAG_UPDATE_CURRENT);


        // NotificationCompat.Builder builder = new NotificationCompat.Builder(this)..
        builder.setContentIntent(contentIntent);


        // Add as notification
        // - void notify(int id, Notification notification)
        //   Post a notification to be shown in the status bar.
        // - build() : Combine all of the options that have been set and return a new object.
        manager.notify(0, builder.build());

        addJob(builder);    // call declared function next


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
                    state = 1;
                    stopSelf();

                } else if (currentTime >= endTime) {

                    state = 2;
                    timer.cancel();
                    timer.purge();
                    sendController.sendOff();
                    saveAndRestore.setEndTime(-1);
                    stopSelf();

                } else {
                    state = 3;
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
