package com.ashiaa.tanmo_app.Views;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashiaa.tanmo_app.R;


public class ScheduleFragment extends Fragment {

    public static ScheduleFragment newInstance() {
        ScheduleFragment fragment = new ScheduleFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

}
