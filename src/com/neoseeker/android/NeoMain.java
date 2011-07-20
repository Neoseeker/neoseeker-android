package com.neoseeker.android;

import android.app.Dialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TabHost;

public class NeoMain extends TabActivity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.neomain);
        
        this.setUpTabs();
        //this.setUpOAuth(); 
    }
    
    private void setUpOAuth() {
    	
    	// Read up on http://donpark.org/blog/2009/01/24/android-client-side-oauth
    	
    	SharedPreferences sharedSettings = getSharedPreferences(Neoseeker.PREFS_NAME, 0);
        String accessToken = sharedSettings.getString("accessToken", "");
        String accessTokenSecret = sharedSettings.getString("accessTokenSecret", "");
        
        if (accessToken.equals("") && accessTokenSecret.equals("")) {
        	// No tokens set, must do primary authentication
        	String authUrl = Neoseeker.API().getAuthenticationUrl();
        	
        	Context mContext = getApplicationContext();
        	Dialog dialog = new Dialog(mContext);
        	
        	dialog.setContentView(R.layout.oauthcustomweb);
        	
        	WebView webView = (WebView)findViewById(R.id.OAuthWebView);
        	webView.loadUrl(authUrl);
        	
        	dialog.show();
        	// Whenever we get the accessToken and accessTokenSecret...
//        	SharedPreferences.Editor sharedSettingsEditor = sharedSettings.edit();
//        	sharedSettingsEditor.putString("accessToken", "");
//        	sharedSettingsEditor.putString("accessTokenSecret", "");
//        	sharedSettingsEditor.commit();
        } else {
        	// Tokens already exist, meaning they can be used (hopefully)
        	Neoseeker.API().setAccessToken(accessToken);
        	Neoseeker.API().setAccessTokenSecret(accessTokenSecret);
        }
    }
    
    private void setUpTabs() {
    	Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
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