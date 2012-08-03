package com.neoseeker.android;

import oauth.signpost.OAuth;
import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class OAuthCustomWeb extends Activity {

	private static final String TAG = "OAuthCustomWeb";
	private WebView mainWebView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oauthcustomweb);
        
        // Get the authentication URL sent with the Intent
        String authUrl = getIntent().getExtras().getString("authUrl");
        Log.v(TAG, "authUrl: \"" + authUrl + "\"");
        
        this.mainWebView = (WebView) findViewById(R.id.OAuthWebView);
        this.mainWebView.setWebViewClient(new CustomWebViewClient());
        this.mainWebView.loadUrl(authUrl);
        
    }
    
    private class CustomWebViewClient extends WebViewClient {
    	@Override
    	public boolean shouldOverrideUrlLoading(WebView view, String url) {
    		if (url.substring(0, 9).equals("neoseeker")) {
    			Uri uri = Uri.parse(url);
    			final String oauth_verifier = uri.getQueryParameter(OAuth.OAUTH_VERIFIER);
            	try {
					Neoseeker.API().authenticate(oauth_verifier);
                	// Whenever we get the accessToken and accessTokenSecret...
        	    	SharedPreferences sharedSettings = getSharedPreferences(Neoseeker.PREFS_NAME, 0);
                	SharedPreferences.Editor sharedSettingsEditor = sharedSettings.edit();
                	sharedSettingsEditor.putString("accessToken", Neoseeker.API().getAccessToken());
                	sharedSettingsEditor.putString("accessTokenSecret", Neoseeker.API().getAccessTokenSecret());
                	sharedSettingsEditor.commit();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            	finish();
    		} else {
        		view.loadUrl(url);
    		}
    		return true;
    	}
    }
}
