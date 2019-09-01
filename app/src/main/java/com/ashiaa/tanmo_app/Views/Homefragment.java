package com.ashiaa.tanmo_app.Views;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.ashiaa.tanmo_app.Controllers.SendController;
import com.ashiaa.tanmo_app.Model.Constants;
import com.ashiaa.tanmo_app.Model.SaveAndRestore;
import com.ashiaa.tanmo_app.R;
import com.ashiaa.tanmo_app.Services.PeriodService;


public class Homefragment extends Fragment {
    ViewGroup periodViewGroup;
    Button startButton, stopButton, startPeriodButton, stopPeriodButton;
    CheckBox periodCheckBox;
    TimePicker timePicker;
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
        checkLastState();
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(periodStateBR);
        getActivity().unregisterReceiver(buttonStates);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_menu_fragment, container, false);
        periodViewGroup = view.findViewById(R.id.period_layout);
        startButton = view.findViewById(R.id.button_on);
        stopButton = view.findViewById(R.id.button_off);
        startPeriodButton = view.findViewById(R.id.period_button_on);
        stopPeriodButton = view.findViewById(R.id.period_button_off);
        periodCheckBox = view.findViewById(R.id.checkBox);
        timePicker = view.findViewById(R.id.timepicker);
        remainingTime = view.findViewById(R.id.remaining_time_id);
        saveAndRestore = new SaveAndRestore(getContext());

        sendController = new SendController(this.getContext());

        setupView();


        return view;
    }

    private void setupView() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send start enigne
                sendController.sendOn();
                //enableStopButton(true);

            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send stop
                sendController.sendOff();
                stopPeriod();
                //enableStopButton(false);


            }
        });

        periodCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true)
                    periodViewGroup.setVisibility(View.VISIBLE);
                else {
                    periodViewGroup.setVisibility(View.GONE);
                    stopPeriod();
                }

            }
        });

        startPeriodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        stopPeriodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        timePicker.setIs24HourView(true);



    }

    private void stopPeriod() {
        periodIntent = new Intent(getContext(), PeriodService.class);
        //add end time at storage
        getActivity().stopService(periodIntent);
        updateTime("");

        saveAndRestore.setEndTime(-1);

    }

    private void checkLastState() {
        //if set period before then sho it
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
