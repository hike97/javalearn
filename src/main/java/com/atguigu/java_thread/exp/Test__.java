package com.atguigu.java_thread.exp;

import java.util.Random;

/**
 * @author hike97
 * @create 2018-09-20 16:25
 * @desc 取钱
 **/
class Bank{
    //银行账号
    int account;

    public synchronized void saveMoney(double m){
        //存钱
        if (account>=100000){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("当前余额为：" + account + "美元，"+Thread.currentThread().getName() + ":" + "存储"+m+"元" );
            account+=m;
            notifyAll();
        }
    }

    public synchronized void consumeMoney(double m){
            if (account<=m){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println( "当前余额为：" + account + "美元，"+Thread.currentThread().getName() + ":" + "消费"+m+"元" );
                account-=m;
                notifyAll();
            }
    }
}
class Saver implements  Runnable{
    Bank bank;

    public Saver(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.currentThread().sleep( 100 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bank.saveMoney( Math.random()*1000+500 );
        }
    }
}
class Consumer_ implements  Runnable{
    Bank bank;

    public Consumer_(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.currentThread().sleep( 100 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bank.consumeMoney( Math.random()*5000+3000 );
        }
    }
}
public class Test__ {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Saver saver = new Saver( bank );
        Consumer_ consumer_ = new Consumer_( bank );
        Thread dad = new Thread( saver, "爹" );
        Thread son = new Thread( consumer_, "儿子" );
        dad.start();
        son.start();
    }
}
