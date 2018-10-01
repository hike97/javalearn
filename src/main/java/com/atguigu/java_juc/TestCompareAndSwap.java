package com.atguigu.java_juc;

/**
 * @author hike97
 * @create 2018-09-28 19:51
 * @desc 模拟CAS算法
 **/
public class TestCompareAndSwap {
    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();
        for (int i = 0; i <10 ; i++) {
            new Thread( ()->{
                int exceptValue = cas.get();
                boolean b = cas.compareAndSet( exceptValue,(int)(Math.random()*101));
                System.out.println( b );
            }).start();
        }
    }
}
class CompareAndSwap{
    private int value;

    //获取内存值
    public synchronized int get(){
        return value;
    }

    //比较
    public synchronized int compareAndSwap(int exceptValue,int newValue){
        int oldValue = value;
        if (oldValue == exceptValue){
            this.value = newValue;
        }
        return oldValue;
    }
    //设置
    public synchronized  boolean compareAndSet(int exceptValue,int newValue){
        return exceptValue == compareAndSwap( exceptValue,newValue );
    }
}
