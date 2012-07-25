package com.neoseeker.android;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NotificationsFragment extends ListFragment {
	
	private final String[] stuff = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	
    public View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
//        ArrayList<NotificationRecord> users = new ArrayList<NotificationRecord>();
//        users.add(new NotificationRecord("user1"));
//        users.add(new NotificationRecord("user2"));
//        users.add(new NotificationRecord("user3"));
//        users.add(new NotificationRecord("user4"));
//        users.add(new NotificationRecord("user5"));
//        users.add(new NotificationRecord("user6"));
//        users.add(new NotificationRecord("user7"));
//        users.add(new NotificationRecord("user8"));
//        users.add(new NotificationRecord("user9"));
//        users.add(new NotificationRecord("user10"));
//        
//        setListAdapter(new NotificationRecordAdapter(getActivity(), R.layout.notification_list_item, users));
//        
//        ListView lv = getListView();
//        lv.setTextFilterEnabled(true);
//        
//        lv.setOnItemClickListener(new OnItemClickListener() {
//        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        		Toast.makeText(getActivity(), ((TextView)getView().findViewById(R.id.notification_username)).getText(), Toast.LENGTH_SHORT).show();
//        	}
//        });
        
        ListAdapter myListAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, stuff);
        setListAdapter(myListAdapter);

		return inflater.inflate(R.layout.notificationactivity, container, false);
    }
    
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	Toast.makeText(getActivity(), getListView().getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
    }
}