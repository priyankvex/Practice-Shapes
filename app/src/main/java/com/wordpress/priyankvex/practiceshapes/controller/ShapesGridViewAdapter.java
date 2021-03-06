package com.wordpress.priyankvex.practiceshapes.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wordpress.priyankvex.practiceshapes.R;
import com.wordpress.priyankvex.practiceshapes.model.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by priyank on 1/11/15.
 * Adapter for shapes grid view.
 */
public final class ShapesGridViewAdapter extends BaseAdapter {

    private final Context mContext;
    private List<Shape> mShapes = new ArrayList<>();
    private LayoutInflater inflater;
    private int mScreenWidth;
    private DisplayImageOptions mOptions;

    public ShapesGridViewAdapter(Context context, List<Shape> shapes){

        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.mShapes = shapes;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        mScreenWidth = size.x;
        mOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.loading)
                .showImageOnFail(R.drawable.loading)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        final Shape shape = mShapes.get(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_shapes_gridview, parent, false);
            viewHolder.imageViewShape = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.imageViewShape.setLayoutParams(new RelativeLayout.LayoutParams(mScreenWidth/2 - 10, mScreenWidth/2 - 10));
            viewHolder.imageViewStar = (ImageView) convertView.findViewById(R.id.imageViewStar);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ImageLoader.getInstance().displayImage("drawable://" + shape.getResourceId(), viewHolder.imageViewShape, mOptions);

        if (shape.getMaxScore() != 100){
            viewHolder.imageViewStar.setVisibility(View.INVISIBLE);
        }

        return convertView;

    }

    @Override
    public int getCount() {
        return mShapes.size();
    }

    @Override
    public Shape getItem(int position) {
        return mShapes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        ImageView imageViewShape;
        ImageView imageViewStar;
    }


}