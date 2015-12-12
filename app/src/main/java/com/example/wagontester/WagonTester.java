package com.example.wagontester;

import java.io.File;
import java.util.ArrayList;

import com.example.wagontester.db.DBContract;

import android.app.Application;
import android.database.Cursor;

public class WagonTester extends Application {
	
	private static WagonTester mApp = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mApp = this;
	}
	
	public static void deleteUnlinkedPhotos() {
		ArrayList<String> fileInDatabase = new ArrayList<String>();
		Cursor c = mApp.getContentResolver().query(DBContract.ContentTable.CONTENT_URI, 
				new String[] {DBContract.ContentTable.KEY_IMAGE}, null, null, null);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			if (!fileInDatabase.contains(c.getString(0))) {
				fileInDatabase.add(c.getString(0));
			}
		}
		
		for(File f : mApp.getFilesDir().listFiles()) {
			if(!fileInDatabase.contains(f.getAbsolutePath())) {
				f.delete();
			}
		}
	}
	
	public static void deleteTask(int task_id) {
		mApp.getContentResolver().delete(DBContract.TaskTable.CONTENT_URI, 
				"_id=" + String.valueOf(task_id), null);
		mApp.getContentResolver().delete(DBContract.ContentTable.CONTENT_URI, 
				DBContract.ContentTable.KEY_TASK + "=" + String.valueOf(task_id), null);
		deleteUnlinkedPhotos();
	}
	
}
