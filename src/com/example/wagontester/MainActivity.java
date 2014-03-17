package com.example.wagontester;

import com.example.wagontester.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
//	// SharedPreferences
//	private SharedPreferences mSpApp;
//	
//	// Views
//	private TextView mTextView;
//	private Button mTaskButton;
//	private Button mVideoButton;
//	private EditText mHostAddressEditText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		mTextView = (TextView)findViewById(R.id.textView);
//		mTaskButton = (Button)findViewById(R.id.taskButton);
//		mVideoButton = (Button)findViewById(R.id.videoButton);
//
//		mTaskButton.setOnClickListener(this);		
//		mVideoButton.setOnClickListener(this);		
//
//		mSpApp = getSharedPreferences("app", MODE_PRIVATE);
//		mTextView.setText(mSpApp.getString("Username", "δ֪"));
	}
//	
//	@Override
//	public void onDestroy(){
//		super.onDestroy();
//		mDBHelper.close();
//	}
//	
//	@Override
//	public void onClick(View view) {
//		switch(view.getId()) {
//		case R.id.taskButton:
//			Intent intent = new Intent(this, TaskListActivity.class);
//	    	startActivity(intent);			
//			break;
//		case R.id.videoButton:
//			break;
//		}
//	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.v("aidi", String.valueOf(keyCode));
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
//		case KeyEvent.KEYCODE_HOME:
//		case KeyEvent.KEYCODE_POWER:
			return true;
		default:
			return super.onKeyDown(keyCode, event);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case R.id.changeUser:
			Intent intent = new Intent(this, LoginActivity.class);
	    	startActivity(intent);
			this.finish();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
}
