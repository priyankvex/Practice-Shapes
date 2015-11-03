package com.wordpress.priyankvex.practiceshapes.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wordpress.priyankvex.practiceshapes.R;
import com.wordpress.priyankvex.practiceshapes.view.widgets.DrawingView;

import java.io.File;
import java.util.UUID;

/**
 * Created by priyank on 3/11/15.
 * Fragment to show the front side of the card.
 */
public class DrawingFragment extends Fragment implements View.OnClickListener{

    private DrawingView mDrawingView;
    private FloatingActionButton buttonReset;
    private FloatingActionButton buttonDone;
    private static final int REQUEST_WRITE_STORAGE = 112;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drawing, container, false);
        findViewsById(rootView);
        buttonDone.setOnClickListener(this);
        buttonReset.setOnClickListener(this);
        mDrawingView = (DrawingView) rootView.findViewById(R.id.drawingView);
        mDrawingView.drawOriginalShape(DrawingActivity.shape);
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_drawing, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save_bitmap){
            saveBitmapFromDrawingView();
        }
        return super.onOptionsItemSelected(item);
    }

    private void findViewsById(View view){
        buttonReset = (FloatingActionButton) view.findViewById(R.id.buttonReset);
        buttonDone = (FloatingActionButton) view.findViewById(R.id.buttonDone);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.buttonReset:
                mDrawingView.resetShape(DrawingActivity.shape);
                break;
            case R.id.buttonDone:
                // Evaluate trace here.
                Toast.makeText(getActivity(), mDrawingView.getScore() + "", Toast.LENGTH_SHORT).show();
                DrawingActivity.flipCard();
                break;
        }
    }

    private void saveBitmapFromDrawingView(){

        boolean hasPermission = (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission)
        {
            Toast.makeText(getActivity(), "Can't do that without your permission? Be kind!", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(getActivity(),
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
                    getActivity().getContentResolver(), mDrawingView.getDrawingCache(),
                    UUID.randomUUID().toString()+".png", "drawing");
            if(imgSaved!=null){
                Toast savedToast = Toast.makeText(getActivity(),
                        "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
                savedToast.show();
            }
            else{
                Toast unsavedToast = Toast.makeText(getActivity(),
                        "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
                unsavedToast.show();
            }
            // Destroy the current cache.
            mDrawingView.destroyDrawingCache();
        }

    }
}
