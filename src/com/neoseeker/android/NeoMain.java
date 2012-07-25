package com.neoseeker.android;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class NeoMain extends SherlockFragmentActivity {
	
	ViewPager mViewPager;
	TabsAdapter mTabsAdapter;
	TextView tabCenter;
	TextView tabText;
	
    /**
     * Called whenever the Activity is started
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewpager);
        
        setContentView(mViewPager);
        ActionBar bar = getSupportActionBar();
        //bar.setDisplayHomeAsUpEnabled(true);
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        mTabsAdapter = new TabsAdapter(this, mViewPager);

        mTabsAdapter.addTab(bar.newTab().setText("Private Messages"), PMListFragment.class, null);
        mTabsAdapter.addTab(bar.newTab().setText("Notifications"), NotificationsFragment.class, null);

    }
    
    public static class TabsAdapter extends FragmentPagerAdapter implements
    ActionBar.TabListener, ViewPager.OnPageChangeListener {
		private final Context mContext;
	    private final com.actionbarsherlock.app.ActionBar mActionBar;
	    private final ViewPager mViewPager;
	    private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

	    static final class TabInfo {
	        private final Class<?> clss;
	        private final Bundle args;

	        TabInfo(Class<?> _class, Bundle _args) {
	            clss = _class;
	            args = _args;
	        }
	    }

	    public TabsAdapter(SherlockFragmentActivity activity, ViewPager pager) {
	        super(activity.getSupportFragmentManager());
	        mContext = activity;
	        mActionBar = activity.getSupportActionBar();
	        mViewPager = pager;
	        mViewPager.setAdapter(this);
	        mViewPager.setOnPageChangeListener(this);
	    }

	    public void addTab(ActionBar.Tab tab, Class<?> clss, Bundle args) {
	        TabInfo info = new TabInfo(clss, args);
	        tab.setTag(info);
	        tab.setTabListener(this);
	        mTabs.add(info);
	        mActionBar.addTab(tab);
	        notifyDataSetChanged();
	    }

	    @Override
	    public int getCount() {
	        return mTabs.size();
	    }

	    @Override
	    public android.support.v4.app.Fragment getItem(int position) {
	        TabInfo info = mTabs.get(position);
	        return android.support.v4.app.Fragment.instantiate(mContext, info.clss.getName(),
	                info.args);
	    }

	    //@Override
	    public void onPageScrolled(int position, float positionOffset,
	            int positionOffsetPixels) {
	    }

	    //@Override
	    public void onPageSelected(int position) {
	        mActionBar.setSelectedNavigationItem(position);
	    }

	    //@Override
	    public void onPageScrollStateChanged(int state) {
	    }

		//@Override
		public void onTabSelected(Tab tab, android.support.v4.app.FragmentTransaction ft) {
	        Object tag = tab.getTag();
	        for (int i = 0; i < mTabs.size(); i++) {
	            if (mTabs.get(i) == tag) {
	                mViewPager.setCurrentItem(i);
	            }
	        }
			
		}

		//@Override
		public void onTabUnselected(Tab tab, android.support.v4.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		//@Override
		public void onTabReselected(Tab tab, android.support.v4.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
	}
    
//    /**
//     * Setup the main menu, for whenever the user hits the menu button on the phone
//     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//    	MenuInflater inflater = getMenuInflater();
//    	inflater.inflate(R.menu.main_menu, menu);
//    	return true;
//    }
//    
//    /**
//     * What should be done for each menu item pressed
//     */
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//    	switch (item.getItemId()) {
//    	case R.id.menu_logout:
//    		MenuLogout();
//    		return true;
//    	default:
//    		return super.onOptionsItemSelected(item);
//    	}
//    }
    
    /**
     * Helper method for what happens when you logout
     */
    private void MenuLogout() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Are you sure you want to logout?");
    	builder.setCancelable(false);
    	builder.setPositiveButton("Yes", new OnClickListener() {
    		public void onClick(DialogInterface dialog, int id) {
    	        SharedPreferences sharedSettings = getSharedPreferences(Neoseeker.PREFS_NAME, 0);
    	    	SharedPreferences.Editor sharedSettingsEditor = sharedSettings.edit();
    	    	sharedSettingsEditor.putString("accessToken", "");
    	    	sharedSettingsEditor.putString("accessTokenSecret", "");
    	    	sharedSettingsEditor.commit();
    			Toast.makeText(NeoMain.this, "Logout", Toast.LENGTH_SHORT).show();
    			Intent neoMainIntent = new Intent(NeoMain.this, NeoEntry.class);
    			NeoMain.this.startActivity(neoMainIntent);
    		}
    	});
    	builder.setNegativeButton("No", new OnClickListener() {
    		public void onClick(DialogInterface dialog, int id) {
    			dialog.cancel();
    		}
    	});
    	AlertDialog alert = builder.create();
    	alert.show();
    }
}