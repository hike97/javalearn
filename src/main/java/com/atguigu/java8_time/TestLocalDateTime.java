package com.atguigu.java8_time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * @author hike97
 * @create 2018-09-09 10:21
 * @desc ceshi localDate
 **/
public class TestLocalDateTime {
    //时区的处理 ZoneDate ZOnedTime ZOnedDAteTime
    @Test
    public void test_8() {
        LocalDateTime now = LocalDateTime.now( ZoneId.of( "Africa/Niamey" ) );
        System.out.println( now );
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime ztd = ldt.atZone( ZoneId.of( "Asia/Shanghai" ) );
        System.out.println( ztd );
    }
    @Test
    public void test_7() {
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach( System.out::println );
    }

    //dateTimeFormatter 格式化时间、日期
    @Test
    public void test_6() {
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();
        String format = ldt.format( dtf );
        System.out.println( format );
        //自定义格式
        DateTimeFormatter mydtf = DateTimeFormatter.ofPattern( "yyyy年MM月dd日 HH:mm:ss" );
        String strDate = ldt.format( mydtf );
        System.out.println( strDate );

        LocalDateTime newDate = ldt.parse( strDate, mydtf );
        System.out.println( newDate );
    }
    //时间校正器
    @Test
    public void test_5() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println( now );
        LocalDateTime localDateTime = now.withDayOfMonth( 10 );
        System.out.println("改为10天：" +localDateTime );
        LocalDateTime dayofWeekSUNDAY = localDateTime.with( TemporalAdjusters.next( DayOfWeek.SUNDAY ) );
        System.out.println( "下周日："+dayofWeekSUNDAY );
        //自定义：下一个工作日
        LocalDateTime ldt5 = now.with( l -> {
            LocalDateTime ldt4 = (LocalDateTime) l;
            DayOfWeek dow = ldt4.getDayOfWeek();
            if (dow.equals( DayOfWeek.FRIDAY )) {
                return ldt4.plusDays( 3 );
            } else if (dow.equals( DayOfWeek.SATURDAY )) {
                return ldt4.plusDays( 2 );
            } else {
                return ldt4.plusDays( 1 );
            }
        });
        System.out.println( "下一个工作日："+ldt5 );
    }
    //3.
    // Duration:计算两个“时间”之间的间隔
    //Period:计算两个日期之间的间隔
    @Test
    public void test_4() {
        LocalDate date = LocalDate.of( 1993, 7, 7 );
        LocalDate now = LocalDate.now();
        Period between = Period.between( date, now );
        System.out.println( between );

        System.out.println( between.getYears() + "年" + between.getMonths() + "月" + between.getDays()+"日");
    }
    @Test
    public void test_3() {
        Instant instant = Instant.now();
        try {
            Thread.sleep( 1000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant instant1 = Instant.now();
        Duration duration = Duration.between( instant, instant1 );
        System.out.println( duration+"毫秒："+duration.toMillis() );

        System.out.println( "----------------------------------" );
        LocalTime localTime = LocalTime.now();
        try {
            Thread.sleep( 1000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime localTime1 = localTime.now();

        System.out.println( Duration.between( localTime, localTime1 ).toMillis() );
    }

    //2.Instant : 时间戳（以Unix 元年：1970年1月1日 00：00：00 到某个时间之间的毫秒值）
    @Test
    public void test_2() {
        Instant now = Instant.now();//默认获取 UTC 时区
        System.out.println( now );
        OffsetDateTime offsetDateTime = now.atOffset( ZoneOffset.ofHours( 8 ) );
        System.out.println( "东八区："+offsetDateTime );
        long l = now.toEpochMilli();
        System.out.println("毫秒："+ l );
        //毫秒偏移量
        Instant epochMilli = Instant.ofEpochMilli( 1000 );
        System.out.println( epochMilli );
    }
    //1.localDate LocalTime LocalDateTime
    @Test
    public void test_1() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println( now );
        //自定义时间
        LocalDateTime localDateTime = LocalDateTime.of( 2015, 10, 1, 12, 23, 33 );
        System.out.println( "自定义时间:"+localDateTime );
        localDateTime = localDateTime.plusYears( 2 );
        System.out.println( "加两年" + localDateTime );
        localDateTime = localDateTime.minusMonths( 2 );
        System.out.println( "减两个月："+localDateTime );

        System.out.println( localDateTime.getYear() );
        System.out.println( localDateTime.getMonthValue() );
        System.out.println( localDateTime.getDayOfMonth());
        System.out.println( localDateTime.getHour() );
        System.out.println( localDateTime.getMinute() );
        System.out.println( localDateTime.getSecond() );


    }
}
