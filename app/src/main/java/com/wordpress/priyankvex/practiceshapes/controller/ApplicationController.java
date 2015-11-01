package com.wordpress.priyankvex.practiceshapes.controller;

import android.app.Application;
import android.content.SharedPreferences;

import com.wordpress.priyankvex.practiceshapes.R;
import com.wordpress.priyankvex.practiceshapes.model.Shape;

import java.util.ArrayList;

/**
 * Created by priyank on 1/11/15.
 * Application controller for the app.
 */
public class ApplicationController extends Application{

    public ArrayList<String> easyShapesPath;
    public static SharedPreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        mSharedPreferences = getApplicationContext().getSharedPreferences("prefs", MODE_PRIVATE);
        // Check is database has been initiated or not.
        if (PreferencesController.getDatabaseInitStatus()){
            // Load the bitmap paths
            easyShapesPath.add("drawable://" + R.drawable.circle);
            easyShapesPath.add("drawable://" + R.drawable.circle);
            easyShapesPath.add("drawable://" + R.drawable.circle);
            easyShapesPath.add("drawable://" + R.drawable.circle);
            easyShapesPath.add("drawable://" + R.drawable.circle);
            easyShapesPath.add("drawable://" + R.drawable.circle);
            // Write the value in the database.
            for (String bitmapPath : easyShapesPath){
                Shape shape = new Shape();
                shape.setBitmapPath(bitmapPath);
                shape.setMaxScore(0);
                shape.save();
            }
        }

    }
}
