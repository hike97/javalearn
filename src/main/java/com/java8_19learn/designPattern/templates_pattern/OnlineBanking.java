package com.java8_19learn.designPattern.templates_pattern;


import lombok.Data;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-05-24 16:05
 * @Modified By:
 **/
abstract class OnlineBanking {

	//获取Customer
	public void processCustomer(int id){
		Customer c = new Customer ();
		makeCustomerHappy(c);
	}

	abstract void makeCustomerHappy(Customer c);

	@Data
	public static class Customer {
		private String name;
	}


	public static void main (String[] args) {
		new OnlineBankingLambda().processCustomer(1337, (Customer c) ->
				System.out.println("Hello " + c.getName()));
	}
}
