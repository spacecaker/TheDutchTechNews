package com.thedutch.technews;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class PostViewActivity extends Activity {
	
	private WebView newswebView;
	private ProgressBar progress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.postview);
		getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON); 
		Intent indispaly = getIntent();
	    String url = indispaly.getStringExtra("link");

		
		newswebView = (WebView)this.findViewById(R.id.webview);
		progress = (ProgressBar) findViewById(R.id.progressBar);
		progress.setVisibility(View.GONE);
		newswebView.setWebViewClient(new MyWebViewClient());

	    WebSettings webSettings = newswebView.getSettings();
	    webSettings.setJavaScriptEnabled(true);

	    newswebView.getSettings().setSupportZoom(true);       //Zoom Control on web (You don't need this 
	     //if ROM supports Multi-Touch      
	    newswebView.getSettings().setBuiltInZoomControls(true); //Enable Multitouch if supported by ROM


	    newswebView.loadUrl(url);
	    final Activity MyActivity = this;


	    newswebView.setWebChromeClient(new WebChromeClient() {
	        public void onProgressChanged(WebView view, int progress) 
	           {

	            //Make the bar disappear after URL is loaded, and changes string to Loading...
	            MyActivity.setTitle("  Loading...");
	            MyActivity.setProgress(progress * 100); //Make the bar disappear after URL is loaded

	            // Return the app name after finish loading
	               if(progress == 100){

	                   MyActivity.setTitle(R.string.app_name);

	               }


	        }
	    });

	    }

	     @Override  
	        public boolean onKeyDown(int keyCode, KeyEvent event)   
	        {  
	            if ((keyCode == KeyEvent.KEYCODE_BACK) && newswebView.canGoBack()) {  
	                newswebView.goBack();  
	                return true;  
	            }  
	            return super.onKeyDown(keyCode, event);  
	        } 
	     
	     private class MyWebViewClient extends WebViewClient {	
			 @Override
			    public boolean shouldOverrideUrlLoading(WebView view, String url) {
			        view.loadUrl(url);
			        return true;
			    }

			 @Override
			public void onPageFinished(WebView view, String url) {
				 progress.setVisibility(View.GONE);
					PostViewActivity.this.progress.setProgress(100);
				super.onPageFinished(view, url);
			}

			 @Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				 progress.setVisibility(View.VISIBLE);
				 PostViewActivity.this.progress.setProgress(0);
				super.onPageStarted(view, url, favicon);
			}
		}
	     
	 	public void setValue(int progress) {
			this.progress.setProgress(progress);		
		}	     
	}