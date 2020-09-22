package com.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author hike97
 * @Description
 * @create 2020-09-03 13:24
 * @Modified By:
 **/
public class DateUtilNotSafe {

	private final  static SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");

	public static Date parse(String dateStr){
		Date date = null;
		if (dateStr!=null){
			try {
				date = sdf.parse (dateStr);
			} catch (ParseException e) {
				e.printStackTrace ();
			}
		}
		return date;
	}
}
