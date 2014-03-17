package com.example.wagontester;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.wagontester.R;
import com.example.wagontester.db.DBHelper;
import com.example.wagontester.db.DBPresetter;

public class SplashActivity extends Activity{

	private static final long  SPLASH_DELAY_MILLIS = 1500;
	
	private static final String DEFAULT_KEY_VERSION = "Version";
	private static final String DEFAULT_KEY_HOSTIP = "HostIP";
	private static final String DEFAULT_KEY_ROOTPWD = "RootPwd";
	private static final String DEFAULT_VALUE_HOSTIP = "192.168.1.1";
	private static final String DEFAULT_VALUE_ROOTPWD = "123456";

	// SharedPreferences
	private SharedPreferences mSpApp;

	@Override
	public void onCreate(Bundle savedInstanceBundle) {
		super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_splash);

        // ‘§÷√ ˝æ›
		mSpApp = getSharedPreferences("app", MODE_PRIVATE);
		if(!mSpApp.contains(DEFAULT_KEY_VERSION) || 
			mSpApp.getInt(DEFAULT_KEY_VERSION, 0)!=DBHelper.getDatabaseVersion()) {
			preset();
		}
		
		// ∑‚√ÊÕº∆¨
		new Handler().postDelayed(new Runnable() { // Redirecting
			@Override
			public void run() {
				// Go to LoginActivity
				Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
			} 
		}, SPLASH_DELAY_MILLIS);
	}
	
	private void preset() {
		SharedPreferences.Editor editor = mSpApp.edit();
		editor.putInt(DEFAULT_KEY_VERSION, DBHelper.getDatabaseVersion());
		editor.putString(DEFAULT_KEY_HOSTIP, DEFAULT_VALUE_HOSTIP);
		editor.putString(DEFAULT_KEY_ROOTPWD, DEFAULT_VALUE_ROOTPWD);
		editor.commit();

		// Preset database
		DBPresetter.preset(getContentResolver());
	}
}

