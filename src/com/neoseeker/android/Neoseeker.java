package com.neoseeker.android;

import android.app.Application;

public class Neoseeker extends Application {
	public static final String PREFS_NAME = "NeoseekerPrefs";
	
	private static String consumerKey 			= "48f9f74808404b41117c2a47368c4a";
	private static String consumerSecret 		= "a4680bc19b";
	
	private static NeoAPI neoAPI = new NeoAPI(Neoseeker.consumerKey, Neoseeker.consumerSecret);
	
	public static NeoAPI API() { return Neoseeker.neoAPI; }
	
}
