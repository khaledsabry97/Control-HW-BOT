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
        //String url = getUrl() +"/on.php";
        String url = "http://www.mocky.io/v2/5d6f61ca3100000d0066054f";
        return url;
    }

    public String getOffUrl()
    {
        //String url = getUrl() +"/off.php";
        String url = "http://www.mocky.io/v2/5d6fc5d431000033a4660901";
        return url;
    }

    public String getStatusUrl()
    {
        //String url = getUrl() +"/status.php";
        String url = "http://www.mocky.io/v2/5d6fc65c31000037a4660906";
        return url;
    }
}
