package com.neoseeker.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PMListFragment extends Fragment {
	
	private TextView pmtext;
	
	 /** Called when the activity is first created. */
    public View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        pmtext = (TextView)getView().findViewById(R.id.PMText);        
        //pmtext.setText(Neoseeker.Me.getUsername());
        pmtext.setText("Your name here");
		return inflater.inflate(R.layout.pmlistactivity, container, false);
        
    }
}
