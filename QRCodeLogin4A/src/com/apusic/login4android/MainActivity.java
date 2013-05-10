package com.apusic.login4android;

import com.apusic.login.zxing.CaptureActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	
	public void startScan(View view) {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, CaptureActivity.class);  
		startActivity(intent);
		finish();
	}

}
