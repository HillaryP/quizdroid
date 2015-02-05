package edu.washington.prathh.quizdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


public class GenericOverview extends ActionBarActivity {
    private Map<String, String> mapping;
    private static Class classFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_overview);

        mapping = new HashMap<String, String>();
        setUpMapping(mapping);
        String className = getIntent().getStringExtra("class");
        TextView view = (TextView) findViewById(R.id.textView);
        view.setText(className + " Quiz");
        TextView view2 = (TextView) findViewById(R.id.textView2);
        view2.setText(mapping.get(className));

        switch (className) {
            case "Math":
                classFile = MathQuiz.class;
                break;
            case "Physics":
                classFile = PhysicsQuiz.class;
                break;
            case "Marvel":
                classFile = MarvelQuiz.class;
                break;
            case "Puppies":
                classFile = PuppyQuiz.class;
                break;
        }

        Button button = (Button) findViewById(R.id.button);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("GenericOverview", "Button clicked");
                Intent intent = new Intent(GenericOverview.this, GenericOverview.classFile);
                startActivity(intent);
            }
        };
        button.setOnClickListener(listener);
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
        getMenuInflater().inflate(R.menu.menu_generic_summary, menu);
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
