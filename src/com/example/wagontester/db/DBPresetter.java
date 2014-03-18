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
		cv.put(DBContract.UserTable.KEY_NAME, "��־��"); cr.insert(DBContract.UserTable.CONTENT_URI, cv);
		cv.put(DBContract.UserTable.KEY_NAME, "����Ⱥ"); cr.insert(DBContract.UserTable.CONTENT_URI, cv);
		cv.put(DBContract.UserTable.KEY_NAME, "�����"); cr.insert(DBContract.UserTable.CONTENT_URI, cv);
		cv.put(DBContract.UserTable.KEY_NAME, "é����"); cr.insert(DBContract.UserTable.CONTENT_URI, cv);
		cv.put(DBContract.UserTable.KEY_NAME, "�Խ�"); cr.insert(DBContract.UserTable.CONTENT_URI, cv);
		cv.put(DBContract.UserTable.KEY_NAME, "�·�"); cr.insert(DBContract.UserTable.CONTENT_URI, cv);
		cv.put(DBContract.UserTable.KEY_NAME, "����"); cr.insert(DBContract.UserTable.CONTENT_URI, cv);
		cv.put(DBContract.UserTable.KEY_NAME, "����"); cr.insert(DBContract.UserTable.CONTENT_URI, cv);
		cv.put(DBContract.UserTable.KEY_NAME, "�ﳤ�"); cr.insert(DBContract.UserTable.CONTENT_URI, cv);
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

		cv_duty.put(DBContract.DutyTable.KEY_NAME, "����");
		duty = Integer.parseInt(cr.insert(DBContract.DutyTable.CONTENT_URI, cv_duty).getLastPathSegment());
		cv_part.put(DBContract.PartTable.KEY_DUTY, duty);
			cv_part.put(DBContract.PartTable.KEY_NAME, "���ͳ��ű��");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "ǣ������ࣨ1��");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "ǣ������ࣨ2��");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "ǣ������ࣨ3��");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "ǣ������ࣨ4��");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
		cv_duty.put(DBContract.DutyTable.KEY_NAME, "����");
		duty = Integer.parseInt(cr.insert(DBContract.DutyTable.CONTENT_URI, cv_duty).getLastPathSegment());
		cv_part.put(DBContract.PartTable.KEY_DUTY, duty);
			cv_part.put(DBContract.PartTable.KEY_NAME, "���ͳ��ű��");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
	}
	private static void set_faults() {
		ContentValues cv = new ContentValues();
		cv.put(DBContract.FaultTable.KEY_PART, "���ͳ��ű��");
			cv.put(DBContract.FaultTable.KEY_NAME, "����");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "����");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "ǣ������ࣨ1��");
			cv.put(DBContract.FaultTable.KEY_NAME, "����");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "ĥ�Ļ�ʴ����");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "��������");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "����");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "ǣ������ࣨ2��");
			cv.put(DBContract.FaultTable.KEY_NAME, "����");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "ĥ�Ļ�ʴ����");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "��������");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "����");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "ǣ������ࣨ3��");
			cv.put(DBContract.FaultTable.KEY_NAME, "����");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "ĥ�Ļ�ʴ����");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "��������");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "����");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "ǣ������ࣨ4��");
			cv.put(DBContract.FaultTable.KEY_NAME, "����");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "ĥ�Ļ�ʴ����");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "��������");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "����");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
	}
}
