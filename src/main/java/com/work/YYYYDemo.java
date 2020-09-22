package com.work;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author hike97
 * @Description
 * @create 2019-12-31 11:06
 * @Modified By:
 **/
public class YYYYDemo {
	/**
	 * 发现YYYY是表示：当天所在的周属于的年份，一周从周日开始，
	 * 周六结束，只要本周跨年，那么这周就算入下一年。
	 *
	 * @param args
	 */
	public static void main (String[] args) throws ParseException {
		//可以用20191231作为demo
//		String mdd = LocalDateTime.now ().format (DateTimeFormatter.ofPattern ("yyyyMMdd"));
//		System.out.println (mdd);
//		LocalDateTime parse = LocalDateTime.parse ("2019-12-31T10:38:10");
//		System.out.println (parse);

		double v = NumberFormat.getPercentInstance ().parse ("-3.09%").floatValue ();
		System.out.println (v);


	}
}
