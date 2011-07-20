package com.neoseeker.android;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NotificationRecordAdapter extends ArrayAdapter<NotificationRecord> {
	private ArrayList<NotificationRecord> users;
	
	public NotificationRecordAdapter(Context context, int textViewResourceId, ArrayList<NotificationRecord> users) {
		super(context, textViewResourceId, users);
		this.users = users;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.notification_list_item, null);
		}
		
		NotificationRecord user = users.get(position);
		if (user != null) {
			TextView username = (TextView) v.findViewById(R.id.notification_username);
			ImageView avatar = (ImageView) v.findViewById(R.id.notification_avatar);
				
			if (username != null) {
				username.setText(user.username);
			}
			
			avatar.setImageBitmap(user.avatarBitmap);
		}
		
		return v;
	}
}
