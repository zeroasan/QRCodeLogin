package com.apusic.login4android;

import com.apusic.login.client.QRLoginService;
import com.apusic.login.client.impl.QRLoginServiceImpl;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class GrantActivity extends Activity {
	
	private static final String SERIAL_NUMBER = "serialNumber";
	
	private String serialNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grant);
		
		serialNumber = getIntent().getExtras().getString(SERIAL_NUMBER);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_blank, menu);
		return true;
	}
	
	//发送授权
	public void submitGrant(View view) {
		
		
		//主进程中不能直接调用网络，需要异步调用
		GrantTask grantTask = new GrantTask();
		grantTask.execute(new Object[]{});
		
		//alert()
		Intent intent = new Intent();  
        intent.setClass(GrantActivity.this, MainActivity.class);
        startActivity(intent); 
		finish();
	}
	
	//取消授权
	public void cancelGrant(View view) {
		Intent intent = new Intent();  
        intent.setClass(GrantActivity.this, MainActivity.class);
        startActivity(intent); 
		finish();
	}
	
	
	
	class GrantTask extends AsyncTask {

		@Override
		protected Boolean doInBackground(Object... params) {
			QRLoginService loginService = QRLoginServiceImpl.getInstance();
			loginService.grant(serialNumber);
			return true;
		}

		
	}
	
	
	

}
