package com.example.sowmyaram.analyticsdemoproject;

import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Tracker mTracker;
    String name = new String("Main Screen");
    static Boolean toggle = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Analytics Integration
        // Obtain the shared Tracker instance.
        GoogleAnalyticsApplication application = (GoogleAnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        Button bNextScreen = (Button) findViewById(R.id.bNextScreen);

        Button bEvent = (Button) findViewById(R.id.bEvent);

        //Starts Next Activity/Screen
        bNextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NextScreen.class);
                startActivity(i);
            }
        });

        bEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get tracker.
                Tracker t = ((GoogleAnalyticsApplication) getApplication()).getDefaultTracker();

                // Build and send an Event.
                t.send(new HitBuilders.EventBuilder()
                        .setCategory(getString(R.string.categoryId))
                        .setAction(getString(R.string.actionId))
                        .setLabel(getString(R.string.labelId))
                        .build());
                Toast.makeText(MainActivity.this, "Event is recorded. Check Google Analytics!", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Setting screen name: " + name);
        //using tracker variable to set Screen Name
        mTracker.setScreenName(name);
        //sending the screen to analytics using ScreenViewBuilder() method
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}