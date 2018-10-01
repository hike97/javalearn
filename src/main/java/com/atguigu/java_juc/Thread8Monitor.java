package com.atguigu.java_juc;

/**
 * @author hike97
 * @create 2018-09-29 16:04
 * @desc 线程8锁：工作中常见的情况
 **/
public class Thread8Monitor {
    /**
     * 题目：判断打印的
     * 1.两个普通同步方法，两个线程，标准打印，打印？// one two
     * 2.新增Thread.sleep()给getOne(),打印？//one two
     * 3.新增getThree(),结果打印// three one two
     * 4.两个普通同步方法，两个Number对象，打印？//two one
     * 5.修改getOne为静态同步方法，打印？//two one
     * 6.修改两个方法均为静态同步方法，一个Number对象？//one two
     * 7.一个静态同步方法，一个非静态同步方法，两个number对象？//two one
     * 8.两个静态同步方法，两个Number对象？//one two
     *
     * 关键点：
     * 1.非静态方法的锁为this,静态方法的锁 为对应的Class实例
     * 2.在某一个时刻内，只能有一个线程持有锁，无论几个方法
     */
    public static void main(String[] args) {
        Number number = new Number();
        Number number2 = new Number();
        new Thread( ()->number.getOne()
        ).start();
        new Thread( ()->
                number2.getTwo()
                //number.getTwo()
        ).start();
//        new Thread( ()->number.getThree()
//        ).start();
    }
}
class Number{

    public static synchronized   void getOne(){//Number.class
        try {
            Thread.sleep( 3000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( "one" );
    }

    public  synchronized  void getTwo(){//this
        System.out.println( "two" );
    }

    public  void getThree(){
        System.out.println( "three" );
    }


}