package edu.washington.prathh.quizdroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Hillary on 2/23/15.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String showString = PreferenceManager.getDefaultSharedPreferences(context).getString("url", "");
        Log.i("AlarmReceiver", "Alarm Received");
        Toast.makeText(context, showString, Toast.LENGTH_LONG).show();
    }
}