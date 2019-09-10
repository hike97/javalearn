package com.java8_19learn.future_learn;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-05-28 15:32
 * @Modified By:
 **/
public class CompletableFutureDemo {
	/*
	      执行比较耗时的操作时，尤其是那些依赖一个或多个远程服务的操作，使用异步任务可
		以改善程序的性能，加快程序的响应速度。
		  你应该尽可能地为客户提供异步API。使用 CompletableFuture 类提供的特性，你能够
		轻松地实现这一目标。
		  CompletableFuture 类还提供了异常管理的机制，让你有机会抛出/管理异步任务执行
		中发生的异常。
		  将同步API的调用封装到一个 CompletableFuture 中，你能够以异步的方式使用其结果。
		  如果异步任务之间相互独立，或者它们之间某一些的结果是另一些的输入，你可以将这
		些异步任务构造或者合并成一个。
		  你可以为 CompletableFuture 注册一个回调函数，在 Future 执行完毕或者它们计算的
		结果可用时，针对性地执行一些程序。
		  你可以决定在什么时候结束程序的运行，是等待由 CompletableFuture 对象构成的列表
		中所有的对象都执行完毕，还是只要其中任何一个首先完成就中止程序的运行。
	 */
	List<Shop> shops = Arrays.asList (
			new Shop ("BestPrice"), new Shop ("LetsSaveBig"),new Shop ("MyFavoriteShop"),new Shop ("BuyItAll")
			,new Shop ("BestPrice"), new Shop ("LetsSaveBig"),new Shop ("MyFavoriteShop"),new Shop ("BuyItAll")
	);
	//创建执行器 线程为商店的个数
	private final Executor executor = Executors.newFixedThreadPool (Math.min (shops.size (), 100), new ThreadFactory () {
		@Override
		public Thread newThread (Runnable r) {
			Thread t = new Thread (r);
			//使用守护线程--这种方式不会组织程序的关停；
			t.setDaemon (true);
			return t;
		}
	});
	///////////////////////////////////////////////////////////上边是参数/////////////////////////////////////////////////
	/*响应 CompletableFuture 的 completion 事件*/
	/**
	 * 需求：
	 * 实现的 findPrices 方法只有在取得所有商店的返回值时才显示商品的价格。
	 * 而你希望的效果是，只要有商店返回商品价格就在第一时间显示返回值，
	 * 不再等待那些还未返回的商店（有些甚至会发生超时）
	 */
	//最终实验
	@Test
	public void test_08 () {
		long start = System.nanoTime();
		CompletableFuture[] futures = findPricesStream("myPhone27S")
				.map(f -> f.thenAccept(
						s -> System.out.println(s + " (done in " +
								((System.nanoTime() - start) / 1_000_000) + " msecs)")))
				.toArray(size -> new CompletableFuture[size]);
		CompletableFuture.allOf(futures).join();
		System.out.println("All shops have now responded in "
				+ ((System.nanoTime() - start) / 1_000_000) + " msecs");
	}
	@Test
	public void test_test07 () {
		/*
		    thenAccept 方法已经定义了如何处理 CompletableFuture 返回的结果，一旦
			CompletableFuture 计算得到结果，它就返回一个 CompletableFuture<Void> 。所以， map
			操作返回的是一个 Stream<CompletableFuture<Void>> 。对这个 <CompletableFuture-
			<Void>> 对象，你能做的事非常有限，只能等待其运行结束，不过这也是你所期望的。你还希望
			能给最慢的商店一些机会，让它有机会打印输出返回的价格。为了实现这一目的，你可以把构成
			Stream 的所有 CompletableFuture<Void> 对象放到一个数组中，等待所有的任务执行完成
		 */
		//findPricesReturnFuture ("myPhonew").map (f->f.thenAccept (System.out::println));
		List<String> list = new ArrayList<> ();
		CompletableFuture[] futures = findPricesStream ("myPhonew")
				.map (f -> f.thenAccept (a->list.add (a)))
				.toArray (size -> new CompletableFuture[size]);
		CompletableFuture.allOf (futures).join ();//anyOf
		System.out.println ("list:"+list);



	}
	//重构方法 返回一FUTURE构成的流
	public Stream<CompletableFuture<String>> findPricesStream(String product) {
		return shops.stream ().map (shop -> CompletableFuture.supplyAsync (
				() -> shop.getPriceNew (product), executor
		))//本地获取折扣
				.map (future -> future.thenApply (Quote::parse))
				//异步计算价格
				.map (future -> future.thenCompose (quote ->
						CompletableFuture.supplyAsync (
								() -> Discount.applyDiscount (quote), executor
						)));
	}
	/**
	 * 将两个完全不相干的 CompletableFuture 对象的结果整合起来，而且你也不希望等到第一个任务完全结束才开始第二项任务。
	 * 使用 thenCombine 方法
	 * 需求：一家商店提供的价格是以欧元（EUR）计价的，但是你希望以美元的方式提供给你的客户。
	 * 你可以用异步的方式向商店查询指定商品的价格，同时从远程的汇率服务那里查到欧元和美元之间的汇率。
	 * 当二者都结束时，再将这两个结果结合起来，用返回的商品价格乘以当时的汇率，得到以美元计价的商品价格。
	 */
	//使用java7实现 该需求
	@Test
	public void test_ExecutorinJava7 () throws ExecutionException, InterruptedException {
		//1.创建一个 ExecutorService 将任务提交到线程池
		ExecutorService executor = Executors.newCachedThreadPool ();
		//2.创建一个查询欧元到美元转换汇率的Future
		Future<Double> futureRate = executor.submit (new Callable<Double> () {
			public Double call () {
				return new Rate ().getRate (Rate.Money.EUR, Rate.Money.USD);
			}

		});
		Future<Double> futurePriceInUSD = executor.submit (new Callable<Double> () {
			public Double call () throws ExecutionException, InterruptedException {
				double priceEUR = new Shop ().getPrice ("HUAWEIP9");
				return priceEUR * futureRate.get ();
			}
		});
		System.out.println (futurePriceInUSD.get ());
	}

	@Test
	public void test_06 () {
		Shop shop = new Shop ("gagalin");
		CompletableFuture<Double> futurePriceInUSD = CompletableFuture.supplyAsync (() -> shop.getPrice ("HUAWEIP40"))
				.thenCombine (
						CompletableFuture.supplyAsync (
								() -> new Rate ().getRate (Rate.Money.EUR, Rate.Money.USD)
						)
						, (price, rate) -> price * rate
				);
		System.out.println (futurePriceInUSD.join ());
	}

	/**
	 * 用异步操作加快了查询速度
	 */
	@Test
	public void test_05 () {
		long start = System.nanoTime ();
		System.out.println (findPricesByDiscountAsync ("myPhone27S"));
		long duration = (System.nanoTime () - start) / 1_000_000;
		System.out.println ("Done in " + duration + " msecs");
		//Done in 2006 msecs
	}

	//构造同步异步操作
	//thenApply 方法，将 Stream 中的每个 CompletableFuture<String> 对象转换为对应的CompletableFuture<Quote> 对象
	// thenCompose 方法允许你对两个异步操作进行流水线，第一个操作完成时，将其结果作为参数传递给第二个操作
	public List<String> findPricesByDiscountAsync(String product) {
		List<CompletableFuture<String>> priceFutures = shops.stream ().map (shop -> CompletableFuture.supplyAsync (
				() -> shop.getPriceNew (product), executor
		))//本地获取折扣
				.map (future -> future.thenApply (Quote::parse))
				//异步计算价格
				.map (future -> future.thenCompose (quote ->
						CompletableFuture.supplyAsync (
								() -> Discount.applyDiscount (quote), executor
						))).collect (toList ());
		return priceFutures.stream ().map (CompletableFuture::join).collect (toList ());
	}
	//新的getPrice方法
	@Test
	public void test_04 () {
//		Shop shop = new Shop ("BestPrice");
//		System.out.println (shop.getPriceNew ("iphone100s"));
		long start = System.nanoTime ();
		System.out.println (findPricesByDiscount ("myPhone27S"));
		long duration = (System.nanoTime () - start) / 1_000_000;
		System.out.println ("Done in " + duration + " msecs");
		//Done in 16045 msecs
	}
	//使用discount服务
	public List<String> findPricesByDiscount(String product) {
		return shops.stream()
				.map(shop -> shop.getPriceNew (product))
				.map(Quote::parse)
				.map(Discount::applyDiscount)
				.collect(toList());
	}


	////////////////////////////////////////////////////对多个异步任务进行流水线操作//////////////////////////////////////////////////
	/*
	   小结：使用流还是 CompletableFutures ？
	   没有Io stream
	   有io 用completableFuture
	 */
	/**
	 * 添加定制的执行器测试
	 */
	@Test
	public void test_03 () {
		long start = System.nanoTime ();
		System.out.println (findPricesByCompaltableFutureExecutor ("myPhone27S"));
		long duration = (System.nanoTime () - start) / 1_000_000;
		System.out.println ("Done in " + duration + " msecs");
		//Done in 1007 msecs
		//十个商店 ：Done in 1020 msecs
	}
	//supplyAsync 方法中有 executor 参数可定制线程数
	public List<String> findPricesByCompaltableFutureExecutor (String product) {
		List<CompletableFuture<String>> priceFeature = shops.stream ().map (shop -> CompletableFuture.supplyAsync (
				() -> String.format ("%s price is %.2f",
						shop.getName (), shop.getPrice (product)),executor)
		).collect (toList ());
		//注意
		//CompletableFuture 类中的 join 方法和 Future 接口中的 get 有相同的含义，并且也声明在
		//Future 接口中，它们唯一的不同是 join 不会抛出任何检测到的异常。
		return priceFeature.stream ().map (CompletableFuture::join).collect (Collectors.toList ());
	}
	/**
	 * 发起异步请求
	 */
	@Test
	public void test_02 () {
		long start = System.nanoTime ();
		System.out.println (findPricesByCompaltableFuture ("myPhone27S"));
		long duration = (System.nanoTime () - start) / 1_000_000;
		System.out.println ("Done in " + duration + " msecs");//Done in 1009 msecs
		//十个商店 ：2007 msecs
		//发现并没有提高多少效率
	}

	public List<String> findPricesByCompaltableFuture (String product) {
		List<CompletableFuture<String>> priceFeature = shops.stream ().map (shop -> CompletableFuture.supplyAsync (
				() -> String.format ("%s price is %.2f",
						shop.getName (), shop.getPrice (product)))
		).collect (toList ());
		//注意
		//CompletableFuture 类中的 join 方法和 Future 接口中的 get 有相同的含义，并且也声明在
		//Future 接口中，它们唯一的不同是 join 不会抛出任何检测到的异常。
		return priceFeature.stream ().map (CompletableFuture::join).collect (Collectors.toList ());
	}
	/**
	 * 使用并行流
	 * 算出每个商店同一产品的价格
	 * 方法实现在下边
	 */
	@Test
	public void test_01 () {
		long start = System.nanoTime ();
		//		System.out.println(findPrices("myPhone27S"));
		//使用parall流速度快了三倍
		System.out.println (findPrices_ByParallStream ("myPhone27S"));
		long duration = (System.nanoTime () - start) / 1_000_000;
		System.out.println ("Done in " + duration + " msecs"); //Done in 4006 msecs ||Done in 1009 msecs
	}

	//提供shops 列出每个商品的价格
	//用Stream解决
	public List<String> findPrices (String product) {
		return shops.stream ()
				.map (shop -> String.format ("%s price is %.2f",
						shop.getName (), shop.getPrice (product)))
				.collect (toList ());
	}
	//用paramStream解决
	public List<String> findPrices_ByParallStream (String product) {
		return shops.parallelStream ()
				.map (shop -> String.format ("%s price is %.2f",
						shop.getName (), shop.getPrice (product)))
				.collect (toList ());
	}

	/**
	 * main 方法
	 *
	 * @param args
	 */
	public static void main (String[] args) {
		Shop shop = new Shop ("BestShop");
		long start = System.nanoTime ();
		Future<Double> futurePrice = shop.getPriceAsync ("my favorite product");
		long invocationTime = ((System.nanoTime () - start) / 1_000_000);
		System.out.println ("Invocation returned after " + invocationTime
				+ " msecs");
		// 执行更多任务，比如查询其他商店
		doSomethingElse ();
		// 在计算商品价格的同时
		try {
			double price = futurePrice.get ();
			System.out.printf ("Price is %.2f%n", price);
		} catch (Exception e) {
			throw new RuntimeException (e);
		}
		long retrievalTime = ((System.nanoTime () - start) / 1_000_000);
		System.out.println ("Price returned after " + retrievalTime + " msecs");
	}

	public static void doSomethingElse () {
		System.out.println ("doSomeThing hard");
	}


}


