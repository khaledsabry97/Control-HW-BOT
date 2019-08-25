package com.ashiaa.tanmo_app.Views;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ashiaa.tanmo_app.Model.SaveAndRestore;
import com.ashiaa.tanmo_app.R;

import java.util.ArrayList;


public class ScheduleFragment extends Fragment {
    TimePicker startTime, periodTime;
    CheckBox sat, sun, mon, tue, wed, thr, fri;
    Button save;
    TextView info;


    SaveAndRestore saveAndRestore;

    public static ScheduleFragment newInstance() {
        ScheduleFragment fragment = new ScheduleFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
            }
        });

    }


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

        if(days.size() <0)
            return;

        inf +="سيعمل الجهاز يوم ";
        inf += days.get(0);
        for(int i = 1;i<days.size();i++)
        {
            inf += " و ";
            inf += days.get(i);
        }
        inf += "\nالساعة ";
        int h = saveAndRestore.getDailyClockHour();
        int m = saveAndRestore.getDailyClockMin();
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
        checkLastState();
    }

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

