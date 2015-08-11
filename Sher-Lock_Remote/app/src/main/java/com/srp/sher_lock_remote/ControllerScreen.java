package com.srp.sher_lock_remote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

/**
 * Created by rishi on 3/8/15.
 */
public class ControllerScreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controller);
        TabHost tabHost=(TabHost)findViewById(android.R.id.tabhost);
        TabHost.TabSpec tab1=tabHost.newTabSpec("Mouse");
        TabHost.TabSpec tab2=tabHost.newTabSpec("Pro Controls");
        TabHost.TabSpec tab3=tabHost.newTabSpec("Key Board");
        TabHost.TabSpec tab4=tabHost.newTabSpec("Screen Cast");

        tab1.setIndicator("Mouse");
        tab1.setContent(new Intent(this,MouseController.class));

        tab2.setIndicator("Pro Controls");
        tab2.setContent(new Intent(this,MainActivity.class));

        tab3.setIndicator("Key Board");
        tab3.setContent(new Intent(this,KeyboardController.class));


        tab4.setIndicator("Screen Cast");
        tab4.setContent(new Intent(this,KeyboardController.class));


        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
        tabHost.addTab(tab4);

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
        // int id = item.getItemId();
        /*
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        */
        //return super.onOptionsItemSelected(item);
       /* super.onOptionsItemSelected(item);
        switch(item.getItemId())
        {
            case R.id.more_options:
                Toast.makeText(getBaseContext(), "you seleted more options", Toast.LENGTH_LONG).show();
                break;
        }*/
        return true;
    }
}
