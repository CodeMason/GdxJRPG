package com.jsandusky.shooter;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class HelpActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.helplayout);
		WebView wv = (WebView)findViewById(R.id.webView1);
		wv.loadUrl("file:///android_asset/playermanual.html");
	}
}
