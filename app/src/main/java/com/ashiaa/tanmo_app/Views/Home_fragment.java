package com.ashiaa.tanmo_app.Views;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TimePicker;

import com.ashiaa.tanmo_app.Controllers.SendController;
import com.ashiaa.tanmo_app.R;
import com.xgc1986.ripplebutton.widget.RippleButton;


public class Home_fragment extends Fragment {
    ViewGroup periodViewGroup;
    Button startButton,stopButton,startPeriodButton, stopPeriodButton;
    CheckBox periodCheckBox;
    TimePicker timePicker;
    SendController sendController;

    public Home_fragment() {
        // Required empty public constructor
    }


    public static Home_fragment newInstance() {
        Home_fragment fragment = new Home_fragment();
        return fragment;
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

                }

            }
        });

        startPeriodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add end time at storage
            }
        });
        stopPeriodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add end time at storage
            }
        });


        checkLastState();

        return view;
    }

    private void checkLastState() {
        //if set period before then sho it
    }

}
