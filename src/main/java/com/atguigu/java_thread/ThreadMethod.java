package com.atguigu.java_thread;

/**
 * @author hike97
 * @create 2018-09-18 20:23
 * @desc thread常用方法
 * 1.start():启动线程并执行对应的run()方法
 * 2.run（）:子线程要执行的代码放入run()方法中
 * 3.currentThread()：静态的 调取当前的线程
 * 4.getName（）获取次线程的名字
 * 5.setName()设置此线程的名字
 * 6.yield():调用此方法的线程 释放当前CPU的执行权
 * 7.join():在A线程中调用B线程的join方法，表示当执行到此方法 A线程停止执行
 * 直至B线程执行完毕，A线程再接着join()之后的代码执行
 * 8.isAlive()判断当前线程是否还存活
 * 9.sleep(long l):显式的让当前线程睡眠l毫秒
 * 10.线程通信：wait() notify() notifyAll()
 *
 * 设置线程的优先级
 * getpriority()
 * setpriority()
 **/
class SubThread_ extends Thread{
    /*
    只能try catch 的原因 被重写的方法不能抛比父类更大范围的异常
    run方法 没有异常 所以重写的run方法不能直接抛出异常
    run() throws Exception--不可执行
     */
    @Override
    public void run() {
        for (int i = 0; i <=100 ; i++) {
//            try {
//                Thread.currentThread().sleep( 1000 );
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println( Thread.currentThread().getName() + ":" +Thread.currentThread().getPriority()+":"+ i );
        }
    }
}
public class ThreadMethod  {
    public static void main(String[] args) throws InterruptedException {
        SubThread_ subThread_ = new SubThread_();
        subThread_.setName( "子线程1" );
        subThread_.setPriority( Thread.MAX_PRIORITY );
        subThread_.start();
        Thread.currentThread().setName( "主线程----------" );
        for (int i = 0; i <=100 ; i++) {
            System.out.println( Thread.currentThread().getName() + ":" +Thread.currentThread().getPriority()+":"+ i );
//            if (i%10==0){
//                Thread.currentThread().yield();
//            }
//              if (i==20){
//                  subThread_.join();
//              }
        }
        boolean alive = subThread_.isAlive();
        System.out.println( alive );
    }
}
