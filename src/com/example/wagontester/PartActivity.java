package com.example.wagontester;

import java.io.File;
import java.util.ArrayList;

import com.example.wagontester.common.Utility;
import com.example.wagontester.db.DBContract;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class PartActivity extends Activity {
	
	public static final String EXTRA_TASK = "com.example.wagontester.task_activity.task_id";
	public static final String EXTRA_PART = "com.example.wagontester.task_activity.part_name";
	
	// Views
	private TextView mTextView;
	private ImageView mPhotoView, mDummyView;
	private ToggleButton mToggleButton;
	private Spinner mSpinner;

	// Data
	private int mTaskID;
	private String mPartName;
	private String mFault = "";
	private String mImagePath = "";
	private Bitmap mBitmap = null;
	private ArrayList<String> mArrayList = new ArrayList<String>();
	private SpinnerHelper mSpinnerHelper;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_part);
		
		mTaskID = getIntent().getExtras().getInt(EXTRA_TASK);
		mPartName = getIntent().getExtras().getString(EXTRA_PART);
		
		mTextView = (TextView)findViewById(R.id.textView);
		mPhotoView = (ImageView)findViewById(R.id.photo);
		mDummyView = (ImageView)findViewById(R.id.dummyPhoto);
		mToggleButton = (ToggleButton)findViewById(R.id.toggleButton);
		mSpinner = (Spinner)findViewById(R.id.spinner);
		
		// Load preseted faults for this part
		Cursor c = getContentResolver().query(DBContract.FaultTable.CONTENT_URI, null, 
				DBContract.FaultTable.KEY_PART + "=?", new String[] {mPartName}, null);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			mArrayList.add(c.getString(DBContract.FaultTable.POS_NAME));
		}
		c.close();
		
		// Set views
		mDummyView.requestFocus();
		mDummyView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivityForResult(new Intent(PartActivity.this, PhotoActivity.class), 0);
			}
		});
		
		mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked) {
					mFault = "";
					mSpinner.setSelection(0);
					mSpinner.setEnabled(false);
					mSpinner.setFocusable(false);
					mSpinner.setFocusableInTouchMode(false);
				} else {
					mSpinner.setEnabled(true);
					mSpinner.setSelection(0);
					mSpinner.setFocusable(true);
					mSpinner.setFocusableInTouchMode(true);
				}
			}
		});
		
		mSpinnerHelper = new SpinnerHelper();
		mSpinner.setAdapter(mSpinnerHelper);
		mSpinner.setOnItemSelectedListener(mSpinnerHelper);
		
		// Update views
		mTextView.setText(mPartName);
		reload();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		// Reload
		setViews();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		
		// Release
		mPhotoView.setImageDrawable(null);
		if (mBitmap != null) {
			mBitmap.recycle();
			mBitmap = null;
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Utility.deleteUnlinkedPhotos(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.menu.activity_part, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case R.id.save:
			if (!mToggleButton.isChecked() && mFault.equals("")) {
				Toast.makeText(this, "请选择", Toast.LENGTH_SHORT).show();
				return true;
			}
			
			ContentValues cv = new ContentValues();
			cv.put(DBContract.ContentTable.KEY_IMAGE, mImagePath);
			cv.put(DBContract.ContentTable.KEY_FAULT, mFault);
			getContentResolver().update(DBContract.ContentTable.CONTENT_URI, cv, 
					DBContract.ContentTable.KEY_TASK + "=" + String.valueOf(mTaskID) + " AND " + DBContract.ContentTable.KEY_PART + "=?", 
					new String[] {mPartName});
			this.finish();
			break;
		case R.id.reset:
			reload();
			setViews();
			break;
		case R.id.clear:
			mFault = "";
			mImagePath = "";
			setViews();
			break;
		}
		return true;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == RESULT_OK) {
			mImagePath = data.getExtras().getString(PhotoActivity.EXTRA);
			// onResume will show the picture
		}
	}
	
	private void reload() {
		Cursor c = getContentResolver().query(DBContract.ContentTable.CONTENT_URI, null, 
				DBContract.ContentTable.KEY_TASK + "=" + String.valueOf(mTaskID) + " AND " + DBContract.ContentTable.KEY_PART + "=?", 
				new String[] {mPartName}, null);
		c.moveToFirst();
		mFault = c.getString(DBContract.ContentTable.POS_FAULT);
		mImagePath = c.getString(DBContract.ContentTable.POS_IMAGE);
		c.close();
	}
	
	private void setViews() {
		// Fault
		if (mFault.equals("")) {
			mToggleButton.setChecked(true);
		} else {
			mToggleButton.setChecked(false);
			mSpinnerHelper.update();
		}
		
		// Image
		loadImage();
	}
	
	private void loadImage() {
		loadImage(mImagePath);
	}
	
	private void loadImage(String path) {
		mImagePath = path;
		
		// Release
		mPhotoView.setImageDrawable(null);
		if (mBitmap != null) {
			mBitmap.recycle();
			mBitmap = null;
		}
		
		// Check
		if (mImagePath.equals("")) {
			return;
		}
		
		File imgFile = new File(mImagePath);
		mBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
	    mPhotoView.setImageBitmap(mBitmap);
	}
	
	private class SpinnerHelper extends BaseAdapter implements AdapterView.OnItemSelectedListener {

		private boolean isCustom() {
			return !mFault.equals("") && !mArrayList.contains(mFault);
		}
		
		public void update() {
			if (mFault.equals("")) {
				mSpinner.setSelection(0);
			} else if (mArrayList.contains(mFault)) {
				mSpinner.setSelection(mArrayList.indexOf(mFault)+1);
			} else {
				mSpinner.setSelection(getCount()-2);
			}
			mSpinnerHelper.notifyDataSetChanged();
		}
		
		@Override
		public int getCount() {
			int count = 2 + mArrayList.size();
			if (isCustom()) {
				count += 1;
			}
			return count;
		}

		@Override
		/**
		 * Please use mFault instead.
		 */
		public Object getItem(int position) { return null; }
		
		@Override
		public long getItemId(int position) { return 0; }

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView view;
			if (convertView == null) {
				view = new TextView(PartActivity.this);
			} else {
				view = (TextView)convertView;
			}
			view.setTextSize(16);
			view.setTextColor(getResources().getColor(R.color.metro_black));
			
			if (position == 0) {
				view.setText("请选择");
			} else if (isCustom() && position == getCount()-2) {
				view.setText(mFault);
			} else if (position == getCount()-1) {
				view.setText("自定义");
			} else {
				view.setText(mArrayList.get(position-1));
			}
			return view;
		}

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			// 自定义
			
			if (position == 0) {
				mFault = "";
			} else if (isCustom() && position == getCount()-2) {
				// Not change
			} else if (position == getCount()-1) {
				final EditText editText = new EditText(PartActivity.this);
				if (isCustom()) {
					editText.setText(mFault);
				}
				new AlertDialog.Builder(PartActivity.this)
						.setTitle("自定义")
						.setView(editText)
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								mFault = editText.getText().toString();
								update();
							}
						})
						.setOnCancelListener(new DialogInterface.OnCancelListener() {
							@Override
							public void onCancel(DialogInterface dialog) {
								mSpinner.setSelection(0);
							}
						})
						.show();
			} else {
				mFault = mArrayList.get(position-1);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			mSpinner.setSelection(0);
		}
		
	}
}
