package edu.washington.prathh.quizdroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        ((QuizApp) getApplication()).start(Integer.parseInt(prefs.getString("minute", "0")));

        if (!isOnline()) {
            dialog();
        }
    }

    private boolean isOnline() {
        try {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((wifiInfo != null && wifiInfo.isConnected()) || (mobileInfo != null && mobileInfo.isConnected())) {
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            Log.e("MainActivity", "Exception when getting ConnectivityManager: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isOnline()) {
            dialog();
        }
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

    public void dialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You don't appear to be connected to the Internet.");
        alertDialogBuilder.setPositiveButton("Check settings",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setClassName("com.android.settings", "com.android.settings.wifi.WifiSettings");
                        startActivity(intent);
                    }
                });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
