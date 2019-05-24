package com.java8_19learn.designPattern.templates_pattern;

import java.util.function.Consumer;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-05-24 16:13
 * @Modified By:
 **/
public class OnlineBankingLambda extends OnlineBanking {

	public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
		Customer c = new Customer ();
		makeCustomerHappy.accept(c);
	}

	@Override
	void makeCustomerHappy (Customer c) {

	}
}
