package com.ashiaa.tanmo_app.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootCompletedIntentReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent pushIntent = new Intent(context, DailyService.class);
            context.startService(pushIntent);

            Intent periodIntent = new Intent(context, PeriodService.class);
            context.startService(periodIntent);

        }
    }
}
