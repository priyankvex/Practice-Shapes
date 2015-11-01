package com.wordpress.priyankvex.practiceshapes.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.wordpress.priyankvex.practiceshapes.Config;
import com.wordpress.priyankvex.practiceshapes.R;
import com.wordpress.priyankvex.practiceshapes.controller.SelectShapeController;
import com.wordpress.priyankvex.practiceshapes.model.Shape;

import java.util.ArrayList;

/**
 * Created by priyank on 31/10/15.
 * Activity to select shape that has to be traced.
 */
public class SelectShapeActivity extends AppCompatActivity {

    private String mShapeLevel;
    private ArrayList<Shape> mShapes;
    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_shape);
        mGridView = (GridView) findViewById(R.id.gridViewShapes);
        //noinspection ConstantConditions
        getSupportActionBar().setTitle("Select Shape");
        // Get the shape level
        mShapeLevel = Config.LEVEL_EASY;
        mShapes = SelectShapeController.getShapesFromLevel(mShapeLevel);
    }



}
