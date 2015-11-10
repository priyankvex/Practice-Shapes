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
    public ArrayList<Integer> mediumShapesIds;
    public ArrayList<Integer> hardShapesIds;
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
            easyShapesIds.add(R.drawable.square);
            easyShapesIds.add(R.drawable.rectangle);
            easyShapesIds.add(R.drawable.diamond);
            // Write the value in the database.
            Shape.deleteAll(Shape.class);
            for (Integer resourceId : easyShapesIds){
                Shape shape = new Shape();
                shape.setMaxScore(0);
                shape.setLevel(Config.LEVEL_EASY);
                shape.setResourceId(resourceId);
                shape.save();
            }
            mediumShapesIds = new ArrayList<>();
            mediumShapesIds.add(R.drawable.pentagon);
            mediumShapesIds.add(R.drawable.hexagon);
            mediumShapesIds.add(R.drawable.star);
            mediumShapesIds.add(R.drawable.heart);
            mediumShapesIds.add(R.drawable.crescent);
            mediumShapesIds.add(R.drawable.lightning);
            for(Integer resourceId : mediumShapesIds){
                Shape shape = new Shape();
                shape.setMaxScore(0);
                shape.setLevel(Config.LEVEL_MEDIUM);
                shape.setResourceId(resourceId);
                shape.save();
            }
            hardShapesIds = new ArrayList<>();
            hardShapesIds.add(R.drawable.bell);
            hardShapesIds.add(R.drawable.bulb);
            hardShapesIds.add(R.drawable.cloud);
            hardShapesIds.add(R.drawable.flower);
            hardShapesIds.add(R.drawable.house);
            hardShapesIds.add(R.drawable.gear);
            for (Integer resourceId : hardShapesIds){
                Shape shape = new Shape();
                shape.setMaxScore(0);
                shape.setLevel(Config.LEVEL_HARD);
                shape.setResourceId(resourceId);
                shape.save();
            }
            PreferencesController.setDatabaseInitStatus(true);
        }

    }
}
