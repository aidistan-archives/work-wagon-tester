package com.example.wagontester.db;

import android.content.ContentResolver;
import android.content.ContentValues;

public class DBPresetter {
	public static final int VERSION = 11;

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
			cv_part.put(DBContract.PartTable.KEY_NAME, "1位上拉杆与1位移动杠杆组装圆销及开口销");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "2位上拉杆与2位移动杠杆组装圆销及开口销");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "制动缸前杠杆与闸调器（中拉杆）组装圆销及开口销");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "闸调器控制杆与控制杠杆组装圆销及开口销");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "上拉杆与制动缸前杠杆组装圆销及开口销");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "制动缸后杠杆与闸调器（中拉杆）组装圆销及开口销");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "制动缸后杠杆与后杠杆支点座组装圆销及开口销");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "制动缸后杠杆与上拉条组装圆销及开口销");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
		cv_duty.put(DBContract.DutyTable.KEY_NAME, "钩缓");
		duty = Integer.parseInt(cr.insert(DBContract.DutyTable.CONTENT_URI, cv_duty).getLastPathSegment());
		cv_part.put(DBContract.PartTable.KEY_DUTY, duty);
			cv_part.put(DBContract.PartTable.KEY_NAME, "车钩铸造标记");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "使用样板测量全开位");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "使用样板测量闭锁位");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "使用样板测量车钩防跳性能");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
		cv_duty.put(DBContract.DutyTable.KEY_NAME, "转向架落成");
		duty = Integer.parseInt(cr.insert(DBContract.DutyTable.CONTENT_URI, cv_duty).getLastPathSegment());
		cv_part.put(DBContract.PartTable.KEY_DUTY, duty);
			cv_part.put(DBContract.PartTable.KEY_NAME, "侧架方框上方台位标识");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "侧架铸造标记");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "中拉杆与移动杠杆组装扁孔圆销及扁开口销");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "制动梁支柱与移动杠杆组装扁孔圆销及扁开口销");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "固定杠杆与固定杠杆支点座组装扁孔圆销及扁开口销");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "中拉杆与固定杠杆组装扁孔圆销及扁开口销");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "制动梁支柱与固定杠杆组装扁孔圆销及扁开口销");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "下旁承上平面与下心盘上平面距离尺寸");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "心盘销孔、下心盘面清洁程度");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "下旁承垫板数量");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
		cv_duty.put(DBContract.DutyTable.KEY_NAME, "轮对支出");
		duty = Integer.parseInt(cr.insert(DBContract.DutyTable.CONTENT_URI, cv_duty).getLastPathSegment());
		cv_part.put(DBContract.PartTable.KEY_DUTY, duty);
			cv_part.put(DBContract.PartTable.KEY_NAME, "左端标志板");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "左侧轴承前盖、后挡组装状态");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "右侧轴承前盖、后挡组装状态");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "轮轴整体图片");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
		cv_duty.put(DBContract.DutyTable.KEY_NAME, "整车落成");
		duty = Integer.parseInt(cr.insert(DBContract.DutyTable.CONTENT_URI, cv_duty).getLastPathSegment());
		cv_part.put(DBContract.PartTable.KEY_DUTY, duty);
			cv_part.put(DBContract.PartTable.KEY_NAME, "大车型车号标记");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "1位车钩缓冲装置钩尾销防脱机构组装状态");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "2位车钩缓冲装置钩尾销防脱机构组装状态");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "制动缸前杠杆与闸调器（中拉杆）组装圆销及开口销");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "闸调器控制杆与控制杠杆组装圆销及开口销");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "上拉杆与制动缸前杠杆组装圆销及开口销");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "制动缸后杠杆与闸调器（中拉杆）组装圆销及开口销");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "制动缸后杠杆与后杠杆支点座组装圆销及开口销");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "制动缸后杠杆与上拉条组装圆销及开口销");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "上、下旁承间隙尺寸（1）");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "上、下旁承间隙尺寸（2）");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "上、下旁承间隙尺寸（3）");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "上、下旁承间隙尺寸（4）");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "车钩高度（采用车钩高度检测尺）（1）");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
			cv_part.put(DBContract.PartTable.KEY_NAME, "车钩高度（采用车钩高度检测尺）（2）");cr.insert(DBContract.PartTable.CONTENT_URI, cv_part);
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
		cv.put(DBContract.FaultTable.KEY_PART, "1位上拉杆与1位移动杠杆组装圆销及开口销");
			cv.put(DBContract.FaultTable.KEY_NAME, "裂纹");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "间隙超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "铸造缺陷");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "作用不良");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "破损");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "错装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "漏装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "2位上拉杆与2位移动杠杆组装圆销及开口销");
			cv.put(DBContract.FaultTable.KEY_NAME, "裂纹");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "间隙超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "铸造缺陷");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "作用不良");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "破损");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "错装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "漏装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "制动缸前杠杆与闸调器（中拉杆）组装圆销及开口销");
			cv.put(DBContract.FaultTable.KEY_NAME, "裂纹");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "间隙超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "铸造缺陷");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "作用不良");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "破损");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "错装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "漏装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "闸调器控制杆与控制杠杆组装圆销及开口销");
			cv.put(DBContract.FaultTable.KEY_NAME, "裂纹");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "间隙超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "铸造缺陷");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "作用不良");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "破损");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "错装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "漏装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "上拉杆与制动缸前杠杆组装圆销及开口销");
			cv.put(DBContract.FaultTable.KEY_NAME, "裂纹");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "间隙超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "铸造缺陷");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "作用不良");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "破损");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "错装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "漏装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "制动缸后杠杆与闸调器（中拉杆）组装圆销及开口销");
			cv.put(DBContract.FaultTable.KEY_NAME, "裂纹");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "间隙超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "铸造缺陷");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "作用不良");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "破损");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "错装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "漏装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "制动缸后杠杆与后杠杆支点座组装圆销及开口销");
			cv.put(DBContract.FaultTable.KEY_NAME, "裂纹");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "间隙超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "铸造缺陷");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "作用不良");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "破损");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "错装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "漏装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "制动缸后杠杆与上拉条组装圆销及开口销");
			cv.put(DBContract.FaultTable.KEY_NAME, "裂纹");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "间隙超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "铸造缺陷");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "作用不良");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "破损");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "错装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "漏装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "车钩铸造标记");
			cv.put(DBContract.FaultTable.KEY_NAME, "不清");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "寿命过期");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "使用样板测量全开位");
			cv.put(DBContract.FaultTable.KEY_NAME, "间隙超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "使用样板测量闭锁位");
			cv.put(DBContract.FaultTable.KEY_NAME, "间隙超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "使用样板测量车钩防跳性能");
			cv.put(DBContract.FaultTable.KEY_NAME, "作用不良");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "侧架方框上方台位标识");
			cv.put(DBContract.FaultTable.KEY_NAME, "不清");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "错误");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "侧架铸造标记");
			cv.put(DBContract.FaultTable.KEY_NAME, "不清");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "错误");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "中拉杆与移动杠杆组装扁孔圆销及扁开口销");
			cv.put(DBContract.FaultTable.KEY_NAME, "裂纹");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "间隙超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "铸造缺陷");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "作用不良");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "破损");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "错装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "漏装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "制动梁支柱与移动杠杆组装扁孔圆销及扁开口销");
			cv.put(DBContract.FaultTable.KEY_NAME, "裂纹");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "间隙超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "铸造缺陷");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "作用不良");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "破损");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "错装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "漏装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "固定杠杆与固定杠杆支点座组装扁孔圆销及扁开口销");
			cv.put(DBContract.FaultTable.KEY_NAME, "裂纹");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "间隙超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "铸造缺陷");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "作用不良");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "破损");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "错装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "漏装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "中拉杆与固定杠杆组装扁孔圆销及扁开口销");
			cv.put(DBContract.FaultTable.KEY_NAME, "裂纹");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "间隙超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "铸造缺陷");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "作用不良");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "破损");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "错装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "漏装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "制动梁支柱与固定杠杆组装扁孔圆销及扁开口销");
			cv.put(DBContract.FaultTable.KEY_NAME, "裂纹");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "间隙超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "磨耗或腐蚀过限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "铸造缺陷");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "作用不良");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "破损");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "错装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "漏装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "下旁承上平面与下心盘上平面距离尺寸");
			cv.put(DBContract.FaultTable.KEY_NAME, "距离超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "错装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "漏装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "心盘销孔、下心盘面清洁程度");
			cv.put(DBContract.FaultTable.KEY_NAME, "有异物");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "下旁承垫板数量");
			cv.put(DBContract.FaultTable.KEY_NAME, "数量超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "左端标志板");
			cv.put(DBContract.FaultTable.KEY_NAME, "标志板错打");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "车轴过期");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "轴承过期");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "左侧轴承前盖、后挡组装状态");
			cv.put(DBContract.FaultTable.KEY_NAME, "轴端螺栓标记不齐全");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "螺栓寿命过期");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "螺栓松动");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "防松片止耳撬起不到位");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "施封锁状态不良");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "密封罩脱出");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "油封失效");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "右侧轴承前盖、后挡组装状态");
			cv.put(DBContract.FaultTable.KEY_NAME, "轴端螺栓标记不齐全");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "螺栓寿命过期");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "螺栓松动");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "防松片止耳撬起不到位");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "施封锁状态不良");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "密封罩脱出");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "油封失效");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "轮轴整体图片");
			cv.put(DBContract.FaultTable.KEY_NAME, "三级交验标记不符合规范");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "经旋修的轮对踏面粗糙度不符合要求");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "各部尺寸过限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "1位车钩缓冲装置钩尾销防脱机构组装状态");
			cv.put(DBContract.FaultTable.KEY_NAME, "裂纹");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "作用不良");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "破损");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "错装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "漏装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "2位车钩缓冲装置钩尾销防脱机构组装状态");
			cv.put(DBContract.FaultTable.KEY_NAME, "裂纹");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "弯曲变形");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "作用不良");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "破损");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "错装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
			cv.put(DBContract.FaultTable.KEY_NAME, "漏装");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "上、下旁承间隙尺寸（1）");
			cv.put(DBContract.FaultTable.KEY_NAME, "超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "上、下旁承间隙尺寸（2）");
			cv.put(DBContract.FaultTable.KEY_NAME, "超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "上、下旁承间隙尺寸（3）");
			cv.put(DBContract.FaultTable.KEY_NAME, "超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "上、下旁承间隙尺寸（4）");
			cv.put(DBContract.FaultTable.KEY_NAME, "超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "车钩高度（采用车钩高度检测尺）（1）");
			cv.put(DBContract.FaultTable.KEY_NAME, "超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
		cv.put(DBContract.FaultTable.KEY_PART, "车钩高度（采用车钩高度检测尺）（2）");
			cv.put(DBContract.FaultTable.KEY_NAME, "超限");cr.insert(DBContract.FaultTable.CONTENT_URI, cv);
	}

}
