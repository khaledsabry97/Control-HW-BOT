package com.ashiaa.tanmo_app.Model;

import android.content.Context;

/**
 * Some constant to use
 */
public class Constants {
    public static final String URL = ""; // the url to the device
    SaveAndRestore saveAndRestore;

    public Constants(Context context)
    {
        saveAndRestore = new SaveAndRestore(context);

    }
    public String getUrl()
    {
        return "https://"+saveAndRestore.getIp()+"/json.php";
    }
}
