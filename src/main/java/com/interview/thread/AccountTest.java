package com.interview.thread;

import lombok.Data;
import lombok.SneakyThrows;

/**
 * @author hike97 2month
 * @create 2020-09-13 22:43
 * @desc
 **/
public class AccountTest {
}
@Data
class Account{
	private double balance;
	//存钱
	public void deposit(double amt) throws InterruptedException {
		if (amt >0){
			balance+=amt;
			Thread.sleep (1000);
			System.out.printf ("存钱成功。余额为：%d", amt);
		}
	}
}
@Data
class Customer extends Thread{
	private Account acct;
    //https://www.cnblogs.com/xianz666/p/13414226.html 没看太懂先用着
	@SneakyThrows
	@Override
	public void run () {
		for (int i = 0; i < 3; i++) {
			acct.deposit (1000);
		}
	}
}
