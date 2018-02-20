package com.example.sowmyaram.analyticsdemoproject;

/**
 * Created by sowmyaram on 10/23/2017.
 */

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class NextScreen extends AppCompatActivity {

    private static final String TAG = NextScreen.class.getSimpleName();
    private Tracker mTracker;
    private String name = "Next Screen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_screen);
        Button bEvent1 = (Button) findViewById(R.id.second_event);
        //Analytics Integration
        // Obtain the shared Tracker instance.
        GoogleAnalyticsApplication application = (GoogleAnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bEvent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get tracker.
                Tracker t = ((GoogleAnalyticsApplication) getApplication()).getDefaultTracker();

                // Build and send an Event.
                t.send(new HitBuilders.EventBuilder()
                        .setCategory(getString(R.string.categoryId1))
                        .setAction(getString(R.string.actionId1))
                        .setLabel(getString(R.string.labelId1))
                        .build());
                Toast.makeText(NextScreen.this, "Event is recorded. Check Google Analytics!", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Setting screen name: " + name);
        mTracker.setScreenName(name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

}