package com.neoseeker.android;

import android.app.Application;

public class Neoseeker extends Application {
	public static final String PREFS_NAME = "NeoseekerPrefs";
	
	private static String consumerKey 			= "64e008663d61ea899f90a5e05e16fc";
	private static String consumerSecret 		= "ec1007fc62";
	
	private static NeoAPI neoAPI = new NeoAPI(Neoseeker.consumerKey, Neoseeker.consumerSecret);
	
	public static NeoAPI API() { return Neoseeker.neoAPI; }
	
	public static User Me = null;
	
}
