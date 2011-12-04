package com.neoseeker.android;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

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
}