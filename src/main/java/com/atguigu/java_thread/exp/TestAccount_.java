package com.atguigu.java_thread.exp;

/**
 * @author hike97
 * @create 2018-09-20 14:15
 * @desc runnable接口实现
 **/
public class TestAccount_ {
     /*
        desc 银行有一个账户
        有两个储户分别向同一个账户存3000
        每次存1000，存3次。每次存完打印账户余额
        1.是否涉及到多线程？有两个储户（两种方式创建多线程）
        2.是否有共享数据？同一个账户
        3.考虑线程的同步。（两种方法处理线程的安全）
     */
     public static void main(String[] args) {
         Account_ a = new Account_();
         customer_ customer_ = new customer_( a );
         Thread t1 = new Thread( customer_, "校长" );
         Thread t2 = new Thread( customer_, "苍老师" );
         t1.start();
         t2.start();
     }
}
//账户
class Account_{
    double  remain;
    //存钱操作
    public synchronized void plus(double money){
        notify();
        remain +=money;
        try {
            Thread.currentThread().sleep( 10 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( Thread.currentThread().getName() + ":" + "当前余额为：" + remain );
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
//储户
class customer_ implements Runnable{
    //账户
    private Account_ account_;

    public customer_(Account_ account_) {
        this.account_ = account_;
    }

    @Override
    public void run() {
        for (int i = 0; i <3 ; i++) {
            account_.plus( 1000 );
        }
    }
}
