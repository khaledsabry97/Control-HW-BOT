package com.ashiaa.tanmo_app.Controllers;

import android.app.Activity;
import android.content.Context;

import com.ashiaa.tanmo_app.Connections.SendRequest;
import com.ashiaa.tanmo_app.Model.SaveAndRestore;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * views contact with the sendController whenever they want to send a message or request
 */
public class SendController extends Controller {
    final static String userName = "user_name";
    final static String password = "password";
    final static String deviceId = "device_id";
    final static String switchs = "switch";
    final static String period = "period";
    Context activity;

    public SendController(Context activity) {
        this.activity = activity;
    }

    private void send(JSONObject jsonObject) {
        SendRequest sendRequest = new SendRequest(activity);
        sendRequest.send(jsonObject);
    }

    /**
     * send to the device to open
     */
    public void sendOn() {
        SaveAndRestore saveAndRestore = new SaveAndRestore(activity);
        JSONObject jsonObject = new JSONObject();
        try {
            //jsonObject.accumulate(userName,saveAndRestore.getUserName());
            jsonObject.accumulate(password, saveAndRestore.getPassWord());
            jsonObject.accumulate(deviceId, saveAndRestore.getDeviceId());
            jsonObject.accumulate(switchs, "on");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        send(jsonObject);
    }

    /**
     * send to the device to close
     */
    public void sendOff() {
        SaveAndRestore saveAndRestore = new SaveAndRestore(activity);
        JSONObject jsonObject = new JSONObject();
        try {
            //jsonObject.accumulate(userName,saveAndRestore.getUserName());
            jsonObject.accumulate(password, saveAndRestore.getPassWord());
            jsonObject.accumulate(deviceId, saveAndRestore.getDeviceId());
            jsonObject.accumulate(switchs, "off");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        send(jsonObject);
    }

    @Deprecated
    public void sendPeriod(int time) {
        SaveAndRestore saveAndRestore = new SaveAndRestore(activity);
        JSONObject jsonObject = new JSONObject();
        try {
            //jsonObject.accumulate(userName,saveAndRestore.getUserName());
            jsonObject.accumulate(password, saveAndRestore.getPassWord());
            jsonObject.accumulate(deviceId, saveAndRestore.getDeviceId());
            jsonObject.accumulate(switchs, "on");
            jsonObject.accumulate(period, time);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        send(jsonObject);
    }


}
