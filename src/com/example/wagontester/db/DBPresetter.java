package com.example.wagontester.db;

import android.content.ContentResolver;
import android.content.ContentValues;

/*
 *  Preset Tables
 */
public class DBPresetter {
	public static final int VERSION = 3;
	
	private static ContentResolver cr;
	
	public static void preset(ContentResolver contentResolver) {
		cr = contentResolver;
		
		set_users();
		set_duties();
		set_models();
		
		ContentValues cv = new ContentValues();
		cv.put(DBContract.TaskTable.KEY_USER, 1);
		cv.put(DBContract.TaskTable.KEY_DUTY, 1);
		cv.put(DBContract.TaskTable.KEY_MODEL, 1);
		cv.put(DBContract.TaskTable.KEY_PLATFORM, "12");
		cv.put(DBContract.TaskTable.KEY_DATE, "2014/03/17");
		cv.put(DBContract.TaskTable.KEY_WAGON, "123456");
		cv.put(DBContract.TaskTable.KEY_STATUS, 0);
		cr.insert(DBContract.TaskTable.CONTENT_URI, cv);
		
		cv = new ContentValues();
		cv.put(DBContract.TaskTable.KEY_USER, 4);
		cv.put(DBContract.TaskTable.KEY_DUTY, 1);
		cv.put(DBContract.TaskTable.KEY_MODEL, 1);
		cv.put(DBContract.TaskTable.KEY_PLATFORM, "12");
		cv.put(DBContract.TaskTable.KEY_DATE, "2014/03/16");
		cv.put(DBContract.TaskTable.KEY_WAGON, "123456");
		cv.put(DBContract.TaskTable.KEY_STATUS, 1);
		cr.insert(DBContract.TaskTable.CONTENT_URI, cv);
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

	private static void set_duties() {
		int duty;
		int part;
		ContentValues cv_duty = new ContentValues();
		ContentValues cv_part = new ContentValues();
		ContentValues cv_fault = new ContentValues();

		cv_duty.put(DBContract.DutyTable.KEY_NAME, "车体");
		duty = Integer.parseInt(cr.insert(DBContract.DutyTable.CONTENT_URI, cv_duty).getLastPathSegment());
		cv_part.put(DBContract.PartTable.KEY_DUTY, duty);
			cv_part.put(DBContract.PartTable.KEY_NAME, "大车型车号标记");
			part = Integer.parseInt(cr.insert(DBContract.PartTable.CONTENT_URI, cv_part).getLastPathSegment());
			cv_fault.put(DBContract.FaultTable.KEY_PART, part);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "不清");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "错误");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
			cv_part.put(DBContract.PartTable.KEY_NAME, "牵引梁外侧（1）");
			part = Integer.parseInt(cr.insert(DBContract.PartTable.CONTENT_URI, cv_part).getLastPathSegment());
			cv_fault.put(DBContract.FaultTable.KEY_PART, part);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "裂纹");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "破损");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
			cv_part.put(DBContract.PartTable.KEY_NAME, "牵引梁外侧（2）");
			part = Integer.parseInt(cr.insert(DBContract.PartTable.CONTENT_URI, cv_part).getLastPathSegment());
			cv_fault.put(DBContract.FaultTable.KEY_PART, part);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "裂纹");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "破损");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
			cv_part.put(DBContract.PartTable.KEY_NAME, "牵引梁外侧（3）");
			part = Integer.parseInt(cr.insert(DBContract.PartTable.CONTENT_URI, cv_part).getLastPathSegment());
			cv_fault.put(DBContract.FaultTable.KEY_PART, part);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "裂纹");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "破损");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
			cv_part.put(DBContract.PartTable.KEY_NAME, "牵引梁外侧（4）");
			part = Integer.parseInt(cr.insert(DBContract.PartTable.CONTENT_URI, cv_part).getLastPathSegment());
			cv_fault.put(DBContract.FaultTable.KEY_PART, part);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "裂纹");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "破损");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
		cv_duty.put(DBContract.DutyTable.KEY_NAME, "制连");
		duty = Integer.parseInt(cr.insert(DBContract.DutyTable.CONTENT_URI, cv_duty).getLastPathSegment());
		cv_part.put(DBContract.PartTable.KEY_DUTY, duty);
			cv_part.put(DBContract.PartTable.KEY_NAME, "大车型车号标记");
			part = Integer.parseInt(cr.insert(DBContract.PartTable.CONTENT_URI, cv_part).getLastPathSegment());
			cv_fault.put(DBContract.FaultTable.KEY_PART, part);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "不清");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "错误");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
			cv_part.put(DBContract.PartTable.KEY_NAME, "1位上拉杆与1位移动杠杆组装圆销及开口销");
			part = Integer.parseInt(cr.insert(DBContract.PartTable.CONTENT_URI, cv_part).getLastPathSegment());
			cv_fault.put(DBContract.FaultTable.KEY_PART, part);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "裂纹");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "间隙超限");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "铸造缺陷");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "作用不良");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "破损");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "错装");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "漏装");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
			cv_part.put(DBContract.PartTable.KEY_NAME, "2位上拉杆与2位移动杠杆组装圆销及开口销");
			part = Integer.parseInt(cr.insert(DBContract.PartTable.CONTENT_URI, cv_part).getLastPathSegment());
			cv_fault.put(DBContract.FaultTable.KEY_PART, part);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "裂纹");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "间隙超限");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "铸造缺陷");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "作用不良");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "破损");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "错装");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "漏装");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
	}

	private static void set_models() {
		ContentValues cv = new ContentValues();
		cv.put(DBContract.ModelTable.KEY_NAME, "C16"); cr.insert(DBContract.ModelTable.CONTENT_URI, cv);
		cv.put(DBContract.ModelTable.KEY_NAME, "C16A"); cr.insert(DBContract.ModelTable.CONTENT_URI, cv);
		cv.put(DBContract.ModelTable.KEY_NAME, "C61"); cr.insert(DBContract.ModelTable.CONTENT_URI, cv);
		cv.put(DBContract.ModelTable.KEY_NAME, "C61K"); cr.insert(DBContract.ModelTable.CONTENT_URI, cv);
		cv.put(DBContract.ModelTable.KEY_NAME, "C61T"); cr.insert(DBContract.ModelTable.CONTENT_URI, cv);
	}

}
