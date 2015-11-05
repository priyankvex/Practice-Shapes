package com.wordpress.priyankvex.practiceshapes.controller;

import android.content.SharedPreferences;

import com.orm.SugarApp;
import com.wordpress.priyankvex.practiceshapes.Config;
import com.wordpress.priyankvex.practiceshapes.R;
import com.wordpress.priyankvex.practiceshapes.model.Shape;

import java.util.ArrayList;

/**
 * Created by priyank on 1/11/15.
 * Application controller for the app.
 */
public class ApplicationController extends SugarApp{

    public ArrayList<Integer> easyShapesIds;
    public static SharedPreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        mSharedPreferences = getApplicationContext().getSharedPreferences("prefs", MODE_PRIVATE);
        // Check is database has been initiated or not.
        if (true){
            // Load the bitmap paths
            easyShapesIds = new ArrayList<>();
            easyShapesIds.add(R.drawable.circle);
            easyShapesIds.add(R.drawable.triangle);
            easyShapesIds.add(R.drawable.oval);
            easyShapesIds.add(R.drawable.circle);
            easyShapesIds.add(R.drawable.circle);
            easyShapesIds.add(R.drawable.circle);
            // Write the value in the database.
            Shape.deleteAll(Shape.class);
            for (Integer resourceId : easyShapesIds){
                Shape shape = new Shape();
                shape.setMaxScore(0);
                shape.setLevel(Config.LEVEL_EASY);
                shape.setResourceId(resourceId);
                shape.save();
            }
            PreferencesController.setDatabaseInitStatus(true);
        }

    }
}
