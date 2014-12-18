package com.thedutch.technews;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AboutActivity extends ActionBarActivity {
	private Toolbar mToolbar;
	TextView sendmediaemail;
	TextView sendmediaweb;
	
	TextView nuemail;
	TextView nuweb;
	
	TextView tweakersemail;
	TextView tweakersweb;
	
	TextView hwiemail;
	TextView hwiweb;
	
	TextView rbemail;
	TextView rbweb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		mToolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
		setSupportActionBar(mToolbar);
		mToolbar.setClickable(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		
		/// Emailllsss
		sendmediaemail = (TextView)this.findViewById(R.id.textView4);
		sendmediaemail.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
        	   Intent emailIntent;
        	   emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        	   emailIntent.setType("plain/text");
        	   emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"info@sendmedia.co.id"});
        	   emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Contact SendMedia.");
        	   startActivity(Intent.createChooser(emailIntent, "Contact SendMedia."));        	   
           }
       });
		
		nuemail = (TextView)this.findViewById(R.id.textViewnu4);
		nuemail.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
        	   Intent emailIntent;
        	   emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        	   emailIntent.setType("plain/text");
        	   emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"redactie@nu.nl"});
        	   emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Contact Nu.nl.");
        	   startActivity(Intent.createChooser(emailIntent, "Contact Nu.nl."));        	   
           }
       });
		
		tweakersemail = (TextView)this.findViewById(R.id.textViewtw4);
		tweakersemail.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
        	   Intent emailIntentt;
        	   emailIntentt = new Intent(android.content.Intent.ACTION_SEND);
        	   emailIntentt.setType("plain/text");
        	   emailIntentt.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"redactie@tweakers.net"});
        	   emailIntentt.putExtra(android.content.Intent.EXTRA_SUBJECT, "Contact Tweakers.net.");
        	   startActivity(Intent.createChooser(emailIntentt, "Contact Tweakers.net."));        	   
           }
       });
		
		hwiemail = (TextView)this.findViewById(R.id.textViewhw4);
		hwiemail.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
        	   Intent emailIntenth;
        	   emailIntenth = new Intent(android.content.Intent.ACTION_SEND);
        	   emailIntenth.setType("plain/text");
        	   emailIntenth.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"info@hardware.info"});
        	   emailIntenth.putExtra(android.content.Intent.EXTRA_SUBJECT, "Contact Hardware.info.");
        	   startActivity(Intent.createChooser(emailIntenth, "Contact Hardware.info."));        	   
           }
       });
		
		rbemail = (TextView)this.findViewById(R.id.textViewrb4);
		rbemail.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
        	   Intent emailIntenth;
        	   emailIntenth = new Intent(android.content.Intent.ACTION_SEND);
        	   emailIntenth.setType("plain/text");
        	   emailIntenth.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"1003114@ict-idcollege.nl"});
        	   emailIntenth.putExtra(android.content.Intent.EXTRA_SUBJECT, "Contact Robin-Barry.");
        	   startActivity(Intent.createChooser(emailIntenth, "Contact Robin-Barry."));        	   
           }
       });
		
		//Sitessssss
		sendmediaweb = (TextView)this.findViewById(R.id.textView3);
		sendmediaweb.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
        	   Uri uri = Uri.parse("http://sendmedia.co.id/");
               startActivity(new Intent(Intent.ACTION_VIEW, uri));        	   
           }
       });	
		
		tweakersweb = (TextView)this.findViewById(R.id.textViewtw3);
		tweakersweb.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
        	   Uri uri = Uri.parse("http://tweakers.net/");
               startActivity(new Intent(Intent.ACTION_VIEW, uri));        	   
           }
       });	
		
		hwiweb = (TextView)this.findViewById(R.id.textViewhw3);
		hwiweb.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
        	   Uri uri = Uri.parse("http://nl.hardware.info/");
               startActivity(new Intent(Intent.ACTION_VIEW, uri));        	   
           }
       });	
		
		nuweb = (TextView)this.findViewById(R.id.textViewnu3);
		nuweb.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
        	   Uri uri = Uri.parse("http://www.nu.nl/");
               startActivity(new Intent(Intent.ACTION_VIEW, uri));        	   
           }
       });
		
		rbweb = (TextView)this.findViewById(R.id.textViewrb3);
		rbweb.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
        	   Uri uri = Uri.parse("http://robin-barry.github.io/");
               startActivity(new Intent(Intent.ACTION_VIEW, uri));        	   
           }
       });		
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            if (getParentActivityIntent() == null) {
                onBackPressed();
            } else {
                NavUtils.navigateUpFromSameTask(this);
            }
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
