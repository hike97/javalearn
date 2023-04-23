package mianshi.newcode.concurrent.base;


import mianshi.newcode.concurrent.base.democlazz.MyTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @ClassName T02_CallableAndFutureTask
 * @Description callable 使用示例
 * @Author hike97
 * @Date 2023/4/1 14:25
 * @Version 1.0
 **/
public class T02_CallableAndFutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /**
         * 实现Callable接口的类无法直接在new Thread中运行，而是需要使用ExecutorService或者FutureTask来执行。这是因为Callable接口
         * 与Runnable接口的执行方式不同。
         * Runnable接口的run方法没有返回值，只能通过Thread类的start方法来启动新线程并执行run方法。而Callable接口的call方法可以返回一
         * 个结果，但是Thread类并没有提供直接执行Callable接口的方法。
         * 因此，需要使用ExecutorService或者FutureTask来执行Callable接口。ExecutorService可以管理线程池并在池中执行任务，
         * 而FutureTask可以将Callable任务转换为Future对象，并在将来某个时间点执行该任务。
         */
        //1.线程池运行callable
        Future<Long> future = Executors.newSingleThreadExecutor().submit(new MyTask());
        System.out.println("结果："+future.get());

        //2.futureTask封装Callable 用线程执行
        FutureTask<Long> futureTask = new FutureTask<>(new MyTask());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}

