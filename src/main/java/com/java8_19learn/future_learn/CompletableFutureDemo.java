package com.java8_19learn.future_learn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static java.util.stream.Collectors.toList;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-05-28 15:32
 * @Modified By:
 **/
public class CompletableFutureDemo {
	List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
			new Shop("LetsSaveBig"),
			new Shop("MyFavoriteShop"),
			new Shop("BuyItAll"));
	//提供shops 列出每个商品的价格
	public List<String> findPrices(String product) {
		return shops.stream()
				.map(shop -> String.format("%s price is %.2f",
						shop.getName(), shop.getPrice(product)))
				.collect(toList());
	}
	public List<String> findPrices_ByParallStream(String product) {
		return shops.parallelStream ()
				.map(shop -> String.format("%s price is %.2f",
						shop.getName(), shop.getPrice(product)))
				.collect(toList());
	}
	//算出每个商店同一产品的价格
	@Test
	public void test_ () {
		long start = System.nanoTime();
//		System.out.println(findPrices("myPhone27S"));
		System.out.println(findPrices_ByParallStream ("myPhone27S"));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs"); //Done in 4006 msecs ||Done in 1009 msecs
	}
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
	//异步获取价格加强版
	public Future<Double> getPriceAsync_(String product) {
		return CompletableFuture.supplyAsync(() -> calculatePrice(product));
	}
}
