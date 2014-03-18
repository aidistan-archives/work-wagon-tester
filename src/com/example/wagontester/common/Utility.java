package com.example.wagontester.common;

import java.io.File;
import java.util.ArrayList;

import com.example.wagontester.db.DBContract;

import android.content.Context;
import android.database.Cursor;

public class Utility {
	
	public static void deleteUnlinkedPhotos(Context context) {
		ArrayList<String> fileInDatabase = new ArrayList<String>();
		Cursor c = context.getContentResolver().query(DBContract.ContentTable.CONTENT_URI, 
				new String[] {DBContract.ContentTable.KEY_IMAGE}, null, null, null);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			if (!fileInDatabase.contains(c.getString(0))) {
				fileInDatabase.add(c.getString(0));
			}
		}
		
		for(File f : context.getFilesDir().listFiles()) {
			if(!fileInDatabase.contains(f.getAbsolutePath())) {
				f.delete();
			}
		}
	}
	
	public static void deleteTask(Context context, int task_id) {
		context.getContentResolver().delete(DBContract.TaskTable.CONTENT_URI, 
				"_id=" + String.valueOf(task_id), null);
		context.getContentResolver().delete(DBContract.ContentTable.CONTENT_URI, 
				DBContract.ContentTable.KEY_TASK + "=" + String.valueOf(task_id), null);
		deleteUnlinkedPhotos(context);
	}
}
