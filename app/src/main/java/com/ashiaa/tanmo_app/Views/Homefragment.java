package com.ashiaa.tanmo_app.Views;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ashiaa.tanmo_app.Controllers.SendController;
import com.ashiaa.tanmo_app.Model.SaveAndRestore;
import com.ashiaa.tanmo_app.R;
import com.ashiaa.tanmo_app.Services.PeriodService;


public class Homefragment extends Fragment {
    ViewGroup periodViewGroup;
    Button startButton,stopButton,startPeriodButton, stopPeriodButton;
    CheckBox periodCheckBox;
    TimePicker timePicker;
    SendController sendController;
    SaveAndRestore saveAndRestore;
TextView remainingTime;
    Intent periodIntent;

    String time = "";

    public Homefragment() {
        // Required empty public constructor
    }


    public static Homefragment newInstance() {
        Homefragment fragment = new Homefragment();
        return fragment;
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
             time = intent.getExtras().getString("time");
             updateTime(time);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(receiver, new IntentFilter("PeriodService"));
        checkLastState();
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);

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
        timePicker.setIs24HourView(true);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send start enigne
                sendController.sendOn();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send stop
                sendController.sendOff();
            }
        });

        periodCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true)
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
                     hour = timePicker.getHour()*60*60*1000;
                     min = timePicker.getMinute()*60*1000;
                }
                else
                {
                     hour = timePicker.getCurrentHour()*60*60*1000;
                     min = timePicker.getCurrentMinute()*60*1000;
                }
                saveAndRestore.setEndTime(System.currentTimeMillis() +hour+min);
                getActivity().startService(periodIntent);
            }
        });
        stopPeriodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopPeriod();

            }
        });



        return view;
    }

    private void stopPeriod()
    {
        periodIntent = new Intent(getContext(), PeriodService.class);
        //add end time at storage
        getActivity().stopService(periodIntent);
        saveAndRestore.setEndTime(-1);
        updateTime("");
    }

    private void checkLastState() {
        //if set period before then sho it
        long endTime = saveAndRestore.getPeriodEndTime();
        long currentTime = System.currentTimeMillis();
         if(currentTime <= endTime && endTime != -1) {
             periodCheckBox.setChecked(true);
         }
    }

    private void updateTime(String time)
    {
        remainingTime.setText(time);
    }

}
