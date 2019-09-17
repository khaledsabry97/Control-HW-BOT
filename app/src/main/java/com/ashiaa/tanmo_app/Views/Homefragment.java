/**
 * @file Homefragment.java
 *
 * @brief Home tab, main menu fragment file.
 */


// folder package contain Home_fragment.
package com.ashiaa.tanmo_app.Views;



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


import android.content.IntentFilter;


// Bundles are generally used for passing data between various Android activities. It depends on
// you what type of values you want to pass, but bundles can hold all types of values and pass them
// to the new activity.
import android.os.Bundle;




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


// A user interface element the user can tap or click to perform an action.
import android.widget.Button;



// A checkbox is a specific type of two-states button that can be either checked or unchecked
// CheckBox extends CompoundButton class extends Button class.
import android.widget.CheckBox;



// A button with two states, checked and unchecked. When the button is pressed or clicked, the state
// changes automatically.
import android.widget.CompoundButton;


import android.widget.TextView;

// A widget for selecting the time of day, in either 24-hour or AM/PM mode.
import android.widget.TimePicker;




// A Fragment is a piece of an application's user interface or behavior that can be placed in an
// Activity. Interaction with fragments is done through FragmentManager, which can be obtained via
// Activity#getFragmentManager() and Fragment#getFragmentManager().
import androidx.fragment.app.Fragment;

// local class controller that will prepare sent data and call SendRequest object.
import com.ashiaa.tanmo_app.Controllers.SendController;
import com.ashiaa.tanmo_app.Model.Constants;
import com.ashiaa.tanmo_app.Model.SaveAndRestore;

// local app resources folder.
import com.ashiaa.tanmo_app.R;
import com.ashiaa.tanmo_app.Services.PeriodService;



/**
 * @class Homefragment
 *
 * @brief Home tab, main menu fragment.
 */
public class Homefragment extends Fragment {


    // define general class attributes. =====>

    // define group for 'period timer views' attribute.
    ViewGroup periodViewGroup;

    // define buttons attributes.
    Button startButton, stopButton, startPeriodButton, stopPeriodButton;

    // define period checkbox attribute.
    CheckBox periodCheckBox;

    // define period time picker attribute.
    TimePicker timePicker;

    // define send controller attribute.
    SendController sendController;

    SaveAndRestore saveAndRestore;

    TextView remainingTime;

    Intent periodIntent;

    String time = "";

    BroadcastReceiver buttonStates,periodStateBR;


    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(periodStateBR, new IntentFilter("PeriodService"));
        getActivity().registerReceiver(buttonStates, new IntentFilter(getActivity().getString(R.string.ButtonStateBrodReceiver)));

        // check if there is previous timer
        checkLastState();
    }



    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(periodStateBR);
        getActivity().unregisterReceiver(buttonStates);
    }



    /**
     * @function public void onCreate(Bundle savedInstanceState)
     *
     * @brief Called to do initial creation of a fragment.
     *
     * @details The onCreate() method in a Fragment is called after the Activity's
     * onAttachFragment() but before that Fragment's onCreateView().In this method, you can assign
     * variables, get Intent extras, and anything else that doesn't involve the View hierarchy
     * (i.e. non-graphical initialisations). This is because this method can be called when the
     * Activity's onCreate() is not finished, and so trying to access the View hierarchy here may
     * result in a crash. If you save the state of the application in a bundle (typically
     * non-persistent, dynamic data in onSaveInstanceState), it can be passed back to onCreate
     * if the activity needs to be recreated (e.g., orientation change) so that you don't lose
     * this prior information.
     *
     * @param savedInstanceState : Bundle data container type which save fragment data to be used
     * whenever fragment needed to be created with last saved settings.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {


        // call the default code of onCreate() inside new onCreate() and pass savedInstanceState
        // to it.
        super.onCreate(savedInstanceState);
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
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {                                                                   // onCreateView start.


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
        View view = inflater.inflate(R.layout.fragment_main_menu_fragment, container, false);






        // prototype : public final T findViewById (int id).
        // Finds the first descendant view with the given ID, the view itself if the ID matches
        // getId(), or null if the ID is invalid (< 0) or there is no matching view in the
        // hierarchy.
        // @param id : int the ID to search for.
        // @return T : a view with given ID if found, or null otherwise.

        // find root in view.
        periodViewGroup = view.findViewById(R.id.period_layout);

        // find main on button in view.
        startButton = view.findViewById(R.id.button_on);

        // find main off button in view.
        stopButton = view.findViewById(R.id.button_off);

        // find timer on button in view.
        startPeriodButton = view.findViewById(R.id.period_button_on);

        // find timer off button in view.
        stopPeriodButton = view.findViewById(R.id.period_button_off);

        // find timer checkbox in view.
        periodCheckBox = view.findViewById(R.id.checkBox);

        // find timer picker in view.
        timePicker = view.findViewById(R.id.timepicker);

        remainingTime = view.findViewById(R.id.remaining_time_id);
        saveAndRestore = new SaveAndRestore(getContext());



        // prototype : public SendController(Context activity).
        // class controller that will prepare sent data and call SendRequest object.
        sendController = new SendController(this.getContext());

        setupView();


        return view;
    }                                                               // onCreateView end.




    private void setupView() {




        // prototype : public void setOnClickListener (View.OnClickListener l).
        // Register a callback to be invoked when this view is clicked. If this view is not
        // clickable, it becomes clickable.
        // @param l : View.OnClickListener The callback that will run This value may be null.
        // new View.OnClickListener() : defines an anonymous inner class and creates an instance
        // of it at the same time.
        // prototype : public static interface View.OnClickListener{}.
        // Interface definition for a callback to be invoked when a view is clicked.
        startButton.setOnClickListener(new View.OnClickListener() {


            // prototype : void onClick(View v).
            // It is abstract definition which related to Android API directlly so, it has no
            // declaration.Called when a view has been clicked.
            // @param view : The view that was clicked.
            @Override
            public void onClick(View view) {

                // send turn on action.
                sendController.sendOn();
                //enableStopButton(true);

            }
        });




        // prototype : public void setOnClickListener (View.OnClickListener l).
        // Register a callback to be invoked when this view is clicked. If this view is not
        // clickable, it becomes clickable.
        // @param l : View.OnClickListener The callback that will run This value may be null.
        // new View.OnClickListener() : defines an anonymous inner class and creates an instance
        // of it at the same time.
        // prototype : public static interface View.OnClickListener{}.
        // Interface definition for a callback to be invoked when a view is clicked.
        stopButton.setOnClickListener(new View.OnClickListener() {

            // prototype : void onClick(View v).
            // It is abstract definition which related to Android API directlly so, it has no
            // declaration.Called when a view has been clicked.
            // @param view : The view that was clicked.
            @Override
            public void onClick(View view) {
                //send stop
                sendController.sendOff();
                stopPeriod();
                //enableStopButton(false);


            }
        });





        // note : CheckBox extends CompoundButton class extends Button class
        // prototype : public void setOnCheckedChangeListener
        // (CompoundButton.OnCheckedChangeListener listener).
        // Register a callback to be invoked when the checked state of this button changes.
        // @param listener : CompoundButton.OnCheckedChangeListener: the callback to call on
        // checked state change This value may be null.
        // new CompoundButton.OnCheckedChangeListener() : defines an anonymous inner class and
        // creates an instance of it at the same time.
        // prototype : public static interface CompoundButton.OnCheckedChangeListener{}.
        // Interface definition for a callback to be invoked when the checked state of a
        // compound button changed.
        periodCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            // prototype : public abstract void onCheckedChanged
            // (CompoundButton buttonView,boolean isChecked).
            // It is abstract definition which related to Android API directlly so, it has no
            // declaration.Called when the checked state of a compound button has changed.
            // @param buttonView : CompoundButton The compound button view whose state has changed.
            // @param isChecked : boolean The new checked state of buttonView.
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true)

                    // show timer period menu.
                    periodViewGroup.setVisibility(View.VISIBLE);
                else {

                    // hide timer period menu.
                    periodViewGroup.setVisibility(View.GONE);
                    stopPeriod();
                }

            }
        });





        // prototype : public void setOnClickListener (View.OnClickListener l).
        // Register a callback to be invoked when this view is clicked. If this view is not
        // clickable, it becomes clickable.
        // @param l : View.OnClickListener The callback that will run This value may be null.
        // new View.OnClickListener() : defines an anonymous inner class and creates an instance
        // of it at the same time.
        // prototype : public static interface View.OnClickListener{}.
        // Interface definition for a callback to be invoked when a view is clicked.
        startPeriodButton.setOnClickListener(new View.OnClickListener() {


            // prototype : void onClick(View v).
            // It is abstract definition which related to Android API directlly so, it has no
            // declaration.Called when a view has been clicked.
            // @param view : The view that was clicked.
            @Override
            public void onClick(View view) {

                /**
                 * start period timer button code.
                 */
                //add end time at storage

                periodIntent = new Intent(getContext(), PeriodService.class);

                int hour = 0;
                int min = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    hour = timePicker.getHour() * 60 * 60 * 1000;
                    min = timePicker.getMinute() * 60 * 1000;
                } else {
                    hour = timePicker.getCurrentHour() * 60 * 60 * 1000;
                    min = timePicker.getCurrentMinute() * 60 * 1000;
                }
                saveAndRestore.setEndTime(System.currentTimeMillis() + hour + min);
                sendController.sendOn();
                getActivity().startService(periodIntent);

                enableStopPeriodButton(true);
                //enableStopButton(true);

            }
        });








        // prototype : public void setOnClickListener (View.OnClickListener l).
        // Register a callback to be invoked when this view is clicked. If this view is not
        // clickable, it becomes clickable.
        // @param l : View.OnClickListener The callback that will run This value may be null.
        // new View.OnClickListener() : defines an anonymous inner class and creates an instance
        // of it at the same time.
        // prototype : public static interface View.OnClickListener{}.
        // Interface definition for a callback to be invoked when a view is clicked.
        stopPeriodButton.setOnClickListener(new View.OnClickListener() {

            // prototype : void onClick(View v).
            // It is abstract definition which related to Android API directlly so, it has no
            // declaration.Called when a view has been clicked.
            // @param view : The view that was clicked.
            @Override
            public void onClick(View view) {

                /**
                 * stop period timer button code.
                 */
                //add end time at storage

                sendController.sendOff();
                stopPeriod();
                //enableStopButton(false);
                enableStopPeriodButton(false);


            }
        });






        periodStateBR = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                time = intent.getExtras().getString("time");
                int state = intent.getExtras().getInt("state");
                updateTime(time);
                if(time.isEmpty() && state == 2)
                {
                    //enableStopButton(false);
                }
                else if(time.isEmpty() == false)
                {
                    //enableStopButton(true);
                }
            }
        };






        buttonStates = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean onButtonState = intent.getExtras().getBoolean("onButtonState");
                enableStopButton(!Constants.onButtonState);

            }
        };
        //enableStopButton(false);
        enableStopPeriodButton(false);




        // prototype : public void setIs24HourView (Boolean is24HourView).
        // Sets whether this widget displays time in 24-hour mode or 12-hour mode with an AM/PM
        // picker.
        //
        // @param is24HourView : Boolean: true to display in 24-hour mode, false for 12-hour mode
        // with AM/PM This value must never be null.
        timePicker.setIs24HourView(true);



    }







    private void stopPeriod() {
        periodIntent = new Intent(getContext(), PeriodService.class);
        //add end time at storage
        getActivity().stopService(periodIntent);
        updateTime("");

        saveAndRestore.setEndTime(-1);

    }




    /**
     * function to check if there is a previous timer.
     */
    private void checkLastState() {
        //if set period before then show it
        long endTime = saveAndRestore.getPeriodEndTime();
        long currentTime = System.currentTimeMillis();
        if (currentTime <= endTime && endTime != -1) {
            periodCheckBox.setChecked(true);
        }
        enableStopButton(!Constants.onButtonState);
    }




    private void updateTime(String time) {
        if (time.isEmpty() == true) {
            enableStopPeriodButton(false);
        }
        else {
            enableStopPeriodButton(true);
        }


        remainingTime.setText(time);
    }






    void enableStopButton(boolean state)
    {
        if (state != true)
        {
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        }
        else
        {
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
        }
    }




    void enableStopPeriodButton(boolean state)
    {
        if (state != true)
        {
            startPeriodButton.setEnabled(true);
            stopPeriodButton.setEnabled(false);
        }
        else
        {
            startPeriodButton.setEnabled(false);
            stopPeriodButton.setEnabled(true);
        }
    }

}
