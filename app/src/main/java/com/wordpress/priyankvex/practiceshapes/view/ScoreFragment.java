package com.wordpress.priyankvex.practiceshapes.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wordpress.priyankvex.practiceshapes.R;

/**
 * Created by priyank on 3/11/15.
 * Fragment to display the backside of the card.
 */
public class ScoreFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_score, container, false);
        return rootView;
    }
}
