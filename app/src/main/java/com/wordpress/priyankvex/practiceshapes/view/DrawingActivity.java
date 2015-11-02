package com.wordpress.priyankvex.practiceshapes.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.wordpress.priyankvex.practiceshapes.R;
import com.wordpress.priyankvex.practiceshapes.model.Shape;
import com.wordpress.priyankvex.practiceshapes.view.widgets.DrawingView;

/**
 * Created by priyank on 1/11/15.
 * Activity on which user traces on the shape.
 */
public class DrawingActivity extends AppCompatActivity implements View.OnClickListener{

    private Shape mShape;
    private DrawingView mDrawingView;
    private FloatingActionButton buttonReset;
    private FloatingActionButton buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        findViewsById();
        buttonDone.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
        long shapeId = getIntent().getLongExtra("shapeId", -1);
        mShape = Shape.findById(Shape.class, shapeId);
        mDrawingView = (DrawingView) findViewById(R.id.drawingView);
        mDrawingView.drawOriginalShape(mShape);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_drawing, menu);
        return true;
    }

    private void findViewsById(){
        buttonReset = (FloatingActionButton) findViewById(R.id.buttonReset);
        buttonDone = (FloatingActionButton) findViewById(R.id.buttonDone);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.buttonReset:
                mDrawingView.resetShape(mShape);
                break;
            case R.id.buttonDone:
                // Evaluate trace here.
                Toast.makeText(getApplicationContext(), mDrawingView.getScore()+"", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
