package edu.washington.prathh.quizdroid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends ActionBarActivity {
    private final String ACTIVITY = "MainActivity";
    private SharedPreferences.OnSharedPreferenceChangeListener listener;
    private String url;
    private int minutes;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        List<Topic> topics = TopicBuilder.getInstance().getTopicList();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> names = TopicBuilder.getInstance().getTitles();

        TextView mathButton = (TextView) findViewById(R.id.mathButton);
        mathButton.setText(names.get(1));
        TextView mathDescription = (TextView) findViewById(R.id.math_description);
        mathDescription.setText(topics.get(TopicBuilder.getInstance().getCurrentTopicIndex("Math")).getShortDescription());

        TextView physicsButton = (TextView) findViewById(R.id.physicsButton);
        physicsButton.setText(names.get(2));
        TextView physicsDescription = (TextView) findViewById(R.id.physics_description);
        physicsDescription.setText(topics.get(TopicBuilder.getInstance().getCurrentTopicIndex("Physics")).getShortDescription());

        TextView marvelButton = (TextView) findViewById(R.id.marvelButton);
        marvelButton.setText(names.get(3));
        TextView marvelDescription = (TextView) findViewById(R.id.marvel_description);
        marvelDescription.setText(topics.get(TopicBuilder.getInstance().getCurrentTopicIndex("Marvel")).getShortDescription());

        TextView puppyButton = (TextView) findViewById(R.id.puppyButton);
        puppyButton.setText(names.get(0));
        TextView puppyDescription = (TextView) findViewById(R.id.puppy_description);
        puppyDescription.setText(topics.get(TopicBuilder.getInstance().getCurrentTopicIndex("Puppies")).getShortDescription());

        Log.i(ACTIVITY, "App created");

        start();
    }

    public void start() {
        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Log.i("MainActivity", "URL: " + this.url + " minutes: " + this.minutes);
        int minutes = Integer.parseInt(
                PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getString("minute", "0"));
        manager.setRepeating(AlarmManager.RTC, System.currentTimeMillis(), (long) (minutes * 60 * 1000), pendingIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, PreferencePage.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void getSummary(View v) {
        Log.i(ACTIVITY, "Button click");
        Intent intent = new Intent(this, QuizAndAnswer.class);
        switch (v.getId()) {
            case R.id.mathButton:
                Log.i(ACTIVITY, "Math Button pushed");
                intent.putExtra("class", "Math");
                break;
            case R.id.physicsButton:
                Log.i(ACTIVITY, "Physics Button pushed");
                intent.putExtra("class", "Physics");
                break;
            case R.id.marvelButton:
                Log.i(ACTIVITY, "Marvel Button pushed");
                intent.putExtra("class", "Marvel");
                break;
            case R.id.puppyButton:
                Log.i(ACTIVITY, "Puppy Button pushed");
                intent.putExtra("class", "Puppies");
                break;
        };
        startActivity(intent);
    }
}
