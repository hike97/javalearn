package com.atguigu.java_thread.day17;

/**
 * @author hike97
 * @create 2018-09-20 15:22
 * @desc 线程通信
 **/
public class Thread_message {
    /**
     * wait() 挂起当前线程 放弃CPU,释放当前锁
     * notify():唤醒优先级最高的线程（排队等待中）
     * notifyAll（）唤醒所有正在排队等待同步资源的线程结束等待
     * 只能在sychronized方法或sychronized代码块中才能使用
     * 否则会报illegalMonitorStateException
     * */
    /*
    需求：使用两个线程交替打印 1-100.
     */
    public static void main(String[] args) {
        PrintNum_ p = new PrintNum_();
        Thread t1 = new Thread( p, "甲" );
        Thread t2 = new Thread( p, "乙" );
        t1.start();
        t2.start();
    }
}
class PrintNum_ implements Runnable{
    int num =  1;
    @Override
    public void run() {
        while (true){
            synchronized (this) {
                notify();
                if (num<=100){
                    try {
                        Thread.currentThread().sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println( Thread.currentThread().getName() + ":" + num );
                    num++;
                }else {
                    break;
                }
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
