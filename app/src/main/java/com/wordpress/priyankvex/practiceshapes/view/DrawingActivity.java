package com.wordpress.priyankvex.practiceshapes.view;

import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.wordpress.priyankvex.practiceshapes.R;
import com.wordpress.priyankvex.practiceshapes.model.Shape;

/**
 * Created by priyank on 1/11/15.
 * Activity on which user traces on the shape.
 */
public class DrawingActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{

    public static Shape shape;
    public static boolean mShowingBack = false;
    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);
        long shapeId = getIntent().getLongExtra("shapeId", -1);
        shape = Shape.findById(Shape.class, shapeId);
        fragmentManager = getFragmentManager();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.container, new DrawingFragment())
                .commit();
        mShowingBack = false;
        getFragmentManager().addOnBackStackChangedListener(this);
    }

    public static void flipCard() {
        if (mShowingBack) {
            fragmentManager.popBackStack();
            return;
        }
        // Flip to the back.
        mShowingBack = true;
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                        R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in, R.animator.card_flip_left_out)
                .replace(R.id.container, new ScoreFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackStackChanged() {
        mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
    }
}
