package com.atguigu.java_thread.day17;

/**
 * @author hike97
 * @create 2018-09-19 14:38
 * @desc
 **/
class PrintNum extends Thread{
    public PrintNum(String name) {
        super( name );
    }

    @Override
    public void run() {
        for (int i = 0; i <100 ; i++) {
            if (i%2 == 0){
//                try {
//                    sleep( 1000 );
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println( Thread.currentThread().getName() + ":" + i );
            }
        }
    }
}
public class Test_Thread {
    public static void main(String[] args) {
        PrintNum p1 = new PrintNum( "小损样1" );
        PrintNum p2 = new PrintNum( "小损样2" );
        p1.setPriority( Thread.MAX_PRIORITY );
        p1.setPriority( Thread.MIN_PRIORITY );
        p1.start();
        p2.start();
    }
}
