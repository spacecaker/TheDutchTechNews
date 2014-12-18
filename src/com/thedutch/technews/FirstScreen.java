package com.thedutch.technews;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
 
public class FirstScreen extends Activity {
	private EasyTracker easyTracker = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        easyTracker = EasyTracker.getInstance(FirstScreen.this);
        setContentView(R.layout.splashy); 	 
        easyTracker.send(MapBuilder.createEvent("Starting app",
				"Times started app", "start app", null).build());	
        Thread background = new Thread() {
            public void run() {
                 
                try {        
                	 sleep(2*1150);
                    Intent i=new Intent(getBaseContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                     
                } catch (Exception e) {
                 
                }
            }
        };
        background.start();
         
    } 
     
    @Override
    protected void onDestroy() {
         
        super.onDestroy();
         
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