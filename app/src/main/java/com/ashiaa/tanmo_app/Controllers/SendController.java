package com.ashiaa.tanmo_app.Controllers;

import android.content.Context;

import com.ashiaa.tanmo_app.Connections.SendRequest;
import com.ashiaa.tanmo_app.Model.Constants;
import com.ashiaa.tanmo_app.Model.SaveAndRestore;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * views contact with the sendController whenever they want to send a message or request
 */
public class SendController extends Controller {
    final static String type = "type";
    final static String password = "password";
    final static String deviceId = "device_id";
    public final static String switchs = "switch";
    final static String checkState = "check_state";
    final static String sendOn = "send_on";
    final static String sendOff = "send_off";
    Context activity;
    Constants constants;

    public SendController(Context activity) {
        this.activity = activity;
        constants = new Constants(activity);
    }

    private void send(JSONObject jsonObject,String url) {
        SendRequest sendRequest = new SendRequest(activity);
        sendRequest.send(jsonObject,url);
    }

    /**
     * send to the device to open
     */
    public void sendOn() {
        SaveAndRestore saveAndRestore = new SaveAndRestore(activity);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate(type,sendOn);
            jsonObject.accumulate(password, saveAndRestore.getPassWord());
            jsonObject.accumulate(deviceId, saveAndRestore.getDeviceId());
            jsonObject.accumulate(switchs, "on");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        send(jsonObject,constants.getOnUrl());
    }

    /**
     * send to the device to close
     */
    public void sendOff() {
        SaveAndRestore saveAndRestore = new SaveAndRestore(activity);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate(type,sendOff);
            jsonObject.accumulate(password, saveAndRestore.getPassWord());
            jsonObject.accumulate(deviceId, saveAndRestore.getDeviceId());
            jsonObject.accumulate(switchs, "off");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        send(jsonObject,constants.getOffUrl());
    }

    public void sendCheckStatus() {
        SaveAndRestore saveAndRestore = new SaveAndRestore(activity);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate(type, checkState);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        send(jsonObject,constants.getStatusUrl());
    }



}
