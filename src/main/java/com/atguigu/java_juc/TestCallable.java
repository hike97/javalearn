package com.atguigu.java_juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author hike97
 * @create 2018-09-29 9:42
 * @desc 创建执行线程的方式三：实现Callable接口
 **/
public class TestCallable {
    public static void main(String[] args) {
        Thread_callable td = new Thread_callable();
        //执行Callable方式，需要FutrueTask实现类的支持，用于接受运算结果。
        //FutureTask是Future接口的实现类
        FutureTask<Integer> result = new FutureTask<Integer>( td );
        new Thread( result ).start();
        //接收线程运算后的结果
        // 线程结束后 result才会执行 --当i<=Integer.MAX_VALUE时
        // 导致数字过大运行结果慢 result没有显示
        // 可用于闭锁功能
        try {
            Integer sum = result.get();
            System.out.println( "运算结果："+sum );
            System.out.println( "----------------------" );
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 相较于实现runnable 接口的方式，
 * 方法可以有返回值，并且可以抛出异常
 */
class Thread_callable implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <=Integer.MAX_VALUE ; i++) {
            //System.out.println( i );
            sum+=i;
        }
        return sum;
    }
}
/**
 * runnable接口
 */
class ThreadDemo_ implements  Runnable{
    @Override
    public void run() {

    }
}