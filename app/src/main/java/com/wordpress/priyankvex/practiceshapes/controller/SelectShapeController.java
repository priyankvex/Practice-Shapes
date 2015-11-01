package com.wordpress.priyankvex.practiceshapes.controller;

import com.wordpress.priyankvex.practiceshapes.model.Shape;

import java.util.ArrayList;

/**
 * Created by priyank on 1/11/15.
 * Controller for SelectShapeActivity.
 */
public class SelectShapeController {

    public static ArrayList<Shape> getShapesFromLevel(String shapeLevel){
        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.addAll(Shape.find(Shape.class, "level = ?", shapeLevel));
        return shapes;
    }
}
