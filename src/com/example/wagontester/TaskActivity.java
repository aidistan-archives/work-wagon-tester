package com.example.wagontester;

import com.example.wagontester.db.DBContract;

import android.app.Activity;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TaskActivity extends Activity {
	public static final String EXTRA_MODE = "com.example.wagontester.task_activity.mode";
	public static final String EXTRA_TASK = "com.example.wagontester.task_activity.task_id";
	public static final int MODE_NEW = 0;
	public static final int MODE_MODIFY = 1;
	public static final int MODE_VIEW = 2;
	
	private int mMode;
	private int mTaskID;
	
	// Views
	private TextView mWagonView, mWagonText, mUserText, mDutyText, mModelView, mModelText, mPlatformView, mPlatformText, mDateText;
	private EditText mWagonEdit, mModelEdit, mPlatformEdit;
	
	@Override
	public void onCreate(Bundle savedInstanceBundle) {
		super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_task);
             
        // Get views
        mWagonView = (TextView)findViewById(R.id.wagonView);
        mWagonText = (TextView)findViewById(R.id.wagonText);
        mUserText = (TextView)findViewById(R.id.userText);
        mDutyText = (TextView)findViewById(R.id.dutyText);
        mModelView = (TextView)findViewById(R.id.modelView);
        mModelText = (TextView)findViewById(R.id.modelText);
        mPlatformView = (TextView)findViewById(R.id.platformView);
        mPlatformText = (TextView)findViewById(R.id.platformText);
        mDateText = (TextView)findViewById(R.id.dateText);
        mWagonEdit = (EditText)findViewById(R.id.wagonEdit);
        mModelEdit = (EditText)findViewById(R.id.modelEdit);
        mPlatformEdit = (EditText)findViewById(R.id.platformEdit);
        
        // Set views to show
        mMode = getIntent().getExtras().getInt(EXTRA_MODE);
        if (mMode == MODE_NEW) {
            mWagonView.setVisibility(View.GONE);
            mWagonText.setVisibility(View.GONE);
            mWagonEdit.setVisibility(View.VISIBLE);
            mModelView.setVisibility(View.GONE);
            mModelText.setVisibility(View.GONE);
            mModelEdit.setVisibility(View.VISIBLE);
            mPlatformView.setVisibility(View.GONE);
            mPlatformText.setVisibility(View.GONE);
            mPlatformEdit.setVisibility(View.VISIBLE);
        } else {
        	mTaskID = getIntent().getExtras().getInt(EXTRA_TASK);
        	
            mWagonView.setVisibility(View.VISIBLE);
            mWagonText.setVisibility(View.VISIBLE);
            mWagonEdit.setVisibility(View.GONE);
            mModelView.setVisibility(View.VISIBLE);
            mModelText.setVisibility(View.VISIBLE);
            mModelEdit.setVisibility(View.GONE);
            mPlatformView.setVisibility(View.VISIBLE);
            mPlatformText.setVisibility(View.VISIBLE);
            mPlatformEdit.setVisibility(View.GONE);
        }
        
        // Set values of views
//        if (mMode == MODE_NEW) {
//        	SharedPreferences sp = getSharedPreferences("app", MODE_PRIVATE);
//        	mUserText.setText(sp.getString("User", "Unknown"));
//        	mDutyText.setText(sp.getString("Duty", "Unknown"));
//        }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		switch (mMode) {
		case MODE_NEW:
			new MenuInflater(this).inflate(R.menu.activity_task_new, menu);
			return true;
		case MODE_MODIFY:
			new MenuInflater(this).inflate(R.menu.activity_task_modify, menu);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		if (mMode == MODE_NEW) {
			switch (item.getItemId()) {
				
			}
		} else {
			ContentValues cv = new ContentValues();
			switch (item.getItemId()) {
			case R.id.check:
				cv.put(DBContract.TaskTable.KEY_STATUS, 1);				
				break;
			case R.id.uncheck:
				cv.put(DBContract.TaskTable.KEY_STATUS, 0);
				break;
			}
			getContentResolver().update(DBContract.TaskTable.CONTENT_URI, cv, "_id=" + String.valueOf(mTaskID), null);
			this.finish();
		}
		return true;
	}
}
