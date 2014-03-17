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

	private static void set_duties() {
		int duty;
		int part;
		ContentValues cv_duty = new ContentValues();
		ContentValues cv_part = new ContentValues();
		ContentValues cv_fault = new ContentValues();

		cv_duty.put(DBContract.DutyTable.KEY_NAME, "����");
		duty = Integer.parseInt(cr.insert(DBContract.DutyTable.CONTENT_URI, cv_duty).getLastPathSegment());
		cv_part.put(DBContract.PartTable.KEY_DUTY, duty);
			cv_part.put(DBContract.PartTable.KEY_NAME, "���ͳ��ű��");
			part = Integer.parseInt(cr.insert(DBContract.PartTable.CONTENT_URI, cv_part).getLastPathSegment());
			cv_fault.put(DBContract.FaultTable.KEY_PART, part);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
			cv_part.put(DBContract.PartTable.KEY_NAME, "ǣ������ࣨ1��");
			part = Integer.parseInt(cr.insert(DBContract.PartTable.CONTENT_URI, cv_part).getLastPathSegment());
			cv_fault.put(DBContract.FaultTable.KEY_PART, part);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "ĥ�Ļ�ʴ����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "��������");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
			cv_part.put(DBContract.PartTable.KEY_NAME, "ǣ������ࣨ2��");
			part = Integer.parseInt(cr.insert(DBContract.PartTable.CONTENT_URI, cv_part).getLastPathSegment());
			cv_fault.put(DBContract.FaultTable.KEY_PART, part);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "ĥ�Ļ�ʴ����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "��������");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
			cv_part.put(DBContract.PartTable.KEY_NAME, "ǣ������ࣨ3��");
			part = Integer.parseInt(cr.insert(DBContract.PartTable.CONTENT_URI, cv_part).getLastPathSegment());
			cv_fault.put(DBContract.FaultTable.KEY_PART, part);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "ĥ�Ļ�ʴ����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "��������");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
			cv_part.put(DBContract.PartTable.KEY_NAME, "ǣ������ࣨ4��");
			part = Integer.parseInt(cr.insert(DBContract.PartTable.CONTENT_URI, cv_part).getLastPathSegment());
			cv_fault.put(DBContract.FaultTable.KEY_PART, part);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "ĥ�Ļ�ʴ����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "��������");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
		cv_duty.put(DBContract.DutyTable.KEY_NAME, "����");
		duty = Integer.parseInt(cr.insert(DBContract.DutyTable.CONTENT_URI, cv_duty).getLastPathSegment());
		cv_part.put(DBContract.PartTable.KEY_DUTY, duty);
			cv_part.put(DBContract.PartTable.KEY_NAME, "���ͳ��ű��");
			part = Integer.parseInt(cr.insert(DBContract.PartTable.CONTENT_URI, cv_part).getLastPathSegment());
			cv_fault.put(DBContract.FaultTable.KEY_PART, part);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
			cv_part.put(DBContract.PartTable.KEY_NAME, "1λ��������1λ�ƶ��ܸ���װԲ����������");
			part = Integer.parseInt(cr.insert(DBContract.PartTable.CONTENT_URI, cv_part).getLastPathSegment());
			cv_fault.put(DBContract.FaultTable.KEY_PART, part);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "��϶����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "ĥ�Ļ�ʴ����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "��������");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "����ȱ��");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "���ò���");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "��װ");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "©װ");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
			cv_part.put(DBContract.PartTable.KEY_NAME, "2λ��������2λ�ƶ��ܸ���װԲ����������");
			part = Integer.parseInt(cr.insert(DBContract.PartTable.CONTENT_URI, cv_part).getLastPathSegment());
			cv_fault.put(DBContract.FaultTable.KEY_PART, part);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "��϶����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "ĥ�Ļ�ʴ����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "��������");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "����ȱ��");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "���ò���");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "����");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "��װ");
				cr.insert(DBContract.FaultTable.CONTENT_URI, cv_fault);
				cv_fault.put(DBContract.FaultTable.KEY_NAME, "©װ");
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
