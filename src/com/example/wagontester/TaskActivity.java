package com.example.wagontester;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import com.example.wagontester.db.DBContract;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TaskActivity extends Activity {
	public static final String EXTRA_MODE = "com.example.wagontester.task_activity.mode";
	public static final String EXTRA_TASK = "com.example.wagontester.task_activity.task_id";
	public static final int MODE_NEW = 0;
	public static final int MODE_WORK = 1;
	
	SharedPreferences mSpApp;
	private boolean isModeNew;
	private int mTaskID;
	
	// Views
	private TextView mWagonView, mWagonText, mUserText, mDutyText, mModelView, mModelText, mPlatformView, mPlatformText, mDateText;
	private EditText mWagonEdit, mPlatformEdit;
	private AutoCompleteTextView mModelEdit;
	private ListView mListView;
	
	private ArrayList<String> mArrayList = new ArrayList<String>();
	private ListHelper mListHelper;
	
	@Override
	public void onCreate(Bundle savedInstanceBundle) {
		super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_task);
        
        isModeNew = (getIntent().getExtras().getInt(EXTRA_MODE) == MODE_NEW);
        if (!isModeNew) {
        	mTaskID = getIntent().getExtras().getInt(EXTRA_TASK);
        }        
             
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
        mModelEdit = (AutoCompleteTextView)findViewById(R.id.modelEdit);
        mPlatformEdit = (EditText)findViewById(R.id.platformEdit);
        mListView = (ListView)findViewById(R.id.listView);
        
        // Set views to show
        if (isModeNew) {
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
        Cursor c;
        if (isModeNew) {
        	mSpApp = getSharedPreferences("app", MODE_PRIVATE);
        	c = getContentResolver().query(DBContract.UserTable.CONTENT_URI, 
        			new String[] {DBContract.UserTable.KEY_NAME}, 
        			"_id=" + String.valueOf(mSpApp.getInt("User", 0)), null, null);
        	c.moveToFirst();
        	mUserText.setText(c.getString(0));
        	c.close();
        	c = getContentResolver().query(DBContract.DutyTable.CONTENT_URI, 
        			new String[] {DBContract.DutyTable.KEY_NAME}, 
        			"_id=" + String.valueOf(mSpApp.getInt("Duty", 0)), null, null);
        	c.moveToFirst();
        	mDutyText.setText(c.getString(0));
        	c.close();
        	
        	mDateText.setText(DateFormat.format("dd/MM/yyyy",Calendar.getInstance(Locale.CHINA)));
        	
        	ArrayList<String> modelList = new ArrayList<String>();
        	c = getContentResolver().query(DBContract.ModelTable.CONTENT_URI, null,null,null,null);
        	for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
        		modelList.add(c.getString(DBContract.ModelTable.POS_NAME));
        	}
			mModelEdit.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, modelList));
			c.close();
        } else {
        	
        }
        mListHelper = new ListHelper();
        mListView.setAdapter(mListHelper);
        mListView.setOnItemClickListener(mListHelper);
        mListView.setOnItemLongClickListener(mListHelper);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (isModeNew) {
			new MenuInflater(this).inflate(R.menu.activity_task_new, menu);
		} else {
			new MenuInflater(this).inflate(R.menu.activity_task_modify, menu);
		}
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		if (isModeNew) {
			switch (item.getItemId()) {
			case R.id.auto:
				Cursor c = getContentResolver().query(DBContract.PartTable.CONTENT_URI, 
						new String[] {DBContract.PartTable.KEY_NAME}, 
						DBContract.PartTable.KEY_DUTY + "=" + String.valueOf(mSpApp.getInt("Duty", 0)), null, null);
				for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
					if (!mArrayList.contains(c.getString(0))) {
						mArrayList.add(c.getString(0));
					}
				}
				c.close();
				mListHelper.notifyDataSetChanged();
				break;
			case R.id.save:
				if (mWagonEdit.getText().toString().isEmpty()) {
					Toast.makeText(this, "未填写车号", Toast.LENGTH_LONG).show();
				} else if (mModelEdit.getText().toString().isEmpty()) {
					Toast.makeText(this, "未填写型号", Toast.LENGTH_LONG).show();
				} else if (mPlatformEdit.getText().toString().isEmpty()) {
					Toast.makeText(this, "未填写台号", Toast.LENGTH_LONG).show();
				} else if (mArrayList.isEmpty()) {
					Toast.makeText(this, "未添加部件", Toast.LENGTH_LONG).show();
				} else {
					ContentValues cv_task = new ContentValues();
					cv_task.put(DBContract.TaskTable.KEY_DUTY, mSpApp.getInt("Duty", 0));
					cv_task.put(DBContract.TaskTable.KEY_USER, mSpApp.getInt("User", 0));
					cv_task.put(DBContract.TaskTable.KEY_WAGON, mWagonEdit.getText().toString());
					cv_task.put(DBContract.TaskTable.KEY_PLATFORM, mPlatformEdit.getText().toString());
					cv_task.put(DBContract.TaskTable.KEY_DATE, (String) mDateText.getText());
					mTaskID = Integer.parseInt(getContentResolver().insert(DBContract.TaskTable.CONTENT_URI, cv_task).getLastPathSegment());
					
					ContentValues cv_content = new ContentValues();
					cv_content.put(DBContract.ContentTable.KEY_TASK, mTaskID);
					cv_content.put(DBContract.ContentTable.KEY_FAULT, "");
					cv_content.put(DBContract.ContentTable.KEY_IMAGE, "");
					for (String s : mArrayList) {
						cv_content.put(DBContract.ContentTable.KEY_PART, s);
						getContentResolver().insert(DBContract.ContentTable.CONTENT_URI, cv_content);
					}
					
					this.finish();
				}
				break;
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
	
	private class ListHelper extends BaseAdapter implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

		@Override
		public int getCount() {
			if (isModeNew) {
				return mArrayList.size() + 1;
			} else {
				return mArrayList.size();
			}
		}

		@Override
		public Object getItem(int position) { return null; }

		@Override
		public long getItemId(int position) { return 0; }

		@Override
		public View getView(int position, View convertView, ViewGroup group) {
			TextView view;
			if (convertView == null) {
				view = new TextView(TaskActivity.this);
			} else {
				view = (TextView)convertView;
			}

			if (position == mArrayList.size()) {
				view.setText("添加部件");
				view.setTextColor(getResources().getColor(R.color.metro_gray));
			} else {
				view.setText(mArrayList.get(position));
				view.setTextColor(getResources().getColor(R.color.metro_white));
			}
			view.setTextSize(16);
			
			return view;
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
			if (isModeNew) {
				final EditText editText = new EditText(TaskActivity.this);
				if (position < mArrayList.size()) {
					editText.setText(mArrayList.get(position));
				}
				
				AlertDialog.Builder builder = new AlertDialog.Builder(TaskActivity.this);
				builder .setTitle("部件名称")
						.setView(editText)
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								if (position < mArrayList.size()) {
									mArrayList.set(position, editText.getText().toString());
								} else {
									mArrayList.add(editText.getText().toString());
								}
								mListHelper.notifyDataSetChanged();
							}
							
						})
						.show();
			} else {
//				mArrayList.get(position)
//				mTaskID
			}
		}
		
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
			if (position == mArrayList.size()) {
				return false;
			}
			
			AlertDialog.Builder builder = new AlertDialog.Builder(TaskActivity.this);
			builder .setTitle("删除部件")
				    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
				    	@Override
				    	public void onClick(DialogInterface dialog, int which) {
				    		mArrayList.remove(position);
				    		mListHelper.notifyDataSetChanged();
				    	}
					})
					.setNegativeButton("取消", null)
					.show();
			return true;
		}
	}
}
