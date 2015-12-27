package com.wordpress.priyankvex.practiceshapes.controller;

import com.wordpress.priyankvex.practiceshapes.Config;

/**
 * Created by priyank on 1/11/15.
 * Controller to manage all calls to SharedPreferences.
 */
public class PreferencesController {

    /**
     * Returns whether database has been initiated or not.
     * @return boolean true or false
     */
    public static boolean getDatabaseInitStatus(){
        return ApplicationController.mSharedPreferences.getBoolean(Config.KEY_DATABASE_INIT, false);
    }

    /**
     * Sets database init value in SharedPreferences
     * @param status boolean value true or false.
     */
    public static void setDatabaseInitStatus(boolean status){
        ApplicationController.mSharedPreferences.edit().putBoolean(Config.KEY_DATABASE_INIT, status).apply();
    }

    /**
     * Sets vibration preference
     * @param status boolean status for vibration
     */
    public static void setVibrationPreference(boolean status){
        ApplicationController.mSharedPreferences.edit().putBoolean(Config.KEY_VIBRATION_PREFERENCE, status).apply();
    }

    /**
     * Returns vibration setting of the user
     * @return boolean
     */
    public static boolean getVibrationPreference(){
        return ApplicationController.mSharedPreferences.getBoolean(Config.KEY_VIBRATION_PREFERENCE, true);
    }

}
