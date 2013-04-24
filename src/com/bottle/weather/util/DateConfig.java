package com.bottle.weather.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateConfig {
	/**
	 * 根据当前系统时间获取未来第n天的日期
	 * 
	 * @param n
	 * @return "yyyy年MM月dd日"格式的时间字符串
	 * @author Bottle
	 * @time 2012.12.20
	 */
	public static String getDate(int n) {

		Date date = new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, n);//把日期往后增加n天.正数往后推,负数往前移动
		date = calendar.getTime(); //这个时间就是日期往后推一天的结果 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		String dateString = formatter.format(date);

		return dateString;

	}

}
