package com.apusic.login4android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.apusic.login.client.impl.QRLoginServiceImpl;
import com.apusic.login4android.zxing.CaptureActivity;

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
	}
	
	public void logoutUser(View view) {
		QRLoginServiceImpl.getInstance().logout();
		
		Intent intent = new Intent();
		intent.setClass(MainActivity.this, LoginActivity.class);  
		startActivity(intent);
		
		finish();
	}

}
