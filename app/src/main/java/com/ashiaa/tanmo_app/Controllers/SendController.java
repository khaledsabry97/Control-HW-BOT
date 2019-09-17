/**
 * @file SendController.java
 *
 * @brief file for class controller that will prepare sent data and call SendRequest object.
 */

//folder package contain Controller.
package com.ashiaa.tanmo_app.Controllers;


// Interface to global information about an application environment. This is an abstract class
// whose implementation is provided by the Android system. It allows access to
// application-specific resources and classes, as well as up-calls for application-level
// operations such as launching activities, broadcasting and receiving intents, etc.
import android.content.Context;

//local basic http request file to handle http connection.
import com.ashiaa.tanmo_app.Connections.SendRequest;

//local class that will keep static values for the app.
import com.ashiaa.tanmo_app.Model.Constants;

//local class to save general app data.
import com.ashiaa.tanmo_app.Model.SaveAndRestore;

//Thrown to indicate a problem with the JSON API.
import org.json.JSONException;

//A modifiable set of name/value mappings.
import org.json.JSONObject;




/**
 * @class SendController
 *
 * @brief class controller that will prepare sent data and call SendRequest object.
 *
 * @details contact with the sendController whenever they want to send a message or request.
 */
public class SendController extends Controller {    // class SendController start.

    final static String type = "type";
    final static String password = "password";  // attribute : password login info.
    final static String deviceId = "device_id"; // attribute : Device ID login info.
    public final static String switchs = "switch";  // attribute : device status.
    final static String checkState = "check_state";
    final static String sendOn = "send_on";
    final static String sendOff = "send_off";
    Context activity;   //define variable "activity" of type Context.
    Constants constants;



    /**
     * @function public SendController(Context activity)
     *
     * @brief class constructor that receive activity parameter of type Context.
     *
     * @param activity : parameter of type Context.
     */
    public SendController(Context activity) {

        // assign class attribute "activity" to activity parameter of constructor function.
        this.activity = activity;

        constants = new Constants(activity);
    }



    /**
     * @function private void send(JSONObject jsonObject,String url)
     *
     * @brief function to pass JSON object to SendRequest object.
     *
     * @param jsonObject
     * @param url
     */
    private void send(JSONObject jsonObject,String url) {

        SendRequest sendRequest = new SendRequest(activity);    // create SendRequest object.

        // use created "sendRequest" object to send function parameter "jsonObject".
        sendRequest.send(jsonObject,url);
    }




    /**
     * @function public void sendOn()
     *
     * @brief function to send ON status.
     *
     * @details send to the device to open.
     */
    public void sendOn() {
        //create SaveAndRestore object called "saveAndRestore" and pass "activity" class attribute to it.
        SaveAndRestore saveAndRestore = new SaveAndRestore(activity);

        //create JSONObject object called "jsonObject".
        JSONObject jsonObject = new JSONObject();

        //fill in "jsonObject" with required data for switch ON.
        try {
            jsonObject.accumulate(type,sendOn);
            jsonObject.accumulate(password, saveAndRestore.getPassWord());
            jsonObject.accumulate(deviceId, saveAndRestore.getDeviceId());
            jsonObject.accumulate(switchs, "on");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //use send function to send prepared JSON object for ON status.
        send(jsonObject,constants.getOnUrl());
    }



    /**
     * @function public void sendOff()
     *
     * @brief function to send OFF status.
     *
     * @details send to the device to close.
     */
    public void sendOff() {
        //create SaveAndRestore object called "saveAndRestore" and pass "activity" class attribute to it.
        SaveAndRestore saveAndRestore = new SaveAndRestore(activity);

        //create JSONObject object called "jsonObject".
        JSONObject jsonObject = new JSONObject();

        //fill in "jsonObject" with required data for switch OFF.
        try {
            jsonObject.accumulate(type,sendOff);
            jsonObject.accumulate(password, saveAndRestore.getPassWord());
            jsonObject.accumulate(deviceId, saveAndRestore.getDeviceId());
            jsonObject.accumulate(switchs, "off");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //use send function to send prepared JSON object for OFF status.
        send(jsonObject,constants.getOffUrl());
    }



    /**
     * @function public void sendCheckStatus()
     *
     * @brief function to ckeck status of device.
     */
    public void sendCheckStatus() {
        //create SaveAndRestore object called "saveAndRestore" and pass "activity" class attribute to it.
        SaveAndRestore saveAndRestore = new SaveAndRestore(activity);

        //create JSONObject object called "jsonObject".
        JSONObject jsonObject = new JSONObject();

        //fill in "jsonObject" with required data for check status.
        try {
            jsonObject.accumulate(type, checkState);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //use send function to send prepared JSON object for OFF status.
        send(jsonObject,constants.getStatusUrl());
    }



}   /** class SendController end.*/
