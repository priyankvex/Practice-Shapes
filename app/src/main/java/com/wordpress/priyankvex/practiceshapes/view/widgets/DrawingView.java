package com.wordpress.priyankvex.practiceshapes.view.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.wordpress.priyankvex.practiceshapes.Config;
import com.wordpress.priyankvex.practiceshapes.R;
import com.wordpress.priyankvex.practiceshapes.controller.PreferencesController;
import com.wordpress.priyankvex.practiceshapes.model.Shape;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by priyank on 1/11/15.
 * Custom view on which drawing takes place.
 */
public class DrawingView extends View {

    /**
     * The drawing path
     */
    private Path mDrawPath;

    /**
     * The paint used to draw the touches
     */
    private Paint mDrawPaint;

    private Paint mCanvasPaint;  //The paints used to draw on the canvas
    /**
     * The canvas of the view
     */
    private Canvas mDrawCanvas;

    /**
     * The bitmap that is set
     */
    private Bitmap mCanvasBitmap;

    /**
     * Vibrator instance to vibrate if the user traces outside the boundary of the string
     */
    private Vibrator mVibrator;

    /**
     * number of wrong touches
     */
    private long mWrongTouches;

    /**
     * number of correct touches
     */
    private long mCorrectTouches;

    /**
     * Boolean variable that enables drawing
     */
    private boolean mDraw;

    /**
     * Boolean variable that enables vibration
     */
    private boolean mVibrate;

    /**
     * Boolean variables that enables scoring
     */
    private boolean mScoring;

    /**
     * The start time of the vibration
     */
    private long mVibrationStartTime;

    /**
     * bounds of the touches
     */
    private int minX, minY, maxX, maxY;

    /**
     * List of strokes. Each ArrayList<Point> is the touches from one MOTION_DOWN even to a MOTION_UP even
     */
    private ArrayList<ArrayList<Point>> mTouchPoints;

    /**
     * The context of the view
     */
    private Context mContext;

    /**
     * View width
     */
    public int mWidth;

    /**
     * View height
     */
    public int mHeight;

    public DrawingView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
        isInEditMode();
    }

    /**
     * Simple constructor to use when creating a view from code.
     * @param context The Context the view is running in, through which it can access the current theme, resources, etc.
     */
    public DrawingView(Context context) {
        super(context);
        init(context);
        isInEditMode();
    }

    /**
     * Function to initialize all the variables of the class
     * @param context The Context the view is running in, through which it can access the current theme, resources, etc.
     */
    private void init(Context context) {
        //context based init goes here
        mContext = context;
        mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        //Getting display width and height
        mWidth = getResources().getDisplayMetrics().widthPixels;
        mHeight = getResources().getDisplayMetrics().heightPixels;

        //initializing without context
        init();
    }

    /**
     *  Function to initialize all the variables of the class that do not require a context
     */
    public void init() {
        //get drawing area setup for interaction
        int mTouchColour = getResources().getColor(R.color.colorAccent);

        mDrawPath = new Path();
        mDrawPaint = new Paint();
        mDrawPaint.setColor(mTouchColour);
        mDrawPaint.setAntiAlias(true);
        mDrawPaint.setStrokeWidth(8);
        //Setting the paint to draw round strokes
        mDrawPaint.setStyle(Paint.Style.STROKE);
        mDrawPaint.setStrokeJoin(Paint.Join.ROUND);
        mDrawPaint.setStrokeCap(Paint.Cap.ROUND);
        mCanvasPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCorrectTouches = 0;
        mWrongTouches = 0;
        mDraw = true;
        mVibrationStartTime = 0;

        minX = mWidth;
        maxX = -1;
        minY = mHeight;
        maxY = -1;

        mScoring = true;

        mVibrate = PreferencesController.getVibrationPreference();
        Log.d(Config.TAG, mVibrate + "");

        mTouchPoints = new ArrayList<>(); //Empty list as no touches yet

        System.gc();
        if(mCanvasBitmap!=null) {
            mCanvasBitmap.recycle();
            mCanvasBitmap = null;
            mDrawCanvas = null;
        }
        mCanvasBitmap = Bitmap.createBitmap(mWidth,mHeight, Bitmap.Config.ARGB_4444);
        mDrawCanvas = new Canvas(mCanvasBitmap);
    }


    // Will be used from MainActivity to set the base shape.
    public void drawOriginalShape(Shape shape){
        init();
        Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintText.setColor(Color.BLACK);
        paintText.setStyle(Paint.Style.FILL);
        TypedValue tv = new TypedValue();
        mContext.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
        int actionBarHeight = getResources().getDimensionPixelSize(tv.resourceId);
        Bitmap icon = BitmapFactory.decodeResource(mContext.getResources(),
                shape.getResourceId());
        // Scales down bitmap ti fit in the width
        Bitmap bitmap = scaleDown(icon, mWidth*3/4, true);
        float height = mHeight - actionBarHeight;
        float heightOffset = height/2 - (mWidth*4/8);
        mDrawCanvas.drawBitmap(bitmap, mWidth/8, heightOffset, null);
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw view
        canvas.drawBitmap(mCanvasBitmap, 0, 0, mCanvasPaint);
        canvas.drawPath(mDrawPath, mDrawPaint);
    }


    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        if(mDraw) {//Draws the touches only if mDraw is true

            //detect user touch
            float touchX = event.getX();
            float touchY = event.getY();

            // mapping screen touch co-ordinates to image pixel co-ordinates
            // TODO : Log the values of both widths and heights and compare.
            int x = (int) (touchX * mCanvasBitmap.getWidth() / mWidth);
            int y = (int) (touchY * mCanvasBitmap.getHeight() / mHeight);
            Log.d(Config.TAG, x + " " + touchX + " y " + y + " " + touchY);

            //updating the touch bounds
            if (x < minX)
                minX = x;
            if (x > maxX)
                maxX = x;
            if (y < minY)
                minY = y;
            if (y > maxY)
                maxY = y;
            if(mScoring) {
                //checking if the touches are correct or wrong (inside or outside the boundary
                if ((x >= 0 && x < mWidth && y >= 0 && y < mHeight && mCanvasBitmap.getPixel(x, y) == Color.TRANSPARENT) || (x < 0 || x >= mWidth || y < 0 || y >= mHeight)) {
                    mWrongTouches++;
                    if (mVibrate) {//Device will vibrate only if mVibrate is true
                        mVibrator.vibrate(100);
                        if (mVibrationStartTime == 0) {
                            mVibrationStartTime = new Date().getTime();
                        }
                    }
                } else {
                    mVibrationStartTime = 0;
                    mCorrectTouches++;
                }
            }

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mDrawPath.moveTo(touchX, touchY);
                    mTouchPoints.add(new ArrayList<Point>());//ACTION_DOWN event means a new stroke so a new ArrayList
                    break;
                case MotionEvent.ACTION_MOVE:
                    mDrawPath.lineTo(touchX, touchY);
                    break;
                case MotionEvent.ACTION_UP:
                    mVibrationStartTime = 0;
                    mDrawCanvas.drawPath(mDrawPath, mDrawPaint);
                    mDrawPath.reset();//End of the current stroke
                    break;
                default:
                    return false;
            }
            //Adding the touch point to the last ArrayList
            mTouchPoints.get(mTouchPoints.size() - 1).add(new Point((int) touchX, (int) touchY));
            invalidate();
            return true;
        }
        return false;
    }


    public Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                            boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

    /**
     * Resets the canvas. Draws the shape again removing all the drawing.
     * @param shape Shape object to be drawn on fresh canvas.
     */
    public void resetShape(Shape shape){
        mDrawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
        drawOriginalShape(shape);
    }

    /**
     * Function to obtain the Score of the current trace
     * @return Score of the current trace
     */
    public float getScore() {
        return (mCorrectTouches + mWrongTouches !=0)?100* mCorrectTouches /(mCorrectTouches + mWrongTouches):0;
    }

}
