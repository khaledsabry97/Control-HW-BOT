package com.ashiaa.tanmo_app.Views;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ashiaa.tanmo_app.Model.SaveAndRestore;
import com.ashiaa.tanmo_app.R;
import com.xgc1986.ripplebutton.widget.RippleButton;


public class ConfigurationFragment extends Fragment {
    EditText ip,deviceId,password;
    RippleButton save;
    SaveAndRestore saveAndRestore;

    public static ConfigurationFragment newInstance() {
        ConfigurationFragment fragment = new ConfigurationFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_configuration, container, false);
        ip = view.findViewById(R.id.ip);
        deviceId = view.findViewById(R.id.device_id);
        password = view.findViewById(R.id.password);
        save = view.findViewById(R.id.save);

        saveAndRestore = new SaveAndRestore(getContext());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });






        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        getData();

    }

    private void save()
    {

        saveAndRestore.saveCredentials(ip.getText().toString(),password.getText().toString(),deviceId.getText().toString());

        Toast.makeText(getContext(),"تم الحفظ بنجاح",Toast.LENGTH_SHORT).show();

    }

    private void getData()
    {
        ip.setText(saveAndRestore.getIp());
        deviceId.setText(saveAndRestore.getDeviceId());
        password.setText(saveAndRestore.getPassWord());


    }

}
