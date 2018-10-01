package com.atguigu.java_thread;

/**
 * @author hike97
 * @create 2018-09-29 10:41
 * @desc 生产者和消费者案例
 * 生产者线程：添加
 * 消费者线程：删除
 **/

/**
 * 加入等待唤醒机制 wait() notifyAll()
 * --会出现的问题：
 *      假设卖货剩循环次数：1
 *      买货剩循环次数：2
 *      sale()操作：当product = 0 时，this.wait 等待并释放锁 循环次数为 0
 *      get()方法： ++product  notifyAll（）->唤醒 sale() sale()没有循环次数 消费者线程结束
 *      生产者线程获取线程资源 product>=1 产品已满 this.wait()
 */
/*无人唤醒，结果如下：
 *       生产者A:1
         消费者B:0
         生产者A:1
         产品已满！
 */
/**
 * 解决方案：去掉else
 * --问题2：
 * 多个线程同时启动会产生虚假唤醒状态
 * jdk源码：
 * wait()-[
 *     As in the one argument version, interrupts and spurious wakeups are possible,
 *     and this method should always be used in a loop:
     synchronized (obj) {
     while (<condition does not hold>)
     obj.wait();
     ... // Perform action appropriate to condition
     }

 * ]
 * 为了避免虚假唤醒问题，应该总是使用在循环中
 */

class TestProductorAndConsuer {
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
    //进货
    public synchronized void get(){
        while (product>=1){//店员只有一个
            System.out.println( "产品已满！" );
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println( Thread.currentThread().getName() + ":" + ++product );
        this.notifyAll();

    }
    //卖货
    public synchronized void sale(){
        while (product<=0){
            System.out.println( "进货!" );
            try {
                this.wait();//等待 并释放锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println( Thread.currentThread().getName() + ":" + --product );
        this.notifyAll();//唤醒生产者

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
