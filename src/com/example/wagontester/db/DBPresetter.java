package com.example.wagontester.db;

import android.content.ContentResolver;
import android.content.ContentValues;

/*
 *  Preset Tables
 */
public class DBPresetter {
	public static final int VERSION = 10;
	
	private static ContentResolver cr;
	
	public static void preset(ContentResolver contentResolver) {
		cr = contentResolver;
		
		set_users();
		set_duties();
		set_faults();
		set_models();
	}

	private static void set_users() {
		ContentValues cv = new ContentValues();
		cv.put(DBContract.UserTable.KEY_NAME, "白志刚"); cr.insert(DBContract.UserTable.CONTENT_URI, cv);
		cv.put(DBContract.UserTable.KEY_NAME, "缪立群"); cr.insert(DBContract.UserTable.CONTENT_URI, cv);
		cv.put(DBContract.UserTable.KEY_NAME, "李贵鹏"); cr.insert(DBContract.UserTable.CONTENT_URI, cv);
		cv.put(DBContract.UserTable.KEY_NAME, "茅春生"); cr.insert(DBContract.UserTable.CONTENT_URI, cv);
		cv.put(DBContract.UserTable.KEY_NAME, "赵杰"); cr.insert(DBContract.UserTable.CONTENT_URI, cv);
		cv.put(DBContract.UserTable.KEY_NAME, "陈峰"); cr.insert(DBContract.UserTable.CONTENT_URI, cv);
		cv.put(DBContract.UserTable.KEY_NAME, "马春鹏"); cr.insert(DBContract.UserTable.CONTENT_URI, cv);
		cv.put(DBContract.UserTable.KEY_NAME, "冯毅"); cr.insert(DBContract.UserTable.CONTENT_URI, cv);
		cv.put(DBContract.UserTable.KEY_NAME, "孙长睿"); cr.insert(DBContract.UserTable.CONTENT_URI, cv);
	}
	private static void set_models() {
		ContentValues cv = new ContentValues();
		cv.put(DBContract.ModelTable.KEY_NAME, "C16"); cr.insert(DBContract.ModelTable.CONTENT_URI, cv);
		cv.put(DBContract.ModelTable.KEY_NAME, "C16A"); cr.insert(DBContract.ModelTable.CONTENT_URI, cv);
		cv.put(DBContract.ModelTable.KEY_NAME, "C61"); cr.insert(DBContract.ModelTable.CONTENT_URI, cv);
		cv.put(DBContract.ModelTable.KEY_NAME, "C61K"); cr.insert(DBContract.ModelTable.CONTENT_URI, cv);
		cv.put(DBContract.ModelTable.KEY_NAME, "C61T"); cr.insert(DBContract.ModelTable.CONTENT_URI, cv);
	}
	private static void set_duties() {
		int duty;
		ContentValues cv_duty = new ContentValues();
		ContentValues cv_part = new ContentValues();

		cv_duty.put(DBContract.DutyTable.KEY_NAME, "车体");
		duty = Integer.parseInt(cr.insert(DBContract.DutyTable.CONTENT_URI, cv_duty).getLastPathSegment());
		cv_part.put(DBContract.PartTable.KEY_DUTY, duty);
			cv_part.put(DBContract.PartTable.KEY_NAME, "大车型车号标记");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "牵引梁外侧（1）");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "牵引梁外侧（2）");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "牵引梁外侧（3）");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "牵引梁外侧（4）");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
		cv_duty.put(DBContract.DutyTable.KEY_NAME, "制连");
		duty = Integer.parseInt(cr.insert(DBContract.DutyTable.CONTENT_URI, cv_duty).getLastPathSegment());
		cv_part.put(DBContract.PartTable.KEY_DUTY, duty);
			cv_part.put(DBContract.PartTable.KEY_NAME, "大车型车号标记");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
	}
	private static void set_faults() {
		ContentValues cv = new ContentValues();
		cv.put(DBContract.FaultTable.KEY_PART, "大车型车号标记");
			cv.put(DBContract.FaultTable.KEY_NAME, "不清");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "错误");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "牵引梁外侧（1）");
			cv.put(DBContract.FaultTable.KEY_NAME, "裂纹");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "破损");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "牵引梁外侧（2）");
			cv.put(DBContract.FaultTable.KEY_NAME, "裂纹");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "破损");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "牵引梁外侧（3）");
			cv.put(DBContract.FaultTable.KEY_NAME, "裂纹");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "破损");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "牵引梁外侧（4）");
			cv.put(DBContract.FaultTable.KEY_NAME, "裂纹");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "破损");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
	}
}
