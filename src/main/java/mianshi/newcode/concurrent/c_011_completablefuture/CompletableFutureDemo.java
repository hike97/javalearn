package mianshi.newcode.concurrent.c_011_completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;
/**
 * @author: hike97
 * @createTime: 2023/04/23 21:52
 * @description: CompletableFuture 的简单使用
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> System.out.println("hello!"));
        future.get();// 输出 "hello!"
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "hello!");
        assertEquals("hello!", future2.get());

        //thenApply
        CompletableFuture<String> thenApplyFuture = CompletableFuture.completedFuture("hello!").thenApply(s -> s + "world!");
        assertEquals("hello!world!", thenApplyFuture.get());
        // 这次调用将被忽略。
        thenApplyFuture.thenApply(s -> s + "nice!");
        assertEquals("hello!world!", thenApplyFuture.get());

        //thenAccept thenRun
        CompletableFuture.completedFuture("hello!")
                .thenApply(s -> s + "world!").thenApply(s -> s + "nice!").thenAccept(System.out::println);//hello!world!nice!

        CompletableFuture.completedFuture("hello!")
                .thenApply(s -> s + "world!").thenApply(s -> s + "nice!").thenRun(() -> System.out.println("hello!"));//hello!

        CompletableFuture<String> whenComplete = CompletableFuture.supplyAsync(() -> "hello!")
                .whenComplete((res, ex) -> {
                    // res 代表返回的结果
                    // ex 的类型为 Throwable ，代表抛出的异常
                    System.out.println(res);
                    // 这里没有抛出异常所有为 null
                    assertNull(ex);
                });
        assertEquals("hello!", whenComplete.get());
        /**
         * 异常处理
         */
        CompletableFuture<String> exFuture
                = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Computation error!");
            }
            return "hello!";
        }).handle((res, ex) -> {
            // res 代表返回的结果
            // ex 的类型为 Throwable ，代表抛出的异常
            return res != null ? res : "world!";
        });
        assertEquals("world!", exFuture.get());

        CompletableFuture<String> exceptionHandleFuture
                = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("Computation error!");
            }
            return "hello!";
        }).exceptionally(ex -> {
            System.out.println(ex.toString());// CompletionException
            return "world!";
        });
        assertEquals("world!", exceptionHandleFuture.get());
        /**
         * 组合使用
         */
        CompletableFuture<String> thenComposeFuture
                = CompletableFuture.supplyAsync(() -> "hello!")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "world!"));
        assertEquals("hello!world!", thenComposeFuture.get());
        /**
         * thenCompose() 可以两个 CompletableFuture 对象，并将前一个任务的返回结果作为下一个任务的参数，它们之间存在着先后顺序。
         * thenCombine() 会在两个任务都执行完成后，把两个任务的结果合并。两个任务是并行执行的，它们之间并没有先后依赖顺序。
         */
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "hello!")
                .thenCombine(CompletableFuture.supplyAsync(
                        () -> "world!"), (s1, s2) -> s1 + s2)
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "nice!"));
        assertEquals("hello!world!nice!", completableFuture.get());

        /**
         * allOf
         * anyOf
         */
        Random rand = new Random();
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("future1 done...");
            }
            return "abc";
        });
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + rand.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("future2 done...");
            }
            return "efg";
        });

        CompletableFuture<Void> allOf = CompletableFuture.allOf(future3, future4);
        //allOf.join();
        //assertTrue(allOf.isDone());
        //System.out.println("all futures done...");

        CompletableFuture<Object> f = CompletableFuture.anyOf(future3, future4);
        System.out.println(f.get());



    }
}
