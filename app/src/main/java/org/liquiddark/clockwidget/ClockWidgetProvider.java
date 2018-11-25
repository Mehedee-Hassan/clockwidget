package org.liquiddark.clockwidget;

import android.appwidget.AppWidgetProvider;
import android.content.Intent;
import android.content.IntentFilter;

public class ClockWidgetProvider extends AppWidgetProvider {
    private static final IntentFilter intentFilter;
    private static final String ACTION_UPDATE =
            "mr.UPDATE";
    static {
        intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        intentFilter.addAction(Intent.ACTION_TIME_CHANGED);
        intentFilter.addAction(ACTION_UPDATE);
    }

}
