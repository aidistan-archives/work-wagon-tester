package com.example.wagontester;

import java.util.ArrayList;

import com.example.wagontester.R;
import com.example.wagontester.common.TaskView;
import com.example.wagontester.db.DBContract;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
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
	private ArrayAdapter<String> mSearchAdapter;
	private ListHelper mListHelper;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mSortBySpinner = (Spinner)findViewById(R.id.sortBySpinner);
		mSortBySpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SORT_BY_OPTIONS));
		mSortBySpinner.setOnItemClickListener(new SortBySpinnerListener());
		
		mSearchAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<String>());
		mSpinner = (Spinner)findViewById(R.id.spinner);
		mSpinner.setAdapter(mSearchAdapter);
		mTextView = (AutoCompleteTextView)findViewById(R.id.textView);
		mTextView.setAdapter(mSearchAdapter);
		
//		mSearchTextView.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				mListHelper.requery(mSearchTextAdapter.getItem(position));
//			}
//		});
		
		mListHelper = new ListHelper(this);
		mListView = (ListView)findViewById(R.id.listView);
		mListView.setAdapter(mListHelper);
		mListView.setItemsCanFocus(false);
		mListView.setOnItemClickListener(mListHelper);
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
		switch (item.getItemId()) {
		case R.id.add:
		case R.id.upload:
			break;
		case R.id.change:
			Intent intent = new Intent(this, LoginActivity.class);
	    	startActivity(intent);
			this.finish();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
	
	private class SortBySpinnerListener implements AdapterView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// Clear
			mTextView.setText("");
			mSearchAdapter.clear();
			
			// Views
			switch (position) {
			case SORT_BY_ALL:
				mTextView.setEnabled(false);
				mTextView.setVisibility(View.GONE);
				mSpinner.setEnabled(false);
				mSpinner.setVisibility(View.GONE);
				return;
			case SORT_BY_USER:
			case SORT_BY_DUTY:
				mTextView.setEnabled(false);
				mTextView.setVisibility(View.GONE);
				mSpinner.setEnabled(true);
				mSpinner.setVisibility(View.VISIBLE);
				mSpinner.requestFocus();
				mSortBySpinner.setNextFocusDownId(R.id.spinner);
				mSpinner.setNextFocusUpId(R.id.sortBySpinner);
				break;
			case SORT_BY_MODEL:
			case SORT_BY_WAGON:
			case SORT_BY_PLATFORM:
			case SORT_BY_DATE:
				mTextView.setEnabled(true);
				mTextView.setVisibility(View.VISIBLE);
				mTextView.requestFocus();
				mSpinner.setEnabled(false);
				mSpinner.setVisibility(View.GONE);
				mSortBySpinner.setNextFocusDownId(R.id.textView);
				mTextView.setNextFocusUpId(R.id.sortBySpinner);
				break;
			}
			
			// Data
			Cursor cursor;
			switch (position) {
				case SORT_BY_USER:
					cursor = getContentResolver().query(DBContract.UserTable.CONTENT_URI, null, null, null, null);
					break;
				case SORT_BY_DUTY:
					cursor = getContentResolver().query(DBContract.DutyTable.CONTENT_URI, null, null, null, null);
					break;
				default:
					throw new IllegalArgumentException();
					
			}
			
			for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
			{
				mSearchAdapter.add(cursor.getString(1));
			}
			cursor.close();

//			if (position == DBHelper.TaskTable.SORT_BY_ALL) {} 
//			else {
//				// Update SearchTextAdapter
//				
//				switch (position) {
//				case DBHelper.TaskTable.SORT_BY_WAGONNUMBER:
//					cursor = DBHelper.mDB.query(DBHelper.TaskTable.NAME, null, null, null, DBHelper.TaskTable.GROUP_BY_COLUMNS[position], null, null);

//					break;
//				default:
//					cursor = DBHelper.mDB.query(DBHelper.TaskTable.NAME, null, null, null, DBHelper.TaskTable.GROUP_BY_COLUMNS[position], null, null);
//					for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
//					{
//						mSearchTextAdapter.add(cursor.getString(cursor.getColumnIndex(DBHelper.TaskTable.GROUP_BY_COLUMNS[position])));
//					}
//				}
//				cursor.close();
//				
//			}
			mSearchAdapter.notifyDataSetChanged();
		}
		
	}
	
	private class ListHelper extends CursorAdapter implements AdapterView.OnItemClickListener {

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
			c = context.getContentResolver().query(
					Uri.withAppendedPath(DBContract.ModelTable.CONTENT_URI, String.valueOf(cursor.getInt(DBContract.TaskTable.POS_MODEL))), 
					null, null, null, null);
			c.moveToFirst();
			taskView.modelView.setText(c.getString(DBContract.ModelTable.POS_NAME));
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			return new TaskView(context, null);

		}
		
//		public void requery() {
//			requery(mSearchTextView.getText().toString());
//		}
//		
//		public void requery(String query) {
//			changeCursor(DBHelper.TaskTable.queryTasks(mSortBySpinner.getSelectedItemPosition(), query));
//		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//			int task_id = Long.valueOf(id).intValue();
//			mSpAppEditor.putInt("TaskID", task_id);
//
//			Cursor cursor = DBHelper.TaskTable.queryTask(task_id);
//			cursor.moveToFirst();
//			boolean isFinished = (cursor.getInt(cursor.getColumnIndex(DBHelper.TaskTable.IS_FINISHED)) == 1); 
//			cursor.close();
//			if (!isFinished) {
//				mSpAppEditor.putInt("TaskMode", TaskActivity.MODE_MODIFY);
//				mSpAppEditor.commit();
//				TaskListActivity.this.gotoTask();
//			} else {
//				AlertDialog.Builder builder = new AlertDialog.Builder(TaskListActivity.this);
//				builder.setTitle("已经完成任务")
//				       .setCancelable(true)
//				       .setPositiveButton("查看", new DialogInterface.OnClickListener() {
//						
//							@Override
//							public void onClick(DialogInterface dialog, int which) {
//								mSpAppEditor.putInt("TaskMode", TaskActivity.MODE_VIEW);
//								mSpAppEditor.commit();
//								TaskListActivity.this.gotoTask();
//							}
//						})
//						.setNeutralButton("修改", new DialogInterface.OnClickListener() {
//						
//							@Override
//							public void onClick(DialogInterface dialog, int which) {
//								mSpAppEditor.putInt("TaskMode", TaskActivity.MODE_MODIFY);
//								mSpAppEditor.commit();
//								TaskListActivity.this.gotoTask();
//							}
//						})
//						.setNegativeButton("返回", new DialogInterface.OnClickListener() {
//						
//							@Override
//							public void onClick(DialogInterface dialog, int which) {
//							}
//						})
//						.show();
//			}
		}
	}
}
