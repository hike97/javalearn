package com.thread_pool;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author hike97
 * @Description Executor 和 ExecutorService 以及 Executors（操作Executor的工具类）
 * @create 2019-12-16 13:59
 * @Modified By:
 **/
public class T01_MyExecutor implements Executor {
	//executor 执行器 ->执行一个runnable对象
	//command.run 方法的直接调用
	//new Thread(command) 新启一个线程调用该方法
	@Override
	public void execute (Runnable command) {
		new Thread (command).start ();
//        command.run ();
	}

	public static void main (String[] args) {
		new T01_MyExecutor ().execute (()-> System.out.println ("hello executor"));
	}
}
class T02_ExxecutorService implements ExecutorService{

	@Override
	public void shutdown () {

	}

	@Override
	public List<Runnable> shutdownNow () {
		return null;
	}

	@Override
	public boolean isShutdown () {
		return false;
	}

	@Override
	public boolean isTerminated () {
		return false;
	}

	@Override
	public boolean awaitTermination (long timeout, TimeUnit unit) throws InterruptedException {
		return false;
	}
    //除了继承execute方法 还新增加了submit接口
	// （runnable callable）两个入参
	//runnable 无返回值
	//callable 有返回值
	@Override
	public <T> Future<T> submit (Callable<T> task) {
		return null;
	}

	@Override
	public <T> Future<T> submit (Runnable task, T result) {
		return null;
	}

	@Override
	public Future<?> submit (Runnable task) {
		return null;
	}

	@Override
	public <T> List<Future<T>> invokeAll (Collection<? extends Callable<T>> tasks) throws InterruptedException {
		return null;
	}

	@Override
	public <T> List<Future<T>> invokeAll (Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
		return null;
	}

	@Override
	public <T> T invokeAny (Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
		return null;
	}

	@Override
	public <T> T invokeAny (Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return null;
	}

	@Override
	public void execute (Runnable command) {

	}

}
