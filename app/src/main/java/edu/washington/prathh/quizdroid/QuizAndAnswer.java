package edu.washington.prathh.quizdroid;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


public class QuizAndAnswer extends ActionBarActivity {
    private Map<String, String> mapping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_and_answer);
        mapping = new HashMap<>();
        setUpMapping(mapping);
        String className = getIntent().getStringExtra("class");

        if (savedInstanceState == null) {
            OverviewFragment overview = new OverviewFragment();
            Log.i("QuizAndAnswer.class", "Fragment and associated activity: "
                    + overview);
            Bundle args = new Bundle();
            args.putString("title", className + " Quiz");
            args.putString("description", mapping.get(className));
            overview.setArguments(args);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, overview)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();

            switch (className) {
                case "Math":
                    break;
                case "Physics":
                    break;
                case "Marvel":
                    break;
                case "Puppies":
                    break;
            }
        }
    }

    public void setUpMapping(Map<String, String> mapping) {
        mapping.put("Math", "Want to learn more about math?\n" +
                "Look no further than this quiz! Test your skills and maybe learn a thing or two.");
        mapping.put("Physics", "Want to learn more about physics?\n" +
                "Look no further than this quiz! Test your skills and maybe learn a thing or two.");
        mapping.put("Marvel", "Think you're a hero?\n" +
                "Look no further than this quiz! Test your skills and maybe learn a thing or two.");
        mapping.put("Puppies", "Who doesn't love a quiz about puppies?\n" +
                "Look no further than this quiz! Test your knowledge and maybe learn a thing or two.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz_and_answer, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class QuizFragment extends Fragment {

        public QuizFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_quiz_and_answer, container, false);
            return rootView;
        }
    }

    public static class OverviewFragment extends Fragment {
        String titleText;
        String description;

        public OverviewFragment() {
            Log.i("OverviewFragment", "Constructor called");
        }

        public static OverviewFragment newInstance(String title, String description) {
            OverviewFragment f = new OverviewFragment();
            Log.i("OverviewFragment", "NewInstance called");

            // Supply index input as an argument.
            Bundle args = new Bundle();
            args.putString("title", title);
            args.putString("description", description);
            f.setArguments(args);

            return f;
        }

        @Override
        public void onCreate(Bundle bundle) {
            Log.i("OverviewFragment", "OnCreate called");
            super.onCreate(bundle);
            if (getArguments() != null) {
                titleText = getArguments().getString("title");
                description = getArguments().getString("description");
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Log.i("OverviewFragment", "OnCreateView called");
            View rootView = inflater.inflate(R.layout.fragment_overview, container, false);
            TextView titleText = (TextView) rootView.findViewById(R.id.textView);
            titleText.setText(this.titleText);
            TextView description = (TextView) rootView.findViewById(R.id.textView2);
            description.setText(this.description);

            Button button = (Button) rootView.findViewById(R.id.button);
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("GenericOverview", "Button clicked");
                    //getSupportFragmentManager().beginTransaction()
                    //        .add(R.id.container, new QuizFragment())
                    //        .commit();
                }
            };
            button.setOnClickListener(listener);
            return rootView;
        }
    }
}
