package com.neoseeker.android;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.Toast;

public class NeoMain extends TabActivity {
	
    /**
     * Called whenever the Activity is started
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neomain);
        
        this.setUpTabs();
    }
    
    /**
     * Setup the TabHost, and add in the necessary Tabs
     */
    private void setUpTabs() {
    	Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Reusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, PMListActivity.class);

        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("pms").setIndicator("PM's",
                          res.getDrawable(R.drawable.ic_tab_pm))
                      .setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs
        intent = new Intent().setClass(this, NotificationsActivity.class);
        spec = tabHost.newTabSpec("notifications").setIndicator("Notifications",
                          res.getDrawable(R.drawable.ic_tab_notification))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }
    
    /**
     * Setup the main menu, for whenever the user hits the menu button on the phone
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.main_menu, menu);
    	return true;
    }
    
    /**
     * What should be done for each menu item pressed
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.menu_logout:
    		MenuLogout();
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
    /**
     * Helper method for what happens when you logout
     */
    private void MenuLogout() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Are you sure you want to logout?");
    	builder.setCancelable(false);
    	builder.setPositiveButton("Yes", new OnClickListener() {
    		public void onClick(DialogInterface dialog, int id) {
    	        SharedPreferences sharedSettings = getSharedPreferences(Neoseeker.PREFS_NAME, 0);
    	    	SharedPreferences.Editor sharedSettingsEditor = sharedSettings.edit();
    	    	sharedSettingsEditor.putString("accessToken", "");
    	    	sharedSettingsEditor.putString("accessTokenSecret", "");
    	    	sharedSettingsEditor.commit();
    			Toast.makeText(NeoMain.this, "Logout", Toast.LENGTH_SHORT).show();
    			Intent neoMainIntent = new Intent(NeoMain.this, NeoEntry.class);
    			NeoMain.this.startActivity(neoMainIntent);
    		}
    	});
    	builder.setNegativeButton("No", new OnClickListener() {
    		public void onClick(DialogInterface dialog, int id) {
    			dialog.cancel();
    		}
    	});
    	AlertDialog alert = builder.create();
    	alert.show();
    }
}