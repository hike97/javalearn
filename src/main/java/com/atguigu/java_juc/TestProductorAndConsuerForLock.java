package com.atguigu.java_juc;

/**
 * @author hike97
 * @create 2018-09-29 10:41
 * @desc 生产者和消费者案例
 * 生产者线程：添加
 * 消费者线程：删除
 **/

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同步锁机制 解决虚假唤醒问题
 * condition 中 相当于 wait notify notifyall 的方法
 *                    await signal signalAll
 */
public class TestProductorAndConsuerForLock {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();

        Productor pro = new Productor( clerk );
        Consumer cus = new Consumer( clerk );
        new Thread( pro,"生产者A" ).start();
        new Thread( pro,"生产者B" ).start();
        new Thread( cus,"消费者A" ).start();
        new Thread( cus,"消费者B" ).start();
    }
}
/*
店员
 */
class Clerk {
    private int product = 0;
    Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    //进货
    public  void get(){
        try {
            while (product>=1){//店员只有一个
                System.out.println( "产品已满！" );
                try {
                    //this.wait();
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println( Thread.currentThread().getName() + ":" + ++product );
            //this.notifyAll();
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }
    //卖货
    public  void sale(){

        try {
            while (product<=0){
                System.out.println( "进货!" );
                try {
                    //this.wait();//等待 并释放锁
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println( Thread.currentThread().getName() + ":" + --product );
            //this.notifyAll();//唤醒生产者
            condition.signalAll();
        } finally {
            lock.unlock();
        }

    }
}
/*
 生产者
 */
class Productor implements  Runnable{

    private Clerk clerk;

    public Productor(Clerk clerk){
        this.clerk = clerk;
    }
    @Override
    public void run() {
        for (int i = 0; i <20 ; i++) {
            try {
                Thread.sleep( 200 );//生产0.2s延迟
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.get();
        }
    }
}
/*
 消费者
 */
class Consumer implements Runnable{
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i = 0; i <20 ; i++) {
            clerk.sale();
        }
    }
}
