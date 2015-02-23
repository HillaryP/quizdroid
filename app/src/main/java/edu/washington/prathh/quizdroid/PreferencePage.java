package edu.washington.prathh.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class PreferencePage extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_page);
    }

    public void okay(View v) {
        EditText url = (EditText) findViewById(R.id.url);
        EditText hours = (EditText) findViewById(R.id.hours);
        EditText minutes = (EditText) findViewById(R.id.minutes);

        String urlToSet = url.getText().toString();
        int minutesToSet = (Integer.parseInt(hours.getText().toString()) * 60)
                              + Integer.parseInt(minutes.getText().toString());

        Log.i("PreferencePage", "URL: " + urlToSet + " Minutes: " + minutesToSet);
        ((QuizApp) this.getApplication()).setURL(urlToSet);
        ((QuizApp) this.getApplication()).setMinutes(minutesToSet);
        Log.i("PreferencePage", "URL in app: " + ((QuizApp) this.getApplication()).getURL()
                + " Minutes in app: " + ((QuizApp) this.getApplication()).getMinutes());

        Intent intent = new Intent(PreferencePage.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preference_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
