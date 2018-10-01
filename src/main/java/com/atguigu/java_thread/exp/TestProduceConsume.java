package com.atguigu.java_thread.exp;

/**
 * @author hike97
 * @create 2018-09-20 15:53
 * @desc 生产者和消费者问题
 **/
public class TestProduceConsume {
    /**
     * 生产者、消费者问题
     * 生产者--->产品(交给店员)<---消费者取走
     * 店员一次只能持有固定数量的产品（比如：20），
        * 如果生产者试图生产更多的产品，店员会叫生产者停一下。
     *如果店中有空位房产品了再通知生产者继续生产；
        *如果店中没有产品了，店员会告诉消费者等一下，
     *如果店中有产品了再通知消费者来取走产品。
     */
    /*
       分析：
       1.是否涉及到多线程？生产者 消费者
       2.是否涉及到共享数据？有！即为产品数量
       3.此共享数据是谁？即为产品的数量
       4.是否涉及线程通信？存在着生产者与消费者通信
     */
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer producer = new Producer( clerk );
        Consumer consumer = new Consumer( clerk );
        Thread t1 = new Thread(producer,"生产者");
        Thread t3 = new Thread(producer,"老王");
        Thread t2 = new Thread(consumer,"消费者");
        t1.start();
        t2.start();
        t3.start();
    }
}
class Clerk{
    //店员
    int product;
    public synchronized void addProduct(){
        //生产产品
        if (product>=20){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            product ++;
            System.out.println( Thread.currentThread().getName() + ":" + "生产了第" + product + "个产品" );
            notifyAll();
        }
    }

    public synchronized void consumeProduct() {
        //消费产品
        if (product <= 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println( Thread.currentThread().getName() + ":" + "消费了第" + product + "个产品" );
            product --;
            //消费一个唤醒
            notifyAll();
        }
    }
}
//生产者
class Producer implements Runnable {
    Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println( "生产者开始生产产品" );
        while (true){
            try {
                Thread.currentThread().sleep( 100 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.addProduct();
        }
    }
}
//消费者
class Consumer implements  Runnable{
    Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println( "消费产品" );
        while (true){
            try {
                Thread.currentThread().sleep( 100 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumeProduct();
        }
    }
}
