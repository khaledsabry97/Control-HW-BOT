/**
 * @file DailyService.java
 *
 * @brief daily service file.
 */

//folder package contain DailyService
package com.ashiaa.tanmo_app.Services;


import android.app.AlarmManager;
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


import android.os.SystemClock;

// A toast is a view containing a quick little message for the user.
import android.widget.Toast;


// Denotes that a parameter, field or method return value can be null. When decorating a method
// call parameter, this denotes that the parameter can legitimately be null and the method will
// gracefully deal with it. Typically used on optional parameters.When decorating a method, this
// denotes the method might legitimately return null.
import androidx.annotation.Nullable;

import com.ashiaa.tanmo_app.Model.SaveAndRestore;

import java.util.Calendar;



/**
 * @class DailyService
 */
public class DailyService extends Service {

    SaveAndRestore saveAndRestore;



    /**
     * @function public void onCreate()
     *
     * @details onCreate() is called when the Service object is instantiated (ie: when the service is
     * created). You should do things in this method that you need to do only once (ie: initialize
     * some variables, etc.). onCreate() will only ever be called once per instantiated object.You
     * only need to implement onCreate() if you actually want/need to initialize something only once.
     */
    @Override
    public void onCreate() {

        super.onCreate();   // call the default code of onCreate() inside new onCreate().

        saveAndRestore = new SaveAndRestore(getApplicationContext());
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

        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        startAlarm(true,true);

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

        super.onDestroy();  // call the default code of onDestroy() inside new onDestroy().

        Toast.makeText(this, "Service Destroy", Toast.LENGTH_LONG).show();  // message for user.
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




    private void startAlarm(boolean isNotification, boolean isRepeat) {
        AlarmManager manager = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        Intent myIntent;
        PendingIntent pendingIntent;

        // SET TIME HERE
        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,saveAndRestore.getDailyClockHour());
        calendar.set(Calendar.MINUTE,saveAndRestore.getDailyClockMin());
        calendar.set(Calendar.SECOND,0);

        myIntent = new Intent(getApplicationContext(), AlarmNotificationReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,myIntent,0);

        Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();

        if(!isRepeat)
            manager.set(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime(),pendingIntent);
        else
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);
        stopSelf();
    }
}
