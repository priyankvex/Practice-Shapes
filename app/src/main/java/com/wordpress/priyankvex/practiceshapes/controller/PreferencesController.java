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


}
