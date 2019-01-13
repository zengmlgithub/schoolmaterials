package com.nkl.common.util;

import java.util.Calendar;

import org.apache.log4j.Logger;

import com.nkl.common.dao.BaseDao;

public class DataUtils {
	private static Logger log = Logger.getLogger(DataUtils.class);
	public static String getDateString() {
		String no = "";
		Calendar cal = Calendar.getInstance();
		String yearStr = cal.get(Calendar.YEAR) + "";
		String monthStr = getStr(cal.get(Calendar.MONTH) + 1);
		String dayStr = getStr(cal.get(Calendar.DAY_OF_MONTH));
		
		String hour = getStr(cal.get(Calendar.HOUR_OF_DAY));
		String minute = getStr(cal.get(Calendar.MINUTE));
		String seconds = getStr(cal.get(Calendar.SECOND));
		String millsecondStr = getStr(cal.get(Calendar.MILLISECOND));
		
		no += yearStr;
		no += monthStr;
		no += dayStr;
		no += hour;
		no += minute;
		no += seconds;
		no += ("_" +millsecondStr);
		
		log.info("create no:" + no);
		return no;
	}
	
	private static String getStr(int temp) {
		return temp < 10? "0" + temp:temp + "";
	}
}
