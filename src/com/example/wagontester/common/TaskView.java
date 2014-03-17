package com.example.wagontester.common;

import com.example.wagontester.R;
import com.example.wagontester.util.DBHelper;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TaskView extends LinearLayout {

	// Icon states
	public static final int STATE_GONE = 0;
	public static final int STATE_UNFINISHED = 1;
	public static final int STATE_FINISHED = 2;
	public static final int STATE_UNCHECKED = 3;
	public static final int STATE_CHECKED = 4;
	
	// Views
	private ImageView mLargeCheckImageView, mSmallCheckImageView, mFrameImageView;
	private TextView mWagonNumberTextView, mModelNumberTextView, mUsernameTextView, mDateTextView;
	
	public TaskView(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (isInEditMode()) { return; }
		LayoutInflater.from(getContext()).inflate(R.layout.view_taskview, this);
		
		mLargeCheckImageView = (ImageView)findViewById(R.id.largeCheckImageView);
		mSmallCheckImageView = (ImageView)findViewById(R.id.smallCheckImageView);
		mFrameImageView = (ImageView)findViewById(R.id.frameImageView);
		mWagonNumberTextView = (TextView)findViewById(R.id.wagonNumberView);
		mModelNumberTextView = (TextView)findViewById(R.id.modelNumberView);
		mUsernameTextView = (TextView)findViewById(R.id.usernameView);
		mDateTextView = (TextView)findViewById(R.id.dateView);
		
		// From XML
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TaskView, 0, 0);
		try {
			setIconState(a.getInt(R.styleable.TaskView_iconState, STATE_UNFINISHED));
			setWagonNumber(a.getString(R.styleable.TaskView_wagonNumber));
			setModelNumber(a.getString(R.styleable.TaskView_modelNumber));
			setUsername(a.getString(R.styleable.TaskView_username));
			setDate(a.getString(R.styleable.TaskView_date));
		} finally {
			a.recycle();
		}
	}

	public void setWagonNumber(String wagonNumber) {
		mWagonNumberTextView.setText(wagonNumber);
	}
	
	public void setWagonID(int id) {
		mWagonNumberTextView.setText(DBHelper.WagonTable.queryName(id));
	}

	public void setModelNumber(String modelNumber) {
		mModelNumberTextView.setText(modelNumber);
	}
	
	public void setModelID(int id) {
		mModelNumberTextView.setText(DBHelper.WagonTable.queryName(id));
	}

	public void setUsername(String username) {
		mUsernameTextView.setText(username);
	}

	public void setDate(String date) {
		mDateTextView.setText(date);
	}

	public void setFinished(boolean b) {
		if (b) {
			setIconState(STATE_FINISHED);
		} else {
			setIconState(STATE_UNFINISHED);
		}
	}
	
	public void setChecked(boolean b) {
		if (b) {
			setIconState(STATE_CHECKED);
		} else {
			setIconState(STATE_UNCHECKED);
		}
	}
	
	public void setIconState(int iconState) {
		switch (iconState) {
		case STATE_UNFINISHED:
			mLargeCheckImageView.setVisibility(INVISIBLE);
			mSmallCheckImageView.setVisibility(INVISIBLE);
			mFrameImageView.setVisibility(INVISIBLE);
			break;
		case STATE_FINISHED:
			mLargeCheckImageView.setVisibility(VISIBLE);
			mSmallCheckImageView.setVisibility(INVISIBLE);
			mFrameImageView.setVisibility(INVISIBLE);			
			break;
		case STATE_UNCHECKED:
			mLargeCheckImageView.setVisibility(INVISIBLE);
			mSmallCheckImageView.setVisibility(INVISIBLE);
			mFrameImageView.setVisibility(VISIBLE);
			break;
		case STATE_CHECKED:
			mLargeCheckImageView.setVisibility(INVISIBLE);
			mSmallCheckImageView.setVisibility(VISIBLE);
			mFrameImageView.setVisibility(VISIBLE);
			break;
		case STATE_GONE:
		default:
			mLargeCheckImageView.setVisibility(GONE);
			mSmallCheckImageView.setVisibility(GONE);
			mFrameImageView.setVisibility(GONE);
			break;
		}
	}
}
