package com.thedutch.technews;

import java.util.ArrayList;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private EasyTracker easyTracker = null;
	private Toolbar mToolbar;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private ListView mDrawerList;
	private ArrayList<ListMenuModel> mListMenu;
	private ListMenuAdapter mListMenuAdapter;
	final String[] data ={"Nutech","Tweakers","Hardware Info"};
    final String[] fragmentos ={
            "com.thedutch.technews.Nutech",
            "com.thedutch.technews.Tweakers",
            "com.thedutch.technews.Hardwareinfo"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		easyTracker = EasyTracker.getInstance(MainActivity.this);
		mToolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
		setSupportActionBar(mToolbar);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);		
		 mDrawerList = (ListView) findViewById(R.id.left_drawer);
		 mListMenu = new ArrayList<ListMenuModel>();
		 mListMenu.add(new ListMenuModel("Nutech", "Nutech", R.drawable.ic_nu));
		 mListMenu.add(new ListMenuModel("Tweakers", "Tweakers", R.drawable.ic_tweakers));
		 mListMenu.add(new ListMenuModel("Hardware Info", "Hardware Info", R.drawable.ic_hardwareinfo));
		 mListMenuAdapter = new ListMenuAdapter(getApplicationContext(),
					mListMenu);	
		 mDrawerList.setAdapter(mListMenuAdapter);	         
		 mDrawerList.setOnItemClickListener(new OnItemClickListener(){
             public void onItemClick(AdapterView<?> parent, View view, final int pos,long id){   
                            	 	mDrawerToggle.syncState();
                                     FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                                     tx.replace(R.id.content_frame, Fragment.instantiate(MainActivity.this, fragmentos[pos]));
                                     tx.commit();
                            		 mDrawerList.setSelection(pos);
                         	        easyTracker.send(MapBuilder.createEvent("Nav Drawer",
                        					"Opened Navigation Drawer", "Navigation Drawer", null).build());	
            	 mDrawerLayout.closeDrawer(mDrawerList);  
             }
     });
     FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
     tx.replace(R.id.content_frame,Fragment.instantiate(MainActivity.this, fragmentos[0]));
     tx.commit();         
		mDrawerToggle = new ActionBarDrawerToggle(
				this, 
				mDrawerLayout, 
				mToolbar, 
				R.string.drawer_open, 
				R.string.drawer_close){

			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				syncState();
			}
		};

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		mDrawerToggle.syncState();
		mToolbar.setClickable(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		  MenuInflater inflater = getMenuInflater();
		  inflater.inflate(R.menu.main, menu);
		  return true;
		}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch(item.getItemId()) {
	    case R.id.about:
	        Intent intent = new Intent(this, AboutActivity.class);
	        this.startActivity(intent);
	        easyTracker.send(MapBuilder.createEvent("AboutActivity",
					"Entered About Page", "about", null).build());	        
	        break;
	    default:
	        return super.onOptionsItemSelected(item);
	    }

	    return true;
	}
	
	public void setActionBarTitle(String title) {
	    getSupportActionBar().setTitle(title);
	}
	
	@Override
	public void onBackPressed() {
	    if(mDrawerLayout.isDrawerOpen(Gravity.START|Gravity.LEFT)){
	        mDrawerLayout.closeDrawers();
	        return;
	    }
	    super.onBackPressed();
	}
	
    public void fabClicked(View view) {
    	Toast.makeText(this, "Refreshed.", Toast.LENGTH_LONG).show();
    }
    
    @Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
	}    
}
