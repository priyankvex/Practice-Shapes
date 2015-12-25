package com.wordpress.priyankvex.practiceshapes.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.priyankvex.practiceshapes.Config;
import com.wordpress.priyankvex.practiceshapes.R;
import com.wordpress.priyankvex.practiceshapes.controller.PreferencesController;

/**
 * Created by priyank on 23/12/15.
 * Activity to provide settings for the app
 */
public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textViewClearData;
    View layoutVibrationSetting;
    CheckBox checkBoxVibration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        // finding views
        textViewClearData = (TextView) findViewById(R.id.textViewClearData);
        layoutVibrationSetting = findViewById(R.id.layoutVibrationSetting);
        checkBoxVibration = (CheckBox) findViewById(R.id.checkBoxVibration);
        textViewClearData.setOnClickListener(this);
        layoutVibrationSetting.setOnClickListener(this);
        // Get the vibration setting
        boolean status = PreferencesController.getVibrationPreference();
        checkBoxVibration.setChecked(status);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.textViewClearData:
                showConfirmationDialog();
                break;
            case R.id.layoutVibrationSetting:
                checkBoxVibration.toggle();
                boolean status = checkBoxVibration.isChecked();
                PreferencesController.setVibrationPreference(status);
                break;
        }
    }

    private void showConfirmationDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Are You Sure?")
                .setMessage("This will delete all data including scores and high scores.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Reset app data here
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }
}
