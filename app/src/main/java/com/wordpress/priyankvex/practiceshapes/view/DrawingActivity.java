package com.wordpress.priyankvex.practiceshapes.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.wordpress.priyankvex.practiceshapes.R;
import com.wordpress.priyankvex.practiceshapes.model.Shape;
import com.wordpress.priyankvex.practiceshapes.view.widgets.DrawingView;

import java.io.File;
import java.util.UUID;

/**
 * Created by priyank on 1/11/15.
 * Activity on which user traces on the shape.
 */
public class DrawingActivity extends AppCompatActivity implements View.OnClickListener{

    private Shape mShape;
    private DrawingView mDrawingView;
    private FloatingActionButton buttonReset;
    private FloatingActionButton buttonDone;
    private static final int REQUEST_WRITE_STORAGE = 112;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save_bitmap){
            saveBitmapFromDrawingView();
        }
        return super.onOptionsItemSelected(item);
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

    private void saveBitmapFromDrawingView(){

        boolean hasPermission = (ContextCompat.checkSelfPermission(DrawingActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission)
        {
            Toast.makeText(getApplicationContext(), "Can't do that without your permission? Be kind!", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(DrawingActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }
        else{
            File sdCardDirectory = new File(Environment.getExternalStorageDirectory() + File.separator + "PracticeShapes");
            if (!sdCardDirectory.exists()){
                sdCardDirectory.mkdir();
            }
            mDrawingView.setDrawingCacheEnabled(true);
            String imgSaved = MediaStore.Images.Media.insertImage(
                    getContentResolver(), mDrawingView.getDrawingCache(),
                    UUID.randomUUID().toString()+".png", "drawing");
            if(imgSaved!=null){
                Toast savedToast = Toast.makeText(getApplicationContext(),
                        "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                savedToast.show();
            }
            else{
                Toast unsavedToast = Toast.makeText(getApplicationContext(),
                        "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
                unsavedToast.show();
            }
            // Destroy the current cache.
            mDrawingView.destroyDrawingCache();
        }

    }

}
