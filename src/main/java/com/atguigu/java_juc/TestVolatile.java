package com.atguigu.java_juc;

/**
 * @author hike97
 * @create 2018-09-21 15:55
 * @desc Volatile关键字
 **/
public class TestVolatile {
    /*
     分析：
        运行结果为：flag=true。
        没有打印“-------------------”，这就是内存可见性的问题
        主存：flag
        main 线程 在读-->过程 线程 先读主存把主存信息 写入自己的缓存 然后修改 重新写入主存中
        线程1 在写
        两个线程之间 彼此的独立缓存 数据是不可见的-->内存可见性问题
          1)解决方案：同步锁
          2) volatile 关键字：当多个线程进行操作共享数据时，可以保证内存中的数据可见。
             volatile 相较于synchronized 是一种较为轻量级的同步策略
             注意：
             1.volatile 不具备“互斥性”
             2.volatile 不能保证变量的“原子性”
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadDemo td = new ThreadDemo();
        new Thread( td ).start();
        while (true){//效率非常高 没机会读取主存
            //Thread.sleep( 1000 ); 主线程睡几秒之后 给线程1赋值主存flag时间  打印出语句

           // synchronized (td) {
                if (td.isFlag()){
                    System.out.println( "------------------" );
                    break;
                }
            //}
        }
    }
}
class ThreadDemo implements Runnable{

    //private  volatile boolean flag  = false;
    private  boolean flag  = false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {

        try {
            Thread.sleep( 200 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println( "flag=" + isFlag() );
    }
}