package com.example.wagontester.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "wagontester.db";
	public static final int    DATABASE_VERSION = 4;
	
	// Singleton
	static DBHelper mInstance = null;
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public static DBHelper getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new DBHelper(context);
		}
		return mInstance;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DBContract.UserTable.CMD_CREATE);
		db.execSQL(DBContract.DutyTable.CMD_CREATE);
		db.execSQL(DBContract.PartTable.CMD_CREATE);
		db.execSQL(DBContract.FaultTable.CMD_CREATE);
		db.execSQL(DBContract.ModelTable.CMD_CREATE);
		db.execSQL(DBContract.TaskTable.CMD_CREATE);
		db.execSQL(DBContract.ContentTable.CMD_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DBContract.UserTable.CMD_DROP);
		db.execSQL(DBContract.DutyTable.CMD_DROP);
		db.execSQL(DBContract.PartTable.CMD_DROP);
		db.execSQL(DBContract.FaultTable.CMD_DROP);
		db.execSQL(DBContract.ModelTable.CMD_DROP);
		db.execSQL(DBContract.TaskTable.CMD_DROP);
		db.execSQL(DBContract.ContentTable.CMD_DROP);
		
		onCreate(db);
	}
}
