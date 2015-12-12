package com.example.wagontester.db;

import android.net.Uri;
import android.provider.BaseColumns;

/*
 * 契约文件
 */
public class DBContract {
	public static final int VERSION = 7;
	
	// The same as "android:authorities" in AndroidManifest.xml
	public static String AUTHORITY = "com.example.wagontester.db";
	
	private static final String PROTOCOL = "content://";
	
	public static final int URI_USER = 1; // 用户
	public static final int URI_USER_ITEM = 2;
	
	public static final int URI_DUTY = 3; // 职责
	public static final int URI_DUTY_ITEM = 4;
	public static final int URI_PART = 5; // 部件
	public static final int URI_PART_ITEM = 6;
	public static final int URI_FAULT = 7; // 故障
	public static final int URI_FAULT_ITEM = 8;
	
	public static final int URI_MODEL = 9; // 车辆
	public static final int URI_MODEL_ITEM = 10;
	public static final int URI_TASK = 11; // 任务
	public static final int URI_TASK_ITEM = 12;
	public static final int URI_CONTENT = 13; // 内容
	public static final int URI_CONTENT_ITEM = 14;
		
	public static final String TYPE_USER = "vnd.android.cursor.dir/com.example.wagontester.db.user"; 
	public static final String TYPE_USER_ITEM = "vnd.android.cursor.item/com.example.wagontester.db.user"; 
	public static final String TYPE_DUTY = "vnd.android.cursor.dir/com.example.wagontester.db.duty"; 
	public static final String TYPE_DUTY_ITEM = "vnd.android.cursor.item/com.example.wagontester.db.duty";
	public static final String TYPE_PART = "vnd.android.cursor.dir/com.example.wagontester.db.part"; 
	public static final String TYPE_PART_ITEM = "vnd.android.cursor.item/com.example.wagontester.db.part";
	public static final String TYPE_FAULT = "vnd.android.cursor.dir/com.example.wagontester.db.fault"; 
	public static final String TYPE_FAULT_ITEM = "vnd.android.cursor.item/com.example.wagontester.db.fault";
	public static final String TYPE_MODEL = "vnd.android.cursor.dir/com.example.wagontester.db.model"; 
	public static final String TYPE_MODEL_ITEM = "vnd.android.cursor.item/com.example.wagontester.db.model";
	public static final String TYPE_TASK = "vnd.android.cursor.dir/com.example.wagontester.db.task"; 
	public static final String TYPE_TASK_ITEM = "vnd.android.cursor.item/com.example.wagontester.db.task";
	public static final String TYPE_CONTENT = "vnd.android.cursor.dir/com.example.wagontester.db.content"; 
	public static final String TYPE_CONTENT_ITEM = "vnd.android.cursor.item/com.example.wagontester.db.content";
	
	/*
	 * User Table
	 */
	public static final class UserTable implements BaseColumns {
		public static final String TABLE_NAME = "UserTable";
		public static final Uri CONTENT_URI = Uri.parse(PROTOCOL + AUTHORITY + "/" + TABLE_NAME);
		
		public static final int POS_ID = 0;
		public static final int POS_NAME = 1;
		
		public static final String KEY_NAME = "user_name";
		
		public static final String CMD_CREATE = 
				"create table " + TABLE_NAME + "(" + 
				_ID + " integer primary key autoincrement, " +
				KEY_NAME + " text" +
				" ) ";
		public static final String CMD_DROP = "drop table if exists " + TABLE_NAME;
	}
	
	/*
	 * Duty Table
	 */
	public static final class DutyTable implements BaseColumns {
		public static final String TABLE_NAME = "DutyTable";
		public static final Uri CONTENT_URI = Uri.parse(PROTOCOL + AUTHORITY + "/" + TABLE_NAME);
		
		public static final int POS_ID = 0;
		public static final int POS_NAME = 1;
		
		public static final String KEY_NAME = "duty_name";
		
		public static final String CMD_CREATE = 
				"create table " + TABLE_NAME + "(" + 
				_ID + " integer primary key autoincrement, " +
				KEY_NAME + " text" +
				" ) ";
		public static final String CMD_DROP = "drop table if exists " + TABLE_NAME;
	}
	
	/*
	 * Part Table
	 */
	public static final class PartTable implements BaseColumns {
		public static final String TABLE_NAME = "PartTable";
		public static final Uri CONTENT_URI = Uri.parse(PROTOCOL + AUTHORITY + "/" + TABLE_NAME);
		
		public static final int POS_ID = 0;
		public static final int POS_NAME = 1;
		public static final int POS_DUTY = 2;
		
		public static final String KEY_NAME = "part_name";
		public static final String KEY_DUTY = "duty_id";
		
		public static final String CMD_CREATE = 
				"create table " + TABLE_NAME + "(" + 
				_ID + " integer primary key autoincrement, " +
				KEY_NAME + " text, " +
				KEY_DUTY + " integer " +
				" ) ";
		public static final String CMD_DROP = "drop table if exists " + TABLE_NAME;
	}
	
	/*
	 * Fault Table
	 */
	public static final class FaultTable implements BaseColumns {
		public static final String TABLE_NAME = "FaultTable";
		public static final Uri CONTENT_URI = Uri.parse(PROTOCOL + AUTHORITY + "/" + TABLE_NAME);
		
		public static final int POS_ID = 0;
		public static final int POS_PART = 1;
		public static final int POS_NAME = 2;
		
		public static final String KEY_PART = "part_name";
		public static final String KEY_NAME = "fault_name";
		
		public static final String CMD_CREATE = 
				"create table " + TABLE_NAME + "(" + 
				_ID + " integer primary key autoincrement, " +
				KEY_PART + " text, " +
				KEY_NAME + " text " +
				" ) ";
		public static final String CMD_DROP = "drop table if exists " + TABLE_NAME;
	}
	
	/*
	 * Model Table
	 */
	public static final class ModelTable implements BaseColumns {
		public static final String TABLE_NAME = "ModelTable";
		public static final Uri CONTENT_URI = Uri.parse(PROTOCOL + AUTHORITY + "/" + TABLE_NAME);
		
		public static final int POS_ID = 0;
		public static final int POS_NAME = 1;
		
		public static final String KEY_NAME = "model_name";
		
		public static final String CMD_CREATE = 
				"create table " + TABLE_NAME + "(" + 
				_ID + " integer primary key autoincrement, " +
				KEY_NAME + " text " +
				" ) ";
		public static final String CMD_DROP = "drop table if exists " + TABLE_NAME;
	}
	
	/*
	 * Task Table
	 */
	public static final class TaskTable implements BaseColumns {
		public static final String TABLE_NAME = "TaskTable";
		public static final Uri CONTENT_URI = Uri.parse(PROTOCOL + AUTHORITY + "/" + TABLE_NAME);
		
		public static final int POS_ID = 0;
		public static final int POS_USER = 1;
		public static final int POS_DUTY = 2;
		public static final int POS_MODEL = 3;
		public static final int POS_WAGON = 4;
		public static final int POS_PLATFORM = 5;
		public static final int POS_DATE = 6;
		public static final int POS_STATUS = 7;
		
		public static final String KEY_USER = "user_id";
		public static final String KEY_DUTY = "duty_id";
		public static final String KEY_MODEL = "model_number";
		public static final String KEY_WAGON = "wagon_number";
		public static final String KEY_PLATFORM = "platform_number";
		public static final String KEY_DATE = "date";
		public static final String KEY_STATUS = "status";
		
		public static final String CMD_CREATE = 
				"create table " + TABLE_NAME + "(" + 
				_ID + " integer primary key autoincrement, " +
				KEY_USER + " integer, " +
				KEY_DUTY + " integer, " +
				KEY_MODEL + " text, " +
				KEY_WAGON + " text, " +
				KEY_PLATFORM + " text, " +
				KEY_DATE + " text, " +
				KEY_STATUS + " integer " +
				" ) ";
		public static final String CMD_DROP = "drop table if exists " + TABLE_NAME;
	}
	
	/*
	 * Content Table
	 */
	public static final class ContentTable implements BaseColumns {
		public static final String TABLE_NAME = "ContentTable";
		public static final Uri CONTENT_URI = Uri.parse(PROTOCOL + AUTHORITY + "/" + TABLE_NAME);
		
		public static final int POS_ID = 0;
		public static final int POS_TASK = 1;
		public static final int POS_PART = 2;
		public static final int POS_FAULT = 3;
		public static final int POS_IMAGE = 4;
		
		public static final String KEY_TASK = "task_id";
		public static final String KEY_PART = "part_name";
		public static final String KEY_FAULT = "fault_name";
		public static final String KEY_IMAGE = "image_path";
		
		public static final String CMD_CREATE = 
				"create table " + TABLE_NAME + "(" + 
				_ID + " integer primary key autoincrement, " +
				KEY_TASK + " integer, " +
				KEY_PART + " text, " +
				KEY_FAULT + " text, " +
				KEY_IMAGE + " text " +
				" ) ";
		public static final String CMD_DROP = "drop table if exists " + TABLE_NAME;
	}
}
