package com.example.wagontester;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.example.wagontester.R;
import com.example.wagontester.common.TaskView;
import com.example.wagontester.common.Utility;
import com.example.wagontester.db.DBContract;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static final String[] SORT_BY_OPTIONS = {"全部", "按作业者", "按车型", "按车号", "按台位", "按日期", "按岗位"};
	private static final int SORT_BY_ALL = 0;
	private static final int SORT_BY_USER = 1;
	private static final int SORT_BY_DUTY = 6;
	private static final int SORT_BY_MODEL = 2;
	private static final int SORT_BY_WAGON = 3;
	private static final int SORT_BY_PLATFORM = 4;
	private static final int SORT_BY_DATE = 5;

	// Views
	private Spinner mSortBySpinner, mSpinner;
	private AutoCompleteTextView mTextView;
	private ListView mListView;
	
	// View adapters
	private ArrayList<String> mArrayList;
	private ListHelper mListHelper;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mArrayList = new ArrayList<String>();
		mSpinner = (Spinner)findViewById(R.id.spinner);
		mTextView = (AutoCompleteTextView)findViewById(R.id.textView);
		
		mSortBySpinner = (Spinner)findViewById(R.id.sortBySpinner);
		mSortBySpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SORT_BY_OPTIONS));
		mSortBySpinner.setOnItemSelectedListener(new SortBySpinnerListener());
		
		mListHelper = new ListHelper(this);
		mListView = (ListView)findViewById(R.id.listView);
		mListView.setAdapter(mListHelper);
		mListView.setItemsCanFocus(false);
		mListView.setOnItemClickListener(mListHelper);
		mListView.setOnItemLongClickListener(mListHelper);
		
		mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				mListHelper.requery();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {}
		});
		mTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				mListHelper.requery();
			}
		});
	}
	
	@Override
	public void onRestart() {
		super.onRestart();
		mListHelper.requery();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
		case KeyEvent.KEYCODE_HOME:
		case KeyEvent.KEYCODE_POWER:
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
		Intent intent;
		switch (item.getItemId()) {
		case R.id.add:
			intent = new Intent(MainActivity.this, TaskActivity.class);
			intent.putExtra(TaskActivity.EXTRA_MODE, TaskActivity.MODE_NEW);
			startActivity(intent);		
		case R.id.upload:
			// Tasks to upload
			ArrayList<Integer> taskList = new ArrayList<Integer>();
			Cursor c = getContentResolver().query(DBContract.TaskTable.CONTENT_URI, new String[] {"_id"}, 
					DBContract.TaskTable.KEY_STATUS + "=1", null, null);
			for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				taskList.add(c.getInt(0));
			}
			c.close();
			
			// Check
//			if (taskList.size() == 0) {
//				Toast.makeText(this, "无需导出", Toast.LENGTH_SHORT).show();
//				break;
//			}
			
			// TODO: Finish in future
			File root = Environment.getExternalStorageDirectory();
			root = new File(root.toURI().resolve("货车检查仪"));
			if (!root.exists()) {
				root.mkdir();
			}
			
			// Each task
			for(int task_id : taskList) {
				File dir = new File(root.toURI().resolve(String.valueOf(task_id)));
				dir.mkdir();
				File file = new File(dir.toURI().resolve("任务.txt"));

				try {
					FileOutputStream fOut = new FileOutputStream(file);
					
					// Task
					c = getContentResolver().query(DBContract.TaskTable.CONTENT_URI, null, 
							"_id=" + String.valueOf(task_id), null, null);
					c.moveToFirst();
					fOut.write(("车号：" + c.getString(DBContract.TaskTable.POS_WAGON) + "\r\n").getBytes());
					fOut.write(("型号：" + c.getString(DBContract.TaskTable.POS_MODEL) + "\r\n").getBytes());
					fOut.write(("台号：" + c.getString(DBContract.TaskTable.POS_PLATFORM) + "\r\n").getBytes());
					fOut.write(("作业者：" + c.getString(DBContract.TaskTable.POS_USER) + "\r\n").getBytes());
					fOut.write(("岗位：" + c.getString(DBContract.TaskTable.POS_DUTY) + "\r\n").getBytes());
					fOut.write(("日期：" + c.getString(DBContract.TaskTable.POS_DATE) + "\r\n").getBytes());
					c.close();
					
					// Parts
					
					fOut.flush();
					fOut.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				Utility.deleteTask(this, task_id);
			}
			
			// In the end
			Toast.makeText(this, "导出完成", Toast.LENGTH_SHORT).show();
			mListHelper.requery();
			break;
		case R.id.change:
			intent = new Intent(this, LoginActivity.class);
	    	startActivity(intent);
			this.finish();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
	
	private class SortBySpinnerListener implements AdapterView.OnItemSelectedListener {

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			mSortBySpinner.setSelection(SORT_BY_ALL);
		}
		
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			// Clear
			mTextView.setText("");
			
			// Data
			if (position == SORT_BY_ALL) {
				mListHelper.requery();
			} else {
				Cursor cursor = null;
				ArrayList<Integer> idList = new ArrayList<Integer>();
				
				mArrayList.clear();
				switch (position) {
					case SORT_BY_USER:
						// Get user ids
						cursor = getContentResolver().query(DBContract.TaskTable.CONTENT_URI, 
								new String[] {DBContract.TaskTable.KEY_USER}, null, null, DBContract.TaskTable.KEY_USER);
						for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
						{
							if (!idList.contains(cursor.getInt(0))) {
								idList.add(cursor.getInt(0));
							}
						}
						cursor.close();
						
						// Get user names
						for(Integer user_id : idList) {
							cursor = getContentResolver().query(
									Uri.withAppendedPath(DBContract.UserTable.CONTENT_URI, String.valueOf(user_id)), 
									new String[] {DBContract.UserTable.KEY_NAME}, null, null, null);
							cursor.moveToFirst();
							mArrayList.add(cursor.getString(0));
							cursor.close();
						}
						break;
					case SORT_BY_DUTY:
						// Get duty ids
						cursor = getContentResolver().query(DBContract.TaskTable.CONTENT_URI, 
								new String[] {DBContract.TaskTable.KEY_DUTY}, null, null, DBContract.TaskTable.KEY_DUTY);
						for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
						{
							if (!idList.contains(cursor.getInt(0))) {
								idList.add(cursor.getInt(0));
							}
						}
						cursor.close();
						
						// Get duty names
						for(Integer duty_id : idList) {
							cursor = getContentResolver().query(
									Uri.withAppendedPath(DBContract.DutyTable.CONTENT_URI, String.valueOf(duty_id)), 
									new String[] {DBContract.DutyTable.KEY_NAME}, null, null, null);
							cursor.moveToFirst();
							mArrayList.add(cursor.getString(0));
							cursor.close();
						}
						break;
					case SORT_BY_MODEL:
						cursor = getContentResolver().query(DBContract.TaskTable.CONTENT_URI, 
								new String[] {DBContract.TaskTable.KEY_MODEL}, null, null, DBContract.TaskTable.KEY_MODEL);
						for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
						{
							if (!mArrayList.contains(cursor.getString(0))) {
								mArrayList.add(cursor.getString(0));
							}
						}
						cursor.close();
						break;
					case SORT_BY_WAGON:
						cursor = getContentResolver().query(DBContract.TaskTable.CONTENT_URI, 
								new String[] {DBContract.TaskTable.KEY_WAGON}, null, null, DBContract.TaskTable.KEY_WAGON);
						for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
						{
							if (!mArrayList.contains(cursor.getString(0))) {
								mArrayList.add(cursor.getString(0));
							}
						}
						cursor.close();
						break;
					case SORT_BY_PLATFORM:
						cursor = getContentResolver().query(DBContract.TaskTable.CONTENT_URI, 
								new String[] {DBContract.TaskTable.KEY_PLATFORM}, null, null, DBContract.TaskTable.KEY_PLATFORM);
						for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
						{
							if (!mArrayList.contains(cursor.getString(0))) {
								mArrayList.add(cursor.getString(0));
							}
						}
						cursor.close();
						break;
					case SORT_BY_DATE:
						cursor = getContentResolver().query(DBContract.TaskTable.CONTENT_URI, 
								new String[] {DBContract.TaskTable.KEY_DATE}, null, null, DBContract.TaskTable.KEY_DATE);
						for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
						{
							if (!mArrayList.contains(cursor.getString(0))) {
								mArrayList.add(cursor.getString(0));
							}
						}
						cursor.close();
						break;
				}
			}

			// Views
			switch (position) {
			case SORT_BY_ALL:
				mTextView.setVisibility(View.INVISIBLE);
				mSpinner.setVisibility(View.GONE);
				mSortBySpinner.setNextFocusDownId(R.id.listView);
				return;
			case SORT_BY_USER:
			case SORT_BY_DUTY:
				mTextView.setVisibility(View.GONE);
				mSpinner.setVisibility(View.VISIBLE);
				mSpinner.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, mArrayList));
				mSpinner.requestFocus();
				mSortBySpinner.setNextFocusDownId(R.id.spinner);
				mSpinner.setNextFocusUpId(R.id.sortBySpinner);
				break;
			case SORT_BY_MODEL:
			case SORT_BY_WAGON:
			case SORT_BY_PLATFORM:
			case SORT_BY_DATE:
				mTextView.setVisibility(View.VISIBLE);
				mSpinner.setVisibility(View.GONE);
				mTextView.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, mArrayList));
				mTextView.requestFocus();
				mSortBySpinner.setNextFocusDownId(R.id.textView);
				mTextView.setNextFocusUpId(R.id.sortBySpinner);
				break;
			}
		}
	}
	
	private class ListHelper extends CursorAdapter implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

		public ListHelper(Context context) {
			super(context, getContentResolver().query(DBContract.TaskTable.CONTENT_URI, null, null, null, null), false);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {  
			TaskView taskView = (TaskView)view;
			
			// Icon
			if (cursor.getInt(DBContract.TaskTable.POS_STATUS) == 1) {
				taskView.imageView.setVisibility(View.VISIBLE);
			} else {
				taskView.imageView.setVisibility(View.INVISIBLE);
			}
			
			// String
			taskView.wagonView.setText(cursor.getString(DBContract.TaskTable.POS_WAGON));
			taskView.platformView.setText(cursor.getString(DBContract.TaskTable.POS_PLATFORM));
			taskView.dateView.setText(cursor.getString(DBContract.TaskTable.POS_DATE));
			taskView.modelView.setText(cursor.getString(DBContract.TaskTable.POS_MODEL));
			
			// ID
			Cursor c;
			c = context.getContentResolver().query(
					Uri.withAppendedPath(DBContract.UserTable.CONTENT_URI, String.valueOf(cursor.getInt(DBContract.TaskTable.POS_USER))), 
					null, null, null, null);
			c.moveToFirst();
			taskView.userView.setText(c.getString(DBContract.UserTable.POS_NAME));
			c = context.getContentResolver().query(
					Uri.withAppendedPath(DBContract.DutyTable.CONTENT_URI, String.valueOf(cursor.getInt(DBContract.TaskTable.POS_DUTY))), 
					null, null, null, null);
			c.moveToFirst();
			taskView.dutyView.setText(c.getString(DBContract.DutyTable.POS_NAME));
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			return new TaskView(context, null);
		}
		
		public void requery() {
			Cursor c;
			
			switch (mSortBySpinner.getSelectedItemPosition()) {
			case SORT_BY_ALL:
				changeCursor(getContentResolver().query(DBContract.TaskTable.CONTENT_URI, null, null, null, null));
				break;
			case SORT_BY_USER:
				c = getContentResolver().query(DBContract.UserTable.CONTENT_URI, null, 
						DBContract.UserTable.KEY_NAME + "=?", new String[] {(String)mSpinner.getSelectedItem()}, null);
				c.moveToFirst();
				changeCursor(getContentResolver().query(DBContract.TaskTable.CONTENT_URI, null, 
						DBContract.TaskTable.KEY_USER + "=" + String.valueOf(c.getInt(0)), null, null));
				c.close();
				break;
			case SORT_BY_DUTY:
				c = getContentResolver().query(DBContract.DutyTable.CONTENT_URI, null, 
						DBContract.DutyTable.KEY_NAME + "=?", new String[] {(String)mSpinner.getSelectedItem()}, null);
				c.moveToFirst();
				changeCursor(getContentResolver().query(DBContract.TaskTable.CONTENT_URI, null, 
						DBContract.TaskTable.KEY_DUTY + "=" + String.valueOf(c.getInt(0)), null, null));
				c.close();
				break;
			case SORT_BY_MODEL:
				changeCursor(getContentResolver().query(DBContract.TaskTable.CONTENT_URI, null, 
						DBContract.TaskTable.KEY_MODEL + "=?", new String[] {mTextView.getText().toString()}, null));
				break;
			case SORT_BY_WAGON:
				changeCursor(getContentResolver().query(DBContract.TaskTable.CONTENT_URI, null, 
						DBContract.TaskTable.KEY_WAGON + "=?", new String[] {mTextView.getText().toString()}, null));
				break;
			case SORT_BY_PLATFORM:
				changeCursor(getContentResolver().query(DBContract.TaskTable.CONTENT_URI, null, 
						DBContract.TaskTable.KEY_PLATFORM + "=?", new String[] {mTextView.getText().toString()}, null));
				break;
			case SORT_BY_DATE:
				changeCursor(getContentResolver().query(DBContract.TaskTable.CONTENT_URI, null, 
						DBContract.TaskTable.KEY_DATE + "=?", new String[] {mTextView.getText().toString()}, null));
				break;
			}
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {
			Intent intent = new Intent(MainActivity.this, TaskActivity.class);
			intent.putExtra(TaskActivity.EXTRA_MODE, TaskActivity.MODE_WORK);
			intent.putExtra(TaskActivity.EXTRA_TASK, (int)id);
			startActivity(intent);
		}

		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id) {
			new AlertDialog.Builder(MainActivity.this)
					.setTitle("删除任务：")
				    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
				    	@Override
				    	public void onClick(DialogInterface dialog, int which) {
				    		Utility.deleteTask(MainActivity.this, (int)id);
				    		requery();
				    	}
					})
					.setNegativeButton("取消", null)
					.show();
			return true;
		}
	}
}
