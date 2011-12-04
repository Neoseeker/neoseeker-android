package com.neoseeker.android;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class NotificationRecord {
	Bitmap avatarBitmap;
	public String username;
	public String action_str;
	public String full_str;
	public String avatar_img;
	public boolean is_unread = false;
	
	public NotificationRecord(String username) {
		this.username = username;
		
		try {
		  this.avatarBitmap = BitmapFactory.decodeStream((InputStream)new URL("http://i.neoseeker.com/m/76710_photo.png").getContent());
		} catch (MalformedURLException e) {
		  e.printStackTrace();
		} catch (IOException e) {
		  e.printStackTrace();
		}
	}
}
