package com.atguigu.java8_time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author hike97
 * @create 2018-09-07 20:21
 * @desc
 * 多线程同时操作 日期格式化
 **/
public class TestSimpleDateFormat {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMdd" );
//        Callable<Date> task = new Callable<Date>(){
//            @Override
//            public Date call() throws Exception {
//                return DateFormatThreadLocal.convert( "20161218" );
//            }
//        };
//        //线程池
//        ExecutorService pool = Executors.newFixedThreadPool( 10 );
//
//        List<Future<Date>> results = new ArrayList<>();
//
//        for (int i = 0; i <10 ; i++) {
//            results.add( pool.submit( task ));
//        }
//        for (Future<Date> future : results) {
//            System.out.println( future.get() );
//        }
//        pool.shutdown();

        /**
         *  LocalDate java1.8
         */
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "yyyyMMdd" );

        Callable<LocalDate> task = new Callable<LocalDate>(){
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse( "20161218",dtf );
            }
        };
        //线程池
        ExecutorService pool = Executors.newFixedThreadPool( 10 );

        List<Future<LocalDate>> results = new ArrayList<>();

        for (int i = 0; i <10 ; i++) {
            results.add( pool.submit( task ));
        }
        for (Future<LocalDate> future : results) {
            System.out.println( future.get() );
        }
        pool.shutdown();
    }
}
