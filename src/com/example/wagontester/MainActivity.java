package com.example.wagontester;

import java.util.ArrayList;

import com.example.wagontester.R;
import com.example.wagontester.db.DBContract;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	
	private static final String[] SORT_BY_OPTIONS = {"全部", "按作业者", "按车型", "按车号", "按台位", "按日期", "按岗位"};
	private static final int SORT_BY_ALL = 0;
	private static final int SORT_BY_USER = 1;
	private static final int SORT_BY_MODEL = 2;
	private static final int SORT_BY_WAGON = 3;
	private static final int SORT_BY_PLATFORM = 4;
	private static final int SORT_BY_DATE = 5;
	private static final int SORT_BY_DUTY = 6;
	
	// Views
	private Spinner mSortBySpinner;
	private AutoCompleteTextView mSearchTextView;
	private ListView mListView;
	
	// View adapters
	private ArrayAdapter<String> mSearchTextAdapter;
	private ListHelper mListHelper;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mSortBySpinner = (Spinner)findViewById(R.id.spinner);
		mSortBySpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SORT_BY_OPTIONS));
		mSortBySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
				case SORT_BY_ALL:
					mSearchTextView.setText("");
					mSearchTextView.setEnabled(false);
					break;
				default:
					mSearchTextView.setText("");
					mSearchTextView.setEnabled(true);
					mSearchTextView.requestFocus();
					mSortBySpinner.setNextFocusDownId(R.id.autoCompleteTextView);
					mSearchTextView.setNextFocusUpId(R.id.spinner);
					break;
				}
				
//				if (position == DBHelper.TaskTable.SORT_BY_ALL) {} 
//				else {
//					// Update SearchTextAdapter
//					Cursor cursor;
//					mSearchTextAdapter.clear();
//					switch (position) {
//					case DBHelper.TaskTable.SORT_BY_WAGONNUMBER:
//						cursor = DBHelper.mDB.query(DBHelper.TaskTable.NAME, null, null, null, DBHelper.TaskTable.GROUP_BY_COLUMNS[position], null, null);
//						for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
//						{
//							mSearchTextAdapter.add(DBHelper.WagonTable.queryName(
//									cursor.getInt(cursor.getColumnIndex(DBHelper.TaskTable.WAGON_ID))
//									));
//						}
//						break;
//					default:
//						cursor = DBHelper.mDB.query(DBHelper.TaskTable.NAME, null, null, null, DBHelper.TaskTable.GROUP_BY_COLUMNS[position], null, null);
//						for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
//						{
//							mSearchTextAdapter.add(cursor.getString(cursor.getColumnIndex(DBHelper.TaskTable.GROUP_BY_COLUMNS[position])));
//						}
//					}
//					cursor.close();
//					mSearchTextAdapter.notifyDataSetChanged();
//				}
//				mListHelper.requery();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				mSortBySpinner.setSelection(SORT_BY_ALL);
			}
		});
		
		mSearchTextAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
		mSearchTextView.setAdapter(mSearchTextAdapter);
		mSearchTextView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				mListHelper.requery(mSearchTextAdapter.getItem(position));
			}
		});
		
		mListHelper = new ListHelper(this);
		mListView = (ListView)findViewById(R.id.listView);
		mListView.setAdapter(mListHelper);
		mListView.setItemsCanFocus(false);
		mListView.setOnItemClickListener(mListHelper);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.v("aidi", String.valueOf(keyCode));
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
	
	private class ListHelper extends CursorAdapter implements AdapterView.OnItemClickListener{

		public ListHelper(Context context) {
			super(context, getContentResolver().query(DBContract.TaskTable.CONTENT_URI, null, null, null, null), false);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {  
//			TaskView taskView = (TaskView)view;
//			taskView.setWagonID(cursor.getInt(cursor.getColumnIndex(DBHelper.TaskTable.WAGON_ID)));
//			taskView.setModelNumber(cursor.getString(cursor.getColumnIndex(DBHelper.TaskTable.MODEL_NAME)));
//			taskView.setUsername(cursor.getString(cursor.getColumnIndex(DBHelper.TaskTable.USERNAME)));
//			taskView.setDate(cursor.getString(cursor.getColumnIndex(DBHelper.TaskTable.DATE)));
//			taskView.setFinished(cursor.getInt(cursor.getColumnIndex(DBHelper.TaskTable.IS_FINISHED)) == 1);
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
//			return new TaskView(context, null);
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
