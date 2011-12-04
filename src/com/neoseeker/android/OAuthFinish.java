package com.neoseeker.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class OAuthFinish extends Activity {

    TextView textview = new TextView(this);
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        textview.setText("Hola");
        setContentView(textview);
    }
    
    @Override
    public void onNewIntent(Intent intent) {
    	super.onNewIntent(intent);
    	final Uri uri = intent.getData();
    	textview.setText(uri.toString());
    }
}
