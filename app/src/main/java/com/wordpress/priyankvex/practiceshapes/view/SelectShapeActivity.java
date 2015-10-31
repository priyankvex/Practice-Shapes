package com.wordpress.priyankvex.practiceshapes.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wordpress.priyankvex.practiceshapes.R;

/**
 * Created by priyank on 31/10/15.
 * Activity to select shape that has to be traced.
 */
public class SelectShapeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_shape);
        getSupportActionBar().setTitle("Select Shape");
    }
}
