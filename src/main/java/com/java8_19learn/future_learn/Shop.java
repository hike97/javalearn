package com.java8_19learn.future_learn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author hike97 許せ　サスケ　これで最後だ
 * @create 2019-06-02 21:30
 * @desc
 **/
public @Data
@AllArgsConstructor
@NoArgsConstructor
class Shop {

	private String name;

	public double getPrice (String product) {
		//待实现
		return calculatePrice (product);
	}

	private double calculatePrice (String product) {
		randomDelay ();
		return Math.random () * product.charAt (0) + product.charAt (1);
	}

	//模拟1s延迟方法
	public static void delay () {
		try {
			Thread.sleep (1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException (e);
		}
	}

	//异步获取价格
	public Future<Double> getPriceAsync (String product) {
		CompletableFuture<Double> futurePrice = new CompletableFuture<> ();
		new Thread (() -> {
			try {
				double price = calculatePrice (product);
				futurePrice.complete (price);
			} catch (Exception e) {
				futurePrice.completeExceptionally (e);
			}
		}).start ();
		return futurePrice;
	}

	//异步获取价格加强版
	public Future<Double> getPriceAsync_ (String product) {
		return CompletableFuture.supplyAsync (() -> calculatePrice (product));
	}

	public String getPriceNew(String product) {
		//随机产生价格
		double price = calculatePrice(product);
		//随机产生dicount码
		Discount.Code code = Discount.Code.values()[
				new Random ().nextInt(Discount.Code.values().length)];
		//name 商品名称
		return String.format("%s:%.2f:%s", name, price, code);
	}

	//一个模拟生成0.5秒至2.5秒随机延迟的方法
	private static final Random random = new Random();
	public static void randomDelay() {
		int delay = 500 + random.nextInt(2000);
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
