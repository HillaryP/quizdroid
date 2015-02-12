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
            Fragment fragment = new OverviewFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(R.id.container, fragment);
            transaction.commit();

            Log.i("QuizAndAnswer.class", "Fragment and associated activity: "
                    + fragment);

            Bundle args = new Bundle();
            args.putString("title", className + " Quiz");
            args.putString("description", mapping.get(className));

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

            Button button = (Button) findViewById(R.id.button);
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("GenericOverview", "Button clicked");
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.container, new QuizFragment())
                            .commit();
                }
            };
            button.setOnClickListener(listener);
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
        TextView titleText;
        TextView description;

        public OverviewFragment() {
            titleText = (TextView) super.getView().findViewById(R.id.textView);
            description = (TextView) super.getView().findViewById(R.id.textView2);
        }

        @Override
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            if (getArguments() != null) {
                titleText.setText(getArguments().getString("title"));
                description.setText(getArguments().getString("description"));
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_overview, container, false);
            return rootView;
        }
    }
}
