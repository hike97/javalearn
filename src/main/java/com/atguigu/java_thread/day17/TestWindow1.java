package com.atguigu.java_thread.day17;

/**
 * @author hike97
 * @create 2018-09-19 14:57
 * @desc 多线程实现多窗口售票
 * 模拟火车站售票窗口，开启三个窗口售票，总票数为100张
 **/
class Window1 implements Runnable{
    //使用static 类变量
     int ticket = 100;

    @Override
    public void run() {
        while (true){
            if (ticket>0){
                try {
                    Thread.currentThread().sleep( 50 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println( Thread.currentThread().getName() + "售票，票号为" + ticket-- );
            }
        }
    }
}

/**
 * 使用多线程的优点
 * 1.提高应用程序的响应，对图形化界面更有意义，可增强用户体验
 * 2.提高计算机系统cpu的利用率
 * 3.改善程序结构，将复杂进程简洁化
 */
public class TestWindow1 {
    public static void main(String[] args) {
        Window w = new Window();
        //多线程
        Thread t1 = new Thread( w ,"窗口1");
        Thread t2 = new Thread( w ,"窗口2");
        Thread t3 = new Thread( w ,"窗口3");
        Thread t4 = new Thread( w ,"窗口4");
        Thread t5 = new Thread( w ,"窗口5");
        Thread t6 = new Thread( w ,"窗口6");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

    }
}
