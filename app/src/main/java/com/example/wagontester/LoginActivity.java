package com.example.wagontester;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wagontester.R;
import com.example.wagontester.db.DBContract;

public class LoginActivity extends Activity {

	// SharedPreferences
	private SharedPreferences mSpApp;

	// Views
	private Spinner mUserSpinner;
	private Spinner mDutySpinner;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		mSpApp = getSharedPreferences("app", MODE_PRIVATE);

		mUserSpinner = (Spinner)findViewById(R.id.userSpinner);
		mDutySpinner = (Spinner)findViewById(R.id.dutySpinner);
		refreshSpinners();
	}

	public void login(View view) {
		int user = (int)mUserSpinner.getSelectedItemId();
		int duty = (int)mDutySpinner.getSelectedItemId();

		if(user == AdapterView.INVALID_POSITION) {
			showDialog("请选择用户");
		} else if (duty == AdapterView.INVALID_POSITION) {
			showDialog("请选择岗位");
		} else {
			SharedPreferences.Editor editor = mSpApp.edit();
			editor.putInt("User", user);
			editor.putInt("Duty", duty);
			editor.commit();

			Intent intent = new Intent(this, MainActivity.class);
	    	startActivity(intent);
			this.finish();
		}
	}

	public void create(View view) {
		final EditText editText = new EditText(LoginActivity.this);

		new AlertDialog.Builder(LoginActivity.this)
			.setTitle("新增用户")
			.setView(editText)
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (!editText.getText().toString().isEmpty()) {
						// Create a new user and insert to the database
						ContentValues cv = new ContentValues();
						cv.put(DBContract.UserTable.KEY_NAME, editText.getText().toString());
						ContentResolver cr = getContentResolver();
						cr.insert(DBContract.UserTable.CONTENT_URI, cv);

						// Refresh the adapter and select the user we created
						refreshSpinners();
						mUserSpinner.setSelection(mUserSpinner.getAdapter().getCount() - 1);
					}
				}
			})
			.setNegativeButton("取消", null)
			.show();
	}

	public void delete(View view) {
		int user_id = (int) mUserSpinner.getSelectedItemId();

		if (getContentResolver().query(
			DBContract.TaskTable.CONTENT_URI, new String[] {"_id"}, null, null, null
		).getCount() > 0) {
			showDialog("有任务尚未完成/导出");
		} else if (getContentResolver().query(
			DBContract.UserTable.CONTENT_URI, new String[] {"_id"}, null, null, null
		).getCount() <= 1) {
			showDialog("请至少保留一个用户");
		} else {
			getContentResolver().delete(DBContract.UserTable.CONTENT_URI, "_id=" + String.valueOf(user_id), null);
			refreshSpinners();
		}
	}

	public void refreshSpinners() {
		mUserSpinner.setAdapter(new SpinnerAdapter(this,
				getContentResolver().query(DBContract.UserTable.CONTENT_URI, null, null, null, null),
				DBContract.UserTable.POS_NAME));
		mDutySpinner.setAdapter(new SpinnerAdapter(this,
				getContentResolver().query(DBContract.DutyTable.CONTENT_URI, null, null, null, null),
				DBContract.DutyTable.POS_NAME));
	}

	private void showDialog(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg)
		       .setCancelable(false)
		       .setPositiveButton("确定", null)
			   .show();
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
}
