package mianshi.newcode.concurrent.c_002_visable;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName T01_Volatile
 * @Description volatile 只能保证线程可见性不能保证一致性
 * @Author hike97
 * @Date 2023/3/23 13:02
 * @Version 1.0
 **/
public class T01_Volatile {
    //在下面的代码中，running是存在于堆内存的t对象中
    volatile boolean running  =  true;

    void m() {
        System.out.println("m start");
        while (running){
            //System.out.println("hello");
        }
        System.out.println("m end");
    }

    public static void main(String[] args) throws InterruptedException {
        T01_Volatile t = new T01_Volatile();
        //当线程t1开始运行的时候，会把running值从内存中读到t1线程的工作区，在运行过程中直接使用这个copy，并不会每次都去读取堆内存，这样，
        new Thread(t::m).start();
        TimeUnit.SECONDS.sleep(1);
        //当主线程修改running的值之后，t1线程感知不到，所以不会停止运行
        // 使用volatile，将会强制所有线程都去堆内存中读取running的值
        t.running = false;
    }
}
