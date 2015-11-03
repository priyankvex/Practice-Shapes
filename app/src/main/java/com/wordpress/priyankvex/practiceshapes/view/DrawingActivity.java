package com.wordpress.priyankvex.practiceshapes.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wordpress.priyankvex.practiceshapes.R;
import com.wordpress.priyankvex.practiceshapes.model.Shape;

/**
 * Created by priyank on 1/11/15.
 * Activity on which user traces on the shape.
 */
public class DrawingActivity extends AppCompatActivity{

    public static Shape shape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        long shapeId = getIntent().getLongExtra("shapeId", -1);
        shape = Shape.findById(Shape.class, shapeId);
    }
}
