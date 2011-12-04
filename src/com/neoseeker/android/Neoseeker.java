package com.neoseeker.android;

import android.app.Application;

public class Neoseeker extends Application {
	public static final String PREFS_NAME = "NeoseekerPrefs";
	
	private static String consumerKey 			= "";
	private static String consumerSecret 		= "";
	
	private static NeoAPI neoAPI = new NeoAPI(Neoseeker.consumerKey, Neoseeker.consumerSecret);
	
	public static NeoAPI API() { return Neoseeker.neoAPI; }
	
}
