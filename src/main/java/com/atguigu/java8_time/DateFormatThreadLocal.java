package com.atguigu.java8_time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hike97
 * @create 2018-09-07 20:51
 * @desc threadlocal
 **/
public class DateFormatThreadLocal {
    //锁上dateformat
    private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat( "yyyyMMdd" );
        }
    };

    public static Date convert(String sooure) throws ParseException {
        return  df.get().parse( sooure );
    }
}
