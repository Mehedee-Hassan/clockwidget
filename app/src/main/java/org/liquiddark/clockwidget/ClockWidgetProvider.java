package org.liquiddark.clockwidget;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Date;

public class ClockWidgetProvider extends AppWidgetProvider {

    private static final String ACTION_UPDATE ="org.liquiddark.clock.UPDATE";


    private void updateTime(Context context) {
        Date d = new Date();
        CharSequence widgetTextDate  = DateFormat.format("d MMM", (d).getTime());
        CharSequence widgetTextYear  = DateFormat.format("yyyy ", (d).getTime());
        CharSequence widgetTextMin = DateFormat.format("mm", (d).getTime());
        CharSequence widgetTextHour  = DateFormat.format("hh", (d).getTime());




        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.main);
        views.setTextViewText(R.id.datetext, widgetTextDate);
        views.setTextViewText(R.id.yeartext, widgetTextYear);
        views.setTextViewText(R.id.timeHourText, widgetTextHour);
        views.setTextViewText(R.id.timeMinText, widgetTextMin);

        ComponentName clockWidget = new ComponentName(context,
                ClockWidgetProvider.class);

        AppWidgetManager appWidgetManager = AppWidgetManager.
                getInstance(context);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(clockWidget, views);
    }



    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {


    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them


        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

        updateTime(context);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created

        super.onEnabled(context);
        AppWidgetAlarm appWidgetAlarm = new AppWidgetAlarm(context.getApplicationContext());
        appWidgetAlarm.startAlarm();
        updateTime(context);


    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        super.onDisabled(context);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisAppWidgetComponentName = new ComponentName(context.getPackageName(),getClass().getName());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidgetComponentName);

        if (appWidgetIds.length == 0) {
            // stop alarm
            AppWidgetAlarm appWidgetAlarm = new AppWidgetAlarm(context.getApplicationContext());
            appWidgetAlarm.stopAlarm();
        }
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);


        Log.d("**", "onReceive: 1");
        if(intent.getAction().equals(ACTION_UPDATE))
        {
            Log.d("**2", "onReceive: 1");

            updateTime(context);
        }
    }
}


