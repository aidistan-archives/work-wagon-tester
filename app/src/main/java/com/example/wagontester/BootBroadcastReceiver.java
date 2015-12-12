package com.example.wagontester;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


//
public class BootBroadcastReceiver extends BroadcastReceiver {
	static final String ACTION = "android.intent.action.BOOT_COMPLETED";

	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(ACTION)){
			//Disable the KeyguardLock
			KeyguardManager mKeyguard = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
			KeyguardManager.KeyguardLock mKeylock = mKeyguard.newKeyguardLock("");
            mKeylock.disableKeyguard();
            
			//Load main entry
			Intent startIntent = new Intent(context, SplashActivity.class);
			startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(startIntent);
		}
	}
}