package edu.washington.prathh.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {
    private final String ACTIVITY = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(ACTIVITY, "App created");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void getSummary(View v) {
        Log.i(ACTIVITY, "Button click");
        Intent intent;
        switch (v.getId()) {
            case R.id.mathButton:
                Log.i(ACTIVITY, "Math Button pushed");
                intent = new Intent(this, MathOverview.class);
                startActivity(intent);
                break;
            case R.id.physicsButton:
                Log.i(ACTIVITY, "Physics Button pushed");
                intent = new Intent(this, PhysicsOverview.class);
                startActivity(intent);
                break;
            case R.id.marvelButton:
                Log.i(ACTIVITY, "Marvel Button pushed");
                intent = new Intent(this, MarvelOverview.class);
                startActivity(intent);
                break;
            case R.id.puppyButton:
                Log.i(ACTIVITY, "Puppy Button pushed");
                intent = new Intent(this, PuppyOverview.class);
                startActivity(intent);
                break;
        };
    }
}
