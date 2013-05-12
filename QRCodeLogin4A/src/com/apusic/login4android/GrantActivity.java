package com.apusic.login4android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.apusic.login.client.QRLoginService;
import com.apusic.login.client.impl.QRLoginServiceImpl;

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
		getMenuInflater().inflate(R.menu.activity_grant, menu);
		return true;
	}
	
	//发送授权
	public void submitGrant(View view) {
		//主进程中不能直接调用网络，需要异步调用
		GrantTask grantTask = new GrantTask(this);
		grantTask.execute(new Void[]{});
	}
	
	//取消授权
	public void cancelGrant(View view) {
		finish();
	}
	
	
	
	class GrantTask extends AsyncTask<Void, Void, Boolean> {
		
		private GrantActivity activity;
		
		GrantTask(GrantActivity activity) {
			this.activity = activity; 
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			QRLoginService loginService = QRLoginServiceImpl.getInstance();
			return loginService.grant(serialNumber);
		}

		@Override
		protected void onPostExecute(final Boolean result) {
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			
			builder.setMessage(result ? R.string.grant_success : R.string.grant_failed)
			       .setCancelable(false)
			       .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			       			finish();
			           }
			       });
			AlertDialog alert = builder.create();
			alert.show();
		}

		
		
	}
	
	
	

}
