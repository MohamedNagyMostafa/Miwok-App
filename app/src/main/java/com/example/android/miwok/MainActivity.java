package com.example.android.miwok;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by mohamed nagy on 8/21/2016.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        ViewPager pager = (ViewPager) findViewById(R.id.viewpager);

        SampleFragmentAdapterPager adapter =
                new SampleFragmentAdapterPager(getSupportFragmentManager());

        pager.setAdapter(adapter);
    }

        // Object from Listener class in which The OnClickListener interface
        // is implements ....

        // new object without name ...
}

