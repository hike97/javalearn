package com.java8_19learn.time;

import com.atguigu.java_collection.genericity.Array;
import com.java8_19learn.entity.Apple;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.util.*;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-06-03 19:56
 * @Modified By:
 **/
public class TimeDemo {
	//LocalDate对象
	@Test
	public void test_ () {
		LocalDate date = LocalDate.of(2014, 3, 18);
		int year = date.getYear();
		Month month = date.getMonth();
		int day = date.getDayOfMonth();
		DayOfWeek dow = date.getDayOfWeek();
		int len = date.lengthOfMonth();
		boolean leap = date.isLeapYear();
		LocalDate today = LocalDate.now();
	}
	//set去重实验
	public static void main (String[] args) {
//		List<Apple> apples = Arrays.asList (new Apple ("red", 12), new Apple ("red", 12));
//		HashSet<Apple> set = new HashSet<> ();
//		boolean b = set.addAll (apples);
//		System.out.println (set.size ());
//		System.out.println (String.format ("%02d",Integer.valueOf ("01")));
		System.out.println (Integer.valueOf ("001") == 1);
	}

	/**
	 * local读取某个字段的值
	 *  TemporalField 参数给 get 方法拿到同样的信息
	 *  ChronoField 实现了  TemporalField接口
	 */
	@Test
	public void test_01 () {
		LocalDate now = LocalDate.now ();
		int year = now.get (ChronoField.YEAR);
		int month = now.get (ChronoField.MONTH_OF_YEAR);
		int day = now.get (ChronoField.DAY_OF_MONTH);
		System.out.println (year + "年" + month + "月" + day + "日");
		//2019年6月6日
		/**创建 LocalTime 并读取其值*/
		LocalTime time = LocalTime.of(13, 45, 20);
		int hour = time.getHour();
		int minute = time.getMinute();
		int second = time.getSecond();

	}
	/**localdateTime*/
	@Test
	public void test_02 () {
		// 2014-03-18T13:45:20
		//
		LocalDate date = LocalDate.parse("2014-03-18");
		LocalTime time = LocalTime.parse("13:45:20");
		LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
		LocalDateTime dt2 = LocalDateTime.of(date, time);
		LocalDateTime dt3 = date.atTime(13, 45, 20);
		LocalDateTime dt4 = date.atTime(time);
		LocalDateTime dt5 = time.atDate(date);
		//localdateTime 转localdate 和 localTime
		LocalDate date1 = dt1.toLocalDate();
		LocalTime time1 = dt1.toLocalTime();
	}
	/**机器的日期和时间格式
	 */
	@Test
	public void test_03 () {
		Instant.ofEpochSecond(3);
		Instant.ofEpochSecond(3, 0);
		Instant.ofEpochSecond(2, 1_000_000_000);
		Instant.ofEpochSecond(4, -1_000_000_000);
	}
	/**
	 * 定义 Duration 或 Period
	 */
	@Test
	public void test_04 () {
		//Duration 的between方法 来确定两个时间段
		//既可以是Instant 也可以是 LocalDateTime
		LocalTime time1 = LocalTime.now ();
		LocalTime time2 = LocalTime.of (15, 42);
		Duration d1 = Duration.between (time1, time2);
		System.out.println (d1);
		//年月日用period
		Period tenDays = Period.between(LocalDate.of(2014, 3, 8),
				LocalDate.of(2014, 3, 18));
		System.out.println (tenDays);
		//
		Duration threeMinutes = Duration.ofMinutes(3);
		Duration threeMinutes_ = Duration.of(3, ChronoUnit.MINUTES);
		Period tenDays_ = Period.ofDays(10);
		Period threeWeeks = Period.ofWeeks(3);
		Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);
		/*
		 * 方 法 名  是否是静态方法    方法描述
			between  是  创建两个时间点之间的 interval
			from     是  由一个临时时间点创建 interval
			of       是  由它的组成部分创建 interval的实例
			parse    是  由字符串创建 interval 的实例
			addTo    否  创建该 interval 的副本，并将其叠加到某个指定的 temporal 对象
			get      否  读取该 interval 的状态
			isNegative  否  检查该 interval 是否为负值，不包含零
			isZero  否  检查该 interval 的时长是否为零
			minus  否  通过减去一定的时间创建该 interval 的副本
			multipliedBy  否  将 interval 的值乘以某个标量创建该 interval 的副本
			negated  否  以忽略某个时长的方式创建该 interval 的副本
			plus  否  以增加某个指定的时长的方式创建该 interval 的副本
		    subtractFrom  否  从指定的 temporal 对象中减去该 interval
		 */
		//以比较直观的方式操纵 LocalDate 的属性
		LocalDate date1 = LocalDate.of(2014, 3, 18);
		LocalDate date2 = date1.withYear(2011);
		LocalDate date3 = date2.withDayOfMonth(25);
		LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);

		//以相对方式修改 LocalDate 对象的属性
		 date1 = LocalDate.of(2014, 3, 18);
		 date2 = date1.plusWeeks(1);
		 date3 = date2.minusYears(3);
		 date4 = date3.plus(6, ChronoUnit.MONTHS);

		/*方 法 名  是否是静态方法 描 述
		from  是  依据传入的 Temporal 对象创建对象实例
		now  是  依据系统时钟创建 Temporal 对象
		of  是  由 Temporal 对象的某个部分创建该对象的实例
		parse  是  由字符串创建 Temporal 对象的实例
		atOffset  否  将 Temporal 对象和某个时区偏移相结合
		atZone  否  将 Temporal 对象和某个时区相结合
		format  否  使用某个指定的格式器将 Temporal 对象转换为字符串（ Instant 类不提供该方法）
		get  否  读取 Temporal 对象的某一部分的值
		minus  否
		创建 Temporal 对象的一个副本，通过将当前 Temporal 对象的值减去一定的时长
				创建该副本
		plus  否
		创建 Temporal 对象的一个副本，通过将当前 Temporal 对象的值加上一定的时长
				创建该副本
		with  否  以该 Temporal 对象为模板，对某些状态进行修改创建该对象的副本*/
	}

	@Test
	public void test_05 () {
//		LocalDate date = LocalDate.of(2014, 3, 18);
//		date = date.with(ChronoField.MONTH_OF_YEAR, 9);
//		System.out.println (date);
//		date = date.plusYears(2).minusDays(10);
		LocalDate date = LocalDate.parse("2017-01-03");
		//不修改date原来的值 只产生一个副本
		System.out.println ("date.withYear方法："+date.withYear (2011));
		System.out.println (date);
	}

	/**
	 * TemporalAdjuster 处理复杂操作
	 */

	@Test
	public void test_06 () {
		LocalDate date1 = LocalDate.of (2014, 3, 18);
		LocalDate date2 = date1.with (nextOrSame (DayOfWeek.SUNDAY));
		System.out.println (date2);
		LocalDate date3 = date2.with (lastDayOfMonth ());
		System.out.println (date3);
		/*
		  表12-3  TemporalAdjuster 类中的工厂方法
			方 法 名  			描 述
			dayOfWeekInMonth  创建一个新的日期，它的值为同一个月中每一周的第几天
			firstDayOfMonth  创建一个新的日期，它的值为当月的第一天
			firstDayOfNextMonth  创建一个新的日期，它的值为下月的第一天
			firstDayOfNextYear  创建一个新的日期，它的值为明年的第一天
			firstDayOfYear  创建一个新的日期，它的值为当年的第一天
			firstInMonth  创建一个新的日期，它的值为同一个月中，第一个符合星期几要求的值
			lastDayOfMonth  创建一个新的日期，它的值为当月的最后一天
			lastDayOfNextMonth  创建一个新的日期，它的值为下月的最后一天
			lastDayOfNextYear  创建一个新的日期，它的值为明年的最后一天
			lastDayOfYear  创建一个新的日期，它的值为今年的最后一天
			lastInMonth  创建一个新的日期，它的值为同一个月中，最后一个符合星期几要求的值
			next/previous
			创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星
			期几要求的日期
			nextOrSame/previousOrSame
			创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星
			期几要求的日期，如果该日期已经符合要求，直接返回该对象
		 */

	}
	//dateTimefommater类
	@Test
	public void test_07 () {
		Timestamp timestamp = new Timestamp (System.currentTimeMillis ());
		System.out.println ("当前时间："+timestamp);
		LocalDateTime time = timestamp.toLocalDateTime ();
		String s1 = time.format (DateTimeFormatter.BASIC_ISO_DATE);
		String s2 = time.format (DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		String s3 = time.format (DateTimeFormatter.ofPattern ("yyyy-MM-dd hh:mm:ss"));
		System.out.println (s1);
		System.out.println (s2);
		System.out.println (s3);
		LocalDate date = LocalDate.of (2018, 8, 8);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("dd/MM/yyyy");
		String formatreedDate = date.format (formatter);
		LocalDate date2 = LocalDate.parse (formatreedDate, formatter);
		System.out.println (formatreedDate);
		System.out.println (date2);
	}
	//zoneId 的使用
	@Test
	public void test_8 () {
		ZoneId romeZone = ZoneId.of ("Europe/Rome");
		//通过Java 8的新方法 toZoneId 将一个老的时区对象转换为 ZoneId
		ZoneId zoneId = TimeZone.getDefault ().toZoneId ();
		//ZoneId对象与LocalDate LocalDateTime Instant结合构造成ZonedDateTime
		LocalDate date = LocalDate.of (2014, Month.MARCH, 18);
		ZonedDateTime zdt1 = date.atStartOfDay (romeZone);
		LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
		ZonedDateTime zdt2 = dateTime.atZone(romeZone);
		Instant instant = Instant.now();
		ZonedDateTime zdt3 = instant.atZone(romeZone);
		System.out.println (zdt1 + ":" + zdt2 + ":" + zdt3);
		//localDateTime转为Instant
		LocalDateTime dateTime_ = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
		Instant instantFromDateTime = dateTime.toInstant(ZoneOffset.MIN);
		//Instant 转换为LocalDateTime
		Instant instant_ = Instant.now ();
		LocalDateTime.ofInstant (instant_,romeZone);
	}

}
