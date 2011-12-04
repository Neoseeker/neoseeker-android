package com.neoseeker.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class NeoEntry extends Activity {
	
	private static final String TAG = "NeoEntry";
	
    /**
     * Called whenever the Activity is started
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Anything needed to be done on entry
        this.setUpOAuth(); 
        
        // After entry stuff are all done... Forward to NeoMain
        Intent neoMain = new Intent(this, NeoMain.class);
        startActivity(neoMain);
        finish();
        
        // For debugging
        SharedPreferences sharedSettings = getSharedPreferences(Neoseeker.PREFS_NAME, 0);
    	SharedPreferences.Editor sharedSettingsEditor = sharedSettings.edit();
    	sharedSettingsEditor.putString("accessToken", "");
    	sharedSettingsEditor.putString("accessTokenSecret", "");
    	sharedSettingsEditor.commit();
    }
    
    /**
     * Sets up OAuth authentication, so the app can use the NeoAPI under the user,
     * with correct permissions to the account.
     */
    private void setUpOAuth() {
    	Log.v(TAG, "Entering setUpOAuth()");
    	
    	SharedPreferences sharedSettings = getSharedPreferences(Neoseeker.PREFS_NAME, 0);
        String accessToken = sharedSettings.getString("accessToken", "");
        String accessTokenSecret = sharedSettings.getString("accessTokenSecret", "");
        
        Log.v(TAG, "Saved accessToken: \"" + accessToken + "\"");
        Log.v(TAG, "Saved accessTokenSecret: \"" + accessTokenSecret + "\"");
        Toast.makeText(this, "Saved accessToken: \"" + accessToken + "\"", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Saved accessTokenSecret: \"" + accessTokenSecret + "\"", Toast.LENGTH_LONG).show();
        
        if (accessToken.equals("") && accessTokenSecret.equals("")) {
        	Log.v(TAG, "Must get authentication");
        	
        	String authUrl = Neoseeker.API().getAuthenticationUrl();
        	
        	Intent webAuthentication = new Intent(this, OAuthCustomWeb.class);
        	webAuthentication.putExtra("authUrl", authUrl);
        	startActivity(webAuthentication);
        } else {
        	Log.v(TAG, "Already authenticated");
        	// Tokens already exist, meaning they can be used (hopefully)
        	Neoseeker.API().setAccessTokenWithSecret(accessToken, accessTokenSecret);
        }
    }
}