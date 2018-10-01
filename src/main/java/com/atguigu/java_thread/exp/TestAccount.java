package com.atguigu.java_thread.exp;

/**
 * @author hike97
 * @create 2018-09-20 11:20
 * @desc 银行账号测试多线程
 **/
public class TestAccount {
    /*
        desc 银行有一个账户
        有两个储户分别向同一个账户存3000
        每次存1000，存3次。每次存完打印账户余额
        1.是否涉及到多线程？有两个储户（两种方式创建多线程）
        2.是否有共享数据？同一个账户
        3.考虑线程的同步。（两种方法处理线程的安全）
     */
    public static void main(String[] args) {
        Account account = new Account();
        Customer c1 = new Customer( account );
        Customer c2 = new Customer( account );

        c1.setName( "小明" );
        c2.setName( "小李" );
        c1.start();
        c2.start();
    }
}
class Account{
    double balance;//余额
    public Account(){

    }
    //存钱
    public synchronized void deposit(double amt) {
        balance += amt;
        try {
            Thread.currentThread().sleep( 10 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( Thread.currentThread().getName() + ":" + balance );
    }
}
class Customer extends Thread{
    Account account;
    public Customer(Account account){
        this.account = account;
    }
    @Override
    public void run() {
        for (int i = 0; i <3 ; i++) {
            account.deposit( 1000 );
        }
    }
}