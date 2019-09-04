package com.ashiaa.tanmo_app.Model;

import android.content.Context;

/**
 * Some constant to use
 */
public class Constants {
    public static final String URL = ""; // the url to the device
    public static boolean onButtonState = true;
    SaveAndRestore saveAndRestore;

    public Constants(Context context)
    {
        saveAndRestore = new SaveAndRestore(context);

    }
    private String getUrl()
    {
        return "https://"+saveAndRestore.getIp();
    }
    public String getOnUrl()
    {
        String url = getUrl() +"/on.php";
        return url;
    }

    public String getOffUrl()
    {
        String url = getUrl() +"/off.php";
        return url;
    }

    public String getStatusUrl()
    {
        String url = getUrl() +"/status.php";
        return url;
    }
}
