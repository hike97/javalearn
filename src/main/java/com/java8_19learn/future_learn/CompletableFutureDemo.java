package com.java8_19learn.future_learn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-05-28 15:32
 * @Modified By:
 **/
public class CompletableFutureDemo {



	public static void main (String[] args) {
		Shop shop = new Shop ("BestShop");
		long start = System.nanoTime ();
		Future<Double> futurePrice = shop.getPriceAsync ("my favorite product");
		long invocationTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Invocation returned after " + invocationTime
				+ " msecs");
		// 执行更多任务，比如查询其他商店
		doSomethingElse();
		// 在计算商品价格的同时
		try {
			double price = futurePrice.get();
			System.out.printf("Price is %.2f%n", price);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Price returned after " + retrievalTime + " msecs");
	}

	public static void doSomethingElse(){
		System.out.println ("doSomeThing hard");
	}
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Shop {

	private String name;

	public double getPrice(String product) {
		//待实现
		return calculatePrice(product);
	}
	private double calculatePrice(String product) {
		delay();
		return Math.random () * product.charAt(0) + product.charAt(1);
	}

	//模拟1s延迟方法
	public static void delay() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	//异步获取价格
	public Future<Double> getPriceAsync(String product) {
		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
		new Thread( () -> {
			try {
				double price = calculatePrice(product);
				futurePrice.complete(price);
			} catch (Exception e) {
				futurePrice.completeExceptionally (e);
			}
		}).start();
		return futurePrice;
	}
}
