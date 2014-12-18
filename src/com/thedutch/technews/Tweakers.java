package com.thedutch.technews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.faizmalkani.floatingactionbutton.FloatingActionButton;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Tweakers extends Fragment implements OnClickListener {

	String key_items = "item";
	String key_title = "title";
	String key_description = "description";
	String key_link = "link";
	String key_date = "pubDate";
	ListView lstPost = null;
	List<HashMap<String, Object>> post_lists = new ArrayList<HashMap<String, Object>>();
	List<String> lists = new ArrayList<String>();
	ArrayAdapter<String> adapter23 = null;
	RSSReader rssfeed = new RSSReader();
	FloatingActionButton mFab;

	public static Fragment newInstance(Context context) {
		Tweakers f = new Tweakers();
 
        return f;
    }
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View view = inflater.inflate(
	            R.layout.activity_main4, container, false);
		((MainActivity) getActivity())
        .setActionBarTitle("Tweakers");
		mFab = (FloatingActionButton) view.findViewById(R.id.fabbutton);
	    mFab.setOnClickListener(this);
        return view;
    }
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Appstatus.getInstance(getActivity()).isOnline()) {
		lstPost = (ListView) getView().findViewById(R.id.lstPosts);
		mFab = (FloatingActionButton) getView().findViewById(R.id.fabbutton);
		mFab.listenTo(lstPost);
		adapter23 = new ArrayAdapter<String>(getActivity(),
				R.layout.feed_list_item, R.id.title, lists) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = super.getView(position, convertView, parent);
				TextView txt1 = (TextView) view
						.findViewById(R.id.title);
				TextView txt2 = (TextView) view
						.findViewById(R.id.desc);
				TextView txt3 = (TextView) view
						.findViewById(R.id.date);
				HashMap<String, Object> data = post_lists.get(position);
				txt1.setText(data.get(key_title).toString());
				txt2.setText(data.get(key_description).toString());
				txt3.setText(data.get(key_date).toString());
				return view;
			}
		};	
			Document xmlFeed = rssfeed
				.getRSSFromServer("http://tweakers.mobi/rss/nieuws");
		NodeList nodes = xmlFeed.getElementsByTagName("item");
		for (int i = 0; i < nodes.getLength(); i++) {
			Element item = (Element) nodes.item(i);
			HashMap<String, Object> feed = new HashMap<String, Object>();
			feed.put(key_title, rssfeed.getValue(item, key_title));
			feed.put(key_description, rssfeed.getValue(item, key_description));
			feed.put(key_link, rssfeed.getValue(item, key_link));
			feed.put(key_date, rssfeed.getValue(item, key_date));
			post_lists.add(feed);
			lists.add(feed.get(key_title).toString());
		}
		lstPost.setAdapter(adapter23);
		lstPost.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
				if (Appstatus.getInstance(getActivity()).isOnline()) {
			 	   Document xmlFeed = rssfeed
							.getRSSFromServer("http://tweakers.mobi/rss/nieuws");
					NodeList nodes = xmlFeed.getElementsByTagName("item");
			 	   Element item = (Element) nodes.item(position);		
			 	  Intent indisplay = new Intent(getActivity(),PostViewActivity.class);
			 	  indisplay.putExtra("link", rssfeed.getValue(item, key_link));
			 	  startActivity(indisplay);	
				} else {
					getActivity().setContentView(R.layout.activity_main3);  
			        Thread background = new Thread() {
			            public void run() {
			                 
			                try {        
			                	sleep(5*1100);
			                	getActivity().finish();
			                     
			                } catch (Exception e) {
			                 
			                }
			            }
			        };
			        background.start();
				}
			}
		});	
		lstPost.setLongClickable(true);
		lstPost.setOnItemLongClickListener(new OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                    int position, long id) {
				if (Appstatus.getInstance(getActivity()).isOnline()) {
				 	   Document xmlFeed = rssfeed
								.getRSSFromServer("http://tweakers.mobi/rss/nieuws");
						NodeList nodes = xmlFeed.getElementsByTagName("item");
				 	   Element item = (Element) nodes.item(position);		
				 	 final Intent wintent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(rssfeed.getValue(item, key_link)));
				 	 startActivity(wintent);
				 	 return true;
				} else {
					getActivity().setContentView(R.layout.activity_main3);  
			        Thread background = new Thread() {
			            public void run() {
			                 
			                try {        
			                	sleep(5*1100);
			                	getActivity().finish();
			                     
			                } catch (Exception e) {
			                 
			                }
			            }
			        };
			        background.start();
				}
				 return true;
				}           
        }); 		
	} else {
		getActivity().setContentView(R.layout.activity_main3);  
        Thread background = new Thread() {
            public void run() {
                 
                try {        
                	sleep(5*1100);
                	getActivity().finish();
                     
                } catch (Exception e) {
                 
                }
            }
        };
        background.start();
	}
	}

    @Override
    public void onClick(View v) {
    	adapter23.notifyDataSetChanged();
    	this.adapter23.notifyDataSetChanged();    	
    	lstPost.invalidateViews();
    	lstPost.refreshDrawableState();
        Toast.makeText(this.getActivity(), 
            "Refreshed Tweakers News!", Toast.LENGTH_LONG).show();
    }
    
	   public void hideFab(View view) {
	        mFab.hide(true);
	        //getActionBar().hide();
	    }

	    public void showFab(View view) {
	        mFab.hide(false);
	        //getActionBar().show();
	    }
}
