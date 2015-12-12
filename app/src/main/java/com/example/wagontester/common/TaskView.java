package com.example.wagontester.common;

import com.example.wagontester.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TaskView extends LinearLayout {

	// Views
	public ImageView imageView;
	public TextView wagonView, userView, dutyView, modelView, platformView, dateView;

	public TaskView(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (isInEditMode()) { return; }
		LayoutInflater.from(getContext()).inflate(R.layout.view_task, this);

		imageView = (ImageView)findViewById(R.id.imageView);
		wagonView = (TextView)findViewById(R.id.wagonView);
		userView = (TextView)findViewById(R.id.userView);
		dutyView = (TextView)findViewById(R.id.dutyView);
		modelView = (TextView)findViewById(R.id.modelView);
		platformView = (TextView)findViewById(R.id.platformView);
		dateView = (TextView)findViewById(R.id.dateView);
	}
}
