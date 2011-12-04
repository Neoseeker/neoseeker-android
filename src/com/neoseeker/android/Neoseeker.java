package com.neoseeker.android;

import android.app.Application;

public class Neoseeker extends Application {
	public static final String PREFS_NAME = "NeoseekerPrefs";
	
	private static String consumerKey 			= "c260b1ca7cba2527157b2acee4c85d";
	private static String consumerSecret 		= "3dc2305ffd";
	
	private static NeoAPI neoAPI = new NeoAPI(Neoseeker.consumerKey, Neoseeker.consumerSecret);
	
	public static NeoAPI API() { return Neoseeker.neoAPI; }
	
}
