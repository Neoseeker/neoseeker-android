package com.neoseeker.android;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class NeoEntry extends Activity {
	
    /**
     * Called whenever the Activity is started
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Anything needed to be done on entry
        this.startSetup();
    }
    
    private void startSetup() {
    	this.setUpOAuth();
    	//Intent neoMain = new Intent(this, NeoMain.class);
    	//startActivity(neoMain);
    }
    
    private void finishSetup() {
        // After entry stuff are all done... Forward to NeoMain
        Intent neoMain = new Intent(this, NeoMain.class);
        JSONObject meJSONObject = null;
		try {
			meJSONObject = new JSONObject(Neoseeker.API().makeRequest("members/me/"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
        Neoseeker.Me = new User(meJSONObject);
        startActivityForResult(neoMain, 0);
        finish();
    }
    
    /**
     * Sets up OAuth authentication, so the app can use the NeoAPI under the user,
     * with correct permissions to the account.
     */
    private void setUpOAuth() {    	
    	SharedPreferences sharedSettings = getSharedPreferences(Neoseeker.PREFS_NAME, 0);
        String accessToken = sharedSettings.getString("accessToken", "");
        String accessTokenSecret = sharedSettings.getString("accessTokenSecret", "");
        
        if (accessToken.equals("") && accessTokenSecret.equals("")) {        	
        	String authUrl = Neoseeker.API().getAuthenticationUrl();
        	
        	Intent webAuthentication = new Intent(this, OAuthCustomWeb.class);
        	webAuthentication.putExtra("authUrl", authUrl);
        	startActivityForResult(webAuthentication, 0);
        } else {
        	// Tokens already exist, meaning they can be used (hopefully)
        	Neoseeker.API().setAccessTokenWithSecret(accessToken, accessTokenSecret);
        	finishSetup();
        }
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == 0) {
    		finishSetup();
    	}
    }
}