package com.example.wagontester;

import android.app.Activity;
import android.os.Bundle;

public class PartActivity extends Activity {
	
	public static final String EXTRA_TASK = "com.example.wagontester.task_activity.task_id";
	public static final String EXTRA_PART = "com.example.wagontester.task_activity.part_name";
	
	private int mTaskID;
	private String mPartName;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_part);
		
		mTaskID = getIntent().getExtras().getInt(EXTRA_TASK);
		mPartName = getIntent().getExtras().getString(EXTRA_PART);
	}
}
//	
//	private SharedPreferences mSpApp;
//	
//	private Spinner mSpinner;
//	private EditText mEditText;
//	private ImageView mImageView;
//	
//	private TestTaskPart mPart;
//	private Bitmap mBitmap = null;
//	
//	@Override
//	public void onCreate(Bundle savedInstanceState) {

//		
//		mSpinner = (Spinner)findViewById(R.id.spinner);
//		mEditText = (EditText)findViewById(R.id.editText);
//		mImageView = (ImageView)findViewById(R.id.imageView);
//		
//		mSpApp = getSharedPreferences("app", MODE_PRIVATE);
//		mPart = new TestTaskPart();
//		mPart._id = mSpApp.getInt("Part_ID", 0);
//		mPart.part_name = mSpApp.getString("Part_Name", "");
//		mPart.state = mSpApp.getString("Part_State", "0"); 
//		mPart.state2 = mSpApp.getString("Part_State2", "");
//		mPart.image_path = mSpApp.getString("Part_ImagePath", "");
//		
//		mSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, TestTaskPart.STATES));
//		mSpinner.setSelection(Integer.parseInt(mPart.state));
//		mEditText.setText(mPart.state2);
//		
//		switch (mSpApp.getInt("TaskMode", TaskActivity.MODE_MODIFY)) {
//		case TaskActivity.MODE_MODIFY:
//			mImageView.setClickable(true);
//			mImageView.setFocusable(true);
//			mImageView.setFocusableInTouchMode(true);			
//			mImageView.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View arg0) {
//					Intent intent = new Intent(PartActivity.this, TaskPhotoActivity.class);
//					startActivityForResult(intent, 0);
//				}
//			});
//			showImage();
//			break;
//		case TaskActivity.MODE_VIEW:
//			mSpinner.setEnabled(false);
//			mEditText.setEnabled(false);
//			showImage();
//			break;
//		}
//		
//		mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//				mPart.state = String.valueOf(position);
//				if (position == TestTaskPart.STATES.length - 1) {
//					mEditText.setText("");
//					mEditText.setEnabled(true);
//					mEditText.setFocusable(true);
//					mEditText.setFocusableInTouchMode(true);					
//				} else {
//					mEditText.setText("");
//					mEditText.setEnabled(false);
//					mEditText.setFocusable(false);
//					mEditText.setFocusableInTouchMode(false);
//				}				
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {}
//		});
//		
//		mEditText.addTextChangedListener(new TextWatcher() {
//				
//			@Override
//			public void onTextChanged(CharSequence s, int start, int before, int count) { }
//			@Override
//			public void beforeTextChanged(CharSequence s, int start, int count,	int after) {}
//			
//			@Override
//			public void afterTextChanged(Editable s) {
//				mPart.state2 = s.toString();
//			}
//		});
//
//	}
//	
//	@Override
//	protected void onDestroy () {
//		super.onDestroy();
//		if(!(mBitmap==null) && !mBitmap.isRecycled()) {
//			mBitmap.recycle();
//		}
//	}
//	
//	@Override
//	protected void onActivityResult (int requestCode, int resultCode, Intent data) {
//		if (resultCode == RESULT_OK) {
//			mPart.image_path = data.getStringExtra(TaskPhotoActivity.INTENT_FIELD);
//			showImage();
//		}
//	}
//	
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if(keyCode == KeyEvent.KEYCODE_BACK) {
//			if(mSpApp.getInt("TaskMode", TaskActivity.MODE_MODIFY) == TaskActivity.MODE_VIEW) {
//				setResult(RESULT_CANCELED);
//			} else {
//				SharedPreferences.Editor editor = mSpApp.edit();
//				editor.putInt("Part_ID", mPart._id);
//				editor.putString("Part_Name", mPart.part_name);
//				editor.putString("Part_State", mPart.state); 
//				editor.putString("Part_State2", mPart.state2);
//				editor.putString("Part_ImagePath", mPart.image_path);
//				editor.commit();
//				
//				setResult(RESULT_OK);
//			}
//			finish();
//			return true;
//		} else {
//			return super.onKeyDown(keyCode, event);
//		}
//	}
//	
//	private void showImage() {
//		if(mPart.image_path.equals("")) { return; }
//		mBitmap = BitmapFactory.decodeFile(mPart.image_path);
//		mImageView.setImageBitmap(mBitmap);
//	}
//}
