package edu.washington.prathh.quizdroid;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

/**
 * Created by iguest on 2/16/15.
 */
public class QuizApp extends Application {
    private int minute;
    private PendingIntent pendingIntent;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("QuizApp", "QuizApp Application started");
        minute = 0;
        initSingletons();
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void start(int minutes) {
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Log.i("QuizApp", "minutes: " + minutes);
        manager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(),
                (long) (minutes * 60 * 1000), pendingIntent);
    }

    public void initSingletons() {
        TopicBuilder.initInstance();
    }
}
