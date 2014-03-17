package com.example.wagontester;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wagontester.R;
import com.example.wagontester.db.DBContract;

public class LoginActivity extends Activity {
	
	// SharedPreferences
	private SharedPreferences mSpApp;
	private Cursor mUserCursor;
	private Cursor mDutyCursor;
	
	// Views
	private Spinner mUserSpinner;
	private Spinner mDutySpinner;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		mUserCursor = getContentResolver().query(DBContract.UserTable.CONTENT_URI, null, null, null, null);
		mUserSpinner = (Spinner)findViewById(R.id.userSpinner);
		mUserSpinner.setAdapter(new SpinnerAdapter(this, mUserCursor, DBContract.UserTable.POS_NAME));
		mDutyCursor = getContentResolver().query(DBContract.DutyTable.CONTENT_URI, null, null, null, null);
		mDutySpinner = (Spinner)findViewById(R.id.dutySpinner);
		mDutySpinner.setAdapter(new SpinnerAdapter(this, mDutyCursor, DBContract.DutyTable.POS_NAME));
		mSpApp = getSharedPreferences("app", MODE_PRIVATE);
	}
	
	private class SpinnerAdapter extends CursorAdapter {
		private int mPos; // Position for text
		public SpinnerAdapter(Context context, Cursor c, int pos) {
			super(context, c, false);
			mPos = pos;
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			((TextView)view).setText(cursor.getString(mPos));
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			TextView view = new TextView(context);
			view.setHeight((int) context.getResources().getDimension(R.dimen.text_xlarge));
			view.setTextColor(Color.BLACK);
			view.setTextSize(20);
			return view;
		}
	}
	
	public void onLogin(View view) {
		int user = mUserSpinner.getSelectedItemPosition();
		int duty = mDutySpinner.getSelectedItemPosition();
		
		if(user == AdapterView.INVALID_POSITION) {
			showDialog("请选择用户");
		} else if (duty == AdapterView.INVALID_POSITION) {
			showDialog("请选择岗位");
		} else {
			mUserCursor.moveToPosition(user);
			user = mUserCursor.getInt(0);
			mDutyCursor.moveToPosition(duty);
			duty = mDutyCursor.getInt(0);
			
			SharedPreferences.Editor editor = mSpApp.edit();
			editor.putInt("User", user);
			editor.putInt("Duty", duty);
			editor.commit();
			
			Intent intent = new Intent(this, MainActivity.class);
	    	startActivity(intent);
			this.finish();
		}
	}
	
	private void showDialog(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg)
		       .setCancelable(false)
		       .setPositiveButton("确定", null)
			   .show();
	}
}
