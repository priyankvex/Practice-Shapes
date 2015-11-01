package com.wordpress.priyankvex.practiceshapes.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.wordpress.priyankvex.practiceshapes.Config;
import com.wordpress.priyankvex.practiceshapes.R;
import com.wordpress.priyankvex.practiceshapes.controller.SelectShapeController;
import com.wordpress.priyankvex.practiceshapes.controller.ShapesGridViewAdapter;
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
    private ShapesGridViewAdapter mAdapter;

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
        mAdapter = new ShapesGridViewAdapter(getApplicationContext(), mShapes);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SelectShapeActivity.this, DrawingActivity.class);
                i.putExtra("shapeId", mShapes.get(position).getId());
                startActivity(i);
            }
        });
    }



}
