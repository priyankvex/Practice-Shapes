package com.wordpress.priyankvex.practiceshapes.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wordpress.priyankvex.practiceshapes.Config;
import com.wordpress.priyankvex.practiceshapes.R;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    FancyButton buttonEasy;
    FancyButton buttonMedium;
    FancyButton buttonHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewsById();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void findViewsById(){
        buttonEasy = (FancyButton) findViewById(R.id.buttonEasyLevel);
        buttonMedium = (FancyButton) findViewById(R.id.buttonMediumLevel);
        buttonHard = (FancyButton) findViewById(R.id.buttonHardLevel);
        buttonEasy.setOnClickListener(this);
        buttonMedium.setOnClickListener(this);
        buttonHard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent i = new Intent(MainActivity.this, SelectShapeActivity.class);
        switch (id){
            case R.id.buttonEasyLevel:
                i.putExtra(Config.KEY_LEVEL, Config.LEVEL_EASY);
                break;
            case R.id.buttonMediumLevel:
                i.putExtra(Config.KEY_LEVEL, Config.LEVEL_MEDIUM);
                break;
            case R.id.buttonHardLevel:
                i.putExtra(Config.KEY_LEVEL, Config.LEVEL_HARD);
                break;
        }
        startActivity(i);
    }
}
