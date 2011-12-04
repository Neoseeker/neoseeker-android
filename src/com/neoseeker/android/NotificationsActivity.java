package com.neoseeker.android;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NotificationsActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ArrayList<NotificationRecord> users = new ArrayList<NotificationRecord>();
        users.add(new NotificationRecord("user1"));
        users.add(new NotificationRecord("user2"));
        users.add(new NotificationRecord("user3"));
        users.add(new NotificationRecord("user4"));
        users.add(new NotificationRecord("user5"));
        users.add(new NotificationRecord("user6"));
        users.add(new NotificationRecord("user7"));
        users.add(new NotificationRecord("user8"));
        users.add(new NotificationRecord("user9"));
        users.add(new NotificationRecord("user10"));
        
        setListAdapter(new NotificationRecordAdapter(this, R.layout.notification_list_item, users));
        
        ListView lv = getListView();
        lv.setTextFilterEnabled(true);
        
        lv.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		Toast.makeText(getApplicationContext(), ((TextView)findViewById(R.id.notification_username)).getText(), Toast.LENGTH_SHORT).show();
        	}
        });
    }
}