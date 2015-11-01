package com.wordpress.priyankvex.practiceshapes.model;

import com.orm.SugarRecord;

/**
 * Created by priyank on 1/11/15.
 * Model for Shape.
 */
public class Shape extends SugarRecord<Shape>{

    private String bitmapPath;
    private float maxScore;

    public Shape(){}


    public String getBitmapPath() {
        return bitmapPath;
    }

    public void setBitmapPath(String bitmapPath) {
        this.bitmapPath = bitmapPath;
    }

    public float getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(float maxScore) {
        this.maxScore = maxScore;
    }
}
