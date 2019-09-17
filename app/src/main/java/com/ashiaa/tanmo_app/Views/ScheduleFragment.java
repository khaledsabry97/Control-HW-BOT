/**
 * @file ScheduleFragment.java
 *
 * @brief schedule tab fragment file.
 */


// folder package contain ScheduleFragment.
package com.ashiaa.tanmo_app.Views;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;


// Bundles are generally used for passing data between various Android activities. It depends on
// you what type of values you want to pass, but bundles can hold all types of values and pass them
// to the new activity.
import android.os.Bundle;


import android.os.SystemClock;


// LayoutInflater is used to create a new View (or Layout) object from one of your xml layouts.
// The LayoutInflater takes XML file as an input and builds View objects from it.
// Instantiates a layout XML file into its corresponding View objects. It is never used directly.
// Instead, use getLayoutInflater() or getSystemService(Class ) to retrieve a standard
// LayoutInflater instance that is already hooked up to the current context and correctly
// configured for the device you are running on. For example:
// LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
import android.view.LayoutInflater;




// This class represents the basic building block for user interface components. A View occupies
// a rectangular area on the screen and is responsible for drawing and event handling. View is the
// base class for widgets, which are used to create interactive UI components (buttons, text fields,
// etc.).Usually View is used as arguments in methods which act as some kind of listener.
// View objects are the basic building blocks of User Interface(UI) elements in Android. View is a
// simple rectangle box which responds to the user's actions. Examples are EditText, Button,
// CheckBox etc..
import android.view.View;



// ViewGroup is the invisible container. It holds View and ViewGroup For example, LinearLayout is
// the ViewGroup that contains Button(View), and other Layouts also. ViewGroup is the base class
// for Layouts. A ViewGroup is a special view that can contain other views (called children.)
// The view group is the base class for layouts and views containers.
import android.view.ViewGroup;


import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;



// A Fragment is a piece of an application's user interface or behavior that can be placed in an
// Activity. Interaction with fragments is done through FragmentManager, which can be obtained via
// Activity#getFragmentManager() and Fragment#getFragmentManager().
import androidx.fragment.app.Fragment;


import com.ashiaa.tanmo_app.MainActivity;
import com.ashiaa.tanmo_app.Model.SaveAndRestore;

// local app resources folder.
import com.ashiaa.tanmo_app.R;
import com.ashiaa.tanmo_app.Services.AlarmNotificationReceiver;
import com.ashiaa.tanmo_app.Services.DailyService;

import java.util.ArrayList;
import java.util.Calendar;



/**
 * @class ScheduleFragment
 *
 * @brief class schedule tab fragment.
 */
public class ScheduleFragment extends Fragment {
    TimePicker startTime, periodTime;
    CheckBox sat, sun, mon, tue, wed, thr, fri;
    Button save;
    TextView info;


    SaveAndRestore saveAndRestore;


    /**
     * @function public static ScheduleFragment newInstance()
     *
     * @brief New instance schedule fragment.
     *
     * @details Class.forName().newInstance() is a dynamic construct that looks up a class with
     * a specific name. It's slower than using new. Use Class.forName().newInstance() if you
     * don't know what type of object you'll be making.using static to call function without
     * creating and access class directly.
     *
     * @return the home fragment object.
     */
    public static ScheduleFragment newInstance() {

        // create object from ScheduleFragment.
        ScheduleFragment fragment = new ScheduleFragment();

        // return object from ScheduleFragment
        return fragment;
    }





    /**
     * @function public View onCreateView(
     * LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
     *
     * @brief Called to have the fragment instantiate its user interface view.
     *
     * @details After the onCreate() is called (in the Fragment), the Fragment's onCreateView() is
     * called. You can assign your View variables and do any graphical initialisations. You are
     * expected to return a View from this method, and this is the main UI view, but if your
     * Fragment does not use any layouts or graphics, you can return null (happens by default if
     * you don't override).
     *
     * @param inflater : LayoutInflater type is used to create a new View (or Layout) object from
     * one of your xml layouts.
     *
     * @param container : A ViewGroup type is a special view that can contain other views.
     *
     * @param savedInstanceState : Bundle type is generally used for passing data between various
     * Android activities.
     *
     * @return view : View type is a simple rectangle box which responds to the user's actions.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        // prototype : View inflate(int resource, ViewGroup root, boolean attachToRoot).
        // Inflate a new view hierarchy from the specified xml resource.
        // Inflate the xml resource layout for this fragment.
        // @param resource : int ID for an XML layout resource to load (e.g., R.layout.main_page).
        // @param root : The root view of the hierarchy you are inflating the resource to attach to.
        // root ViewGroup Optional view to be the parent of the generated hierarchy (if attachToRoot
        // is true), or else simply an object that provides a set of LayoutParams values for root
        // of the returned hierarchy (if attachToRoot is false.) This value may be null.
        // @param attachToRoot : it governs whether or not the inflated view is attached to the
        // supplied root after inflation. attachToRoot boolean Whether the inflated hierarchy should
        // be attached to the root parameter? If false, root is only used to create the correct
        // subclass of LayoutParams for the root view in the XML.
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        startTime = view.findViewById(R.id.start_time_id);
        periodTime = view.findViewById(R.id.end_time_id);
        save = view.findViewById(R.id.save_id);
        sat = view.findViewById(R.id.sat);
        sun = view.findViewById(R.id.sun);
        mon = view.findViewById(R.id.mon);
        tue = view.findViewById(R.id.tue);
        wed = view.findViewById(R.id.wed);
        thr = view.findViewById(R.id.thr);
        fri = view.findViewById(R.id.fri);
        info = view.findViewById(R.id.info);


        saveAndRestore = new SaveAndRestore(getContext());

        configureViews();
        return view;
    }

    /**
     * setup your views at the begining
     */
    private void configureViews() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAndRestore.saveDailyClock(startTime.getCurrentHour(), startTime.getCurrentMinute());
                saveAndRestore.saveDailyPeriod(periodTime.getCurrentHour(), periodTime.getCurrentMinute());
                saveAndRestore.saveDayState(getString(R.string.sat), sat.isChecked());
                saveAndRestore.saveDayState(getString(R.string.sun), sun.isChecked());
                saveAndRestore.saveDayState(getString(R.string.mon), mon.isChecked());
                saveAndRestore.saveDayState(getString(R.string.tue), tue.isChecked());
                saveAndRestore.saveDayState(getString(R.string.wed), wed.isChecked());
                saveAndRestore.saveDayState(getString(R.string.thr), thr.isChecked());
                saveAndRestore.saveDayState(getString(R.string.fri), fri.isChecked());
                Toast.makeText(getContext(), "تم الحفظ بنجاح!", Toast.LENGTH_SHORT).show();
                updateTextInfo();
                getActivity().startService(new Intent(getContext(), DailyService.class));
            }
        });

    }

    /**
     * update info text whenever you save the setup
     */
    void updateTextInfo() {
        String inf = "";
        ArrayList<String> days = new ArrayList<>();
        if (sat.isChecked())
            days.add("السبت");
        if (sun.isChecked())
            days.add("الأحد");
        if (mon.isChecked())
            days.add("الإثنين");
        if (tue.isChecked())
            days.add("الثلاثاء");
        if (wed.isChecked())
            days.add("الأربعاء");
        if (thr.isChecked())
            days.add("الخميس");
        if (fri.isChecked())
            days.add("الجمعة");

        if(days.size() <=0) {
            info.setText("");
            return;
        }

        inf +="سيعمل الجهاز يوم ";
        inf += days.get(0);
        for(int i = 1;i<days.size();i++)
        {
            inf += " و ";
            inf += days.get(i);
        }
        inf += "\nالساعة ";
        int h = saveAndRestore.getDailyClockHour();
        String m = String.valueOf(saveAndRestore.getDailyClockMin());
        if(m.length() == 1)
            m = "0"+m;
        if(h == 0)
        {
            inf +=String.valueOf(12) +":"+ String.valueOf(m);
            inf += " صباحًا";
        }
        else if (h <12)
        {
            inf +=String.valueOf(h) +":"+ String.valueOf(m);
            inf += " صباحًا";
        }
        else if (h == 12)
        {
            inf +=String.valueOf(h) +":"+ String.valueOf(m);
            inf += " مساءًا";
        }
        else if(h > 12)
        {
            inf +=String.valueOf(h - 12) +":"+ String.valueOf(m);
            inf += " مساءًا";
        }

        inf += "\nولمدة ";
        Integer min = saveAndRestore.getDailyPeriodMin();
        Integer hour = saveAndRestore.getDailyPeriodHour();
        if (hour > 0)
        {
            inf += hour.toString() + " ساعة و ";
        }
        if (min> 0)
        {
            inf += Integer.valueOf(min).toString() + " دقيقة";
        }

        info.setText(inf);




    }

    @Override
    public void onResume() {
        super.onResume();
        checkLastState(); //so when Fragment get pause and restart he check the last state
    }

    /**
     * to restore all the required settings and ui when you open the fragment
     */
    private void checkLastState() {
        startTime.setCurrentHour(saveAndRestore.getDailyClockHour());
        startTime.setCurrentMinute(saveAndRestore.getDailyClockMin());

        periodTime.setCurrentHour(saveAndRestore.getDailyPeriodHour());
        periodTime.setCurrentMinute(saveAndRestore.getDailyPeriodMin());

        sat.setChecked(saveAndRestore.getDayState(getActivity().getString(R.string.sat)));
        sun.setChecked(saveAndRestore.getDayState(getActivity().getString(R.string.sun)));
        mon.setChecked(saveAndRestore.getDayState(getActivity().getString(R.string.mon)));
        tue.setChecked(saveAndRestore.getDayState(getActivity().getString(R.string.tue)));
        wed.setChecked(saveAndRestore.getDayState(getActivity().getString(R.string.wed)));
        thr.setChecked(saveAndRestore.getDayState(getActivity().getString(R.string.thr)));
        fri.setChecked(saveAndRestore.getDayState(getActivity().getString(R.string.fri)));

        periodTime.setIs24HourView(true);
        updateTextInfo();

    }



}

