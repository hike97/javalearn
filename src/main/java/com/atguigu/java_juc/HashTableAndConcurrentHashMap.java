package com.atguigu.java_juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author hike97
 * @create 2018-09-28 20:21
 * @desc hashTable, hashmap和concurrenthashmap的区别
 **/
public class HashTableAndConcurrentHashMap {
    /*  HashMap:线程不安全
        Hashtable:线程安全，效率低
            hashtable 的锁 锁整个表 并行改串行
            复合操作的安全问题：“若不存在则添加”，“若存在则删除”
            if("table.contains()")
       ConcurrentHashMap 采用“锁分段” 机制
        --concurrentLevel
     */
    /**
     * copyOnWriteArrayList “写入并复制”
     * 报异常：java.util.ConcurrentModificationException
     * 注意：添加操作多时，效率低，因为每次添加时都会进行复制，开销非常大。
     * 并发迭代操作多时可以选择。
     */
    public static void main(String[] args) {
        HelloThread helloThread = new HelloThread();
        for (int i = 0; i <10 ; i++) {
            new Thread( helloThread ).start();
        }
    }
}
class HelloThread implements Runnable {

    //private static List<String> list = Collections.synchronizedList(new ArrayList<String>());
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
    static {
        list.add( "AA" );
        list.add( "BB" );
        list.add( "CC" );
    }
    @Override
    public void run() {
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            System.out.println( it.next() );
            list.add("AA");
        }
    }
}
