package com.neoseeker.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PMListActivity extends Activity {
	
	private TextView pmtext;
	private Button pmtest;
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pmlistactivity);
        
        pmtext = (TextView)findViewById(R.id.PMText);
        pmtest = (Button)findViewById(R.id.PMTest);
        
        pmtest.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        		String result = "hmm";
        		try {
					result = Neoseeker.API().makeRequest("members/me/");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					result = "failed";
					e.printStackTrace();
				}
        		pmtext.setText(result);
        	}
        });
    }
}
