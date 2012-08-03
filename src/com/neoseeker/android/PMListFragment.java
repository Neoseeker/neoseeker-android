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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        View view = inflater.inflate(R.layout.pmlistfragment, container, false);
        
        pmtext = (TextView)view.findViewById(R.id.PMText);        
        pmtext.setText("Hello there, " + Neoseeker.Me.getUsername());
		return view;
        
    }
}
