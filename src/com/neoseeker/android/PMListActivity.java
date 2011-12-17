package com.neoseeker.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PMListActivity extends Activity {
	
	private TextView pmtext;
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pmlistactivity);
        
        pmtext = (TextView)findViewById(R.id.PMText);        
        pmtext.setText(Neoseeker.Me.getUsername());
        
    }
}
