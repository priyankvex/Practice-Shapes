package com.wordpress.priyankvex.practiceshapes.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.wordpress.priyankvex.practiceshapes.R;
import com.wordpress.priyankvex.practiceshapes.view.widgets.DrawingView;

/**
 * Created by priyank on 1/11/15.
 * Activity on which user traces on the shape.
 */
public class DrawingActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        DrawingView drawingView = (DrawingView) findViewById(R.id.drawingView);
        drawingView.drawOriginalShape(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_drawing, menu);

        return true;
    }
}
